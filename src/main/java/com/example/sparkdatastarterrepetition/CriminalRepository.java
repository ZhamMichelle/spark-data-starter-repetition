package com.example.sparkdatastarterrepetition;

import java.util.List;

/**
 * @author zhamilya on 4/10/24
 */
public interface CriminalRepository extends SparkRepository<Criminal> {
    List<Criminal> findByNumberBetween(int min, int max);
    List<Criminal> findByNumberGreaterThan(int min);
    List<Criminal> findByNumberGreaterThanOrderByNumber(int num);
    long findByNumberGreaterThanOrderByNumberCount(int num);
    List<Criminal> findByNumberGreaterThanOrderByNumberAndName(int num);  //does not work
}
