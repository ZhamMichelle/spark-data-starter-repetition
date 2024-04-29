package com.example.starter.unsafe;

import scala.Tuple2;

import java.util.List;
import java.util.Set;

/**
 * @author zhamilya on 4/14/24
 */
public interface TransformationSpider {
    Tuple2<SparkTransformation, List<String>> createTransformation(List<String> remainingWords, Set<String> fieldNames);
}
