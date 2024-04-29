package com.example.starter.unsafe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import scala.Tuple2;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhamilya on 4/28/24
 */
@Component("sortBy")
@RequiredArgsConstructor
public class SortTransformationSpider implements TransformationSpider {
    private final Map<String, SparkTransformation> transformationMap;

    @Override
    public Tuple2<SparkTransformation, List<String>> createTransformation(List<String> remainingWords, Set<String> fieldNames) {
        String fieldName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(fieldNames, remainingWords);
        String sortName = WordsMatcher.findAndRemoveMatchingPiecesIfExists(transformationMap.keySet(), remainingWords);
        return new Tuple2<>(transformationMap.get(sortName), List.of(fieldName));
    }
}
