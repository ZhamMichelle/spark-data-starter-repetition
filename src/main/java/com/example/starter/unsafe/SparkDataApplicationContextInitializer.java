package com.example.starter.unsafe;

import com.example.sparkdatastarterrepetition.SparkRepository;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.lang.reflect.Proxy;

/**
 * @author zhamilya on 4/10/24
 */
//@Component
public class SparkDataApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext context) {
        AnnotationConfigApplicationContext tempContext = new AnnotationConfigApplicationContext(InternalConf.class);
        SparkInvocationHandlerFactory factory = tempContext.getBean(SparkInvocationHandlerFactory.class);
        factory.setRealContext(context);
        tempContext.close();

        registerSparkBean(context);
        Reflections scanner = new Reflections(context.getEnvironment().getProperty("spark.package-to-scan"));
        scanner.getSubTypesOf(SparkRepository.class).forEach(sparkRepositoryInterface -> {
            Object krang = Proxy.newProxyInstance(
                    sparkRepositoryInterface.getClassLoader(),
                    new Class[]{sparkRepositoryInterface},
                    factory.create(sparkRepositoryInterface));
            context.getBeanFactory().registerSingleton(Introspector.decapitalize(sparkRepositoryInterface.getSimpleName()), krang);
        });
    }

    private void registerSparkBean(ConfigurableApplicationContext context) {
        SparkSession sparkSession = SparkSession.builder().master("local[*]").appName(context.getEnvironment().getProperty("spark.app-name")).getOrCreate();
        JavaSparkContext sc = new JavaSparkContext(sparkSession.sparkContext());
        context.getBeanFactory().registerSingleton("sparkSession", sparkSession);
        context.getBeanFactory().registerSingleton("sparkContext", sc);
    }
}
