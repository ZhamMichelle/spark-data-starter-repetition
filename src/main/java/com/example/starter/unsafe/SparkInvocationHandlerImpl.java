package com.example.starter.unsafe;

import lombok.Builder;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.context.ConfigurableApplicationContext;
import scala.Tuple2;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author zhamilya on 4/10/24
 */
@Builder
public class SparkInvocationHandlerImpl implements SparkInvocationHandler {
    private Class<?> modelClass;
    private String pathToData;
    private DataExtractor dataExtractor;
    private Map<Method, List<Tuple2<SparkTransformation, List<String>>>> transformationChain;
    private Map<Method, Finalizer> finalizerMap;
    private FinalizerPostProcessor postProcessor;

    private ConfigurableApplicationContext context;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        OrderedBag<Object> orderedBag = new OrderedBag<>(args);
        Dataset<Row> dataset = dataExtractor.readData(pathToData, context);
        List<Tuple2<SparkTransformation, List<String>>> tuple2List = transformationChain.get(method);
        for (Tuple2<SparkTransformation, List<String>> tuple : tuple2List) {
            dataset = tuple._1().transform(dataset, tuple._2(), orderedBag);
        }

        Finalizer finalizer = finalizerMap.get(method);
        Object retVal = finalizer.doAction(dataset, modelClass, orderedBag);
        return postProcessor.postFinalize(retVal);
    }
}
