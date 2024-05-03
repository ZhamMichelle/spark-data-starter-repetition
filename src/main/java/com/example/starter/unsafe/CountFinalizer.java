package com.example.starter.unsafe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Component;

/**
 * @author zhamilya on 4/30/24
 */
@Component("count")
public class CountFinalizer implements Finalizer{
    @Override
    public Object doAction(Dataset<Row> dataset, Class<?> modelClass, OrderedBag<?> orderedBag) {
        return dataset.count();
    }
}
