package com.example.starter;

import lombok.Data;
import lombok.experimental.Delegate;

import java.util.List;

/**
 * @author zhamilya on 4/30/24
 */
@Data
public class LazySparkList implements List {

    @Delegate
    private List content;

    private long ownerId;
    private Class<?> modelClass;
    private String foreignKeyName;
    private String pathToSource;
    public boolean initialized(){
        return (content!=null && !content.isEmpty());
    }
}
