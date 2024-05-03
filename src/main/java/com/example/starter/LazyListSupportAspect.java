package com.example.starter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author zhamilya on 4/30/24
 */
@Aspect
public class LazyListSupportAspect {
    @Autowired
    private FirstLevelCacheService cacheService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Before("execution(* com.example.starter.LazySparkList.*(..)) && execution(* java.util.*.*(..))")
    public void beforeEachMethodInvocationCheckAndFillContent(JoinPoint jp) {
        LazySparkList lazyList = (LazySparkList) jp.getTarget();
        if (!lazyList.initialized()) {
            List list = cacheService.getDataFor(lazyList.getOwnerId(), lazyList.getModelClass(), lazyList.getPathToSource(), lazyList.getForeignKeyName(), context);
            lazyList.setContent(list);
        }
    }
}
