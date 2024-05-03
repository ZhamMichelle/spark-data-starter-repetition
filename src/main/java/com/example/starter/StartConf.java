package com.example.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author zhamilya on 5/1/24
 */
@Configuration
public class StartConf {
    @Bean
    @Scope("prototype")
    public LazySparkList lazySparkList() {
        return new LazySparkList();
    }

    @Bean
    public FirstLevelCacheService firstLevelCacheService(){
        return new FirstLevelCacheService();
    }

    @Bean
    public LazyListSupportAspect lazyListSupportAspect(){
        return new LazyListSupportAspect();
    }
}
