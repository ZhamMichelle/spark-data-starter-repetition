package com.example.starter.unsafe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * @author zhamilya on 4/10/24
 */
public interface Finalizer {
    Object doAction(Dataset<Row> dataset, Class<?> modelClass, OrderedBag<?> orderedBag);
}
