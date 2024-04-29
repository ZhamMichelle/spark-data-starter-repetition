package com.example.starter.unsafe;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhamilya on 4/14/24
 */
@Component
public class DataExtractorResolver {
//    @Autowired
    private Map<String, DataExtractor> extractorMap;

    public DataExtractorResolver(Map<String, DataExtractor> extractorMap) {
        this.extractorMap = extractorMap;
    }

    public DataExtractor resolve(String pathToData) {
        String fileExt = pathToData.split("\\.")[1];
        return extractorMap.get(fileExt);
    }
}
