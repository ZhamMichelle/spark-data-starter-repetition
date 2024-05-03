package com.example.starter.unsafe;

/**
 * @author zhamilya on 5/1/24
 */
public interface FinalizerPostProcessor {
    Object postFinalize(Object retVal);
}
