package com.example.starter.unsafe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.util.List;

/**
 * @author zhamilya on 4/10/24
 */
public interface SparkTransformation {
    Dataset<Row> transform(Dataset<Row> dataset, List<String> fieldNames, OrderedBag<Object> args);
}
