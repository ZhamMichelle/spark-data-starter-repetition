package com.example.sparkdatastarterrepetition;

import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class SparkDataStarterRepetitionApplication {

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext context = SpringApplication.run(SparkDataStarterRepetitionApplication.class, args);
            CriminalRepository criminalRepository = context.getBean(CriminalRepository.class);
            List<Criminal> criminals = criminalRepository.findByNumberGreaterThanSortByNumberAsc(13);
            criminals.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}