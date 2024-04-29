package com.example.starter.unsafe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhamilya on 4/10/24
 */
public interface DataExtractor {
    Dataset<Row> readData(String pathToData, ConfigurableApplicationContext context);
}
