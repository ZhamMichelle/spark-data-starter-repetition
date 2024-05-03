package com.example.starter.unsafe;

import lombok.SneakyThrows;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author zhamilya on 4/18/24
 */
@Component("collect")
public class CollectFinalizer implements Finalizer {
    @SneakyThrows
    @Override
    public Object doAction(Dataset<Row> dataset, Class<?> modelClass, OrderedBag<?> orderedBag) {
        Encoder<?> encoder = Encoders.bean(modelClass);
        List<String> listFieldNames =
                Arrays.stream(encoder.schema().fields()).filter(structField -> structField.dataType() instanceof ArrayType)
                        .map(StructField::name)
                        .collect(Collectors.toList());
        for (String fieldName : listFieldNames) {
            ParameterizedType genericType = (ParameterizedType) modelClass.getDeclaredField(fieldName).getGenericType();
            Class c = (Class) genericType.getActualTypeArguments()[0];
            dataset = dataset.withColumn(fieldName, functions.lit(null).cast(DataTypes.createArrayType(DataTypes.createStructType(Encoders.bean(c).schema().fields()))));
        }
        return dataset.as(encoder).collectAsList();
    }
}
