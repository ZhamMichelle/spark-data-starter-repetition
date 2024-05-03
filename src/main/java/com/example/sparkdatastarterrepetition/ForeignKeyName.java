package com.example.sparkdatastarterrepetition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhamilya on 4/30/24
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKeyName {
    String value();
}
