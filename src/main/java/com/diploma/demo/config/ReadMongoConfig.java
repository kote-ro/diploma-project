package com.diploma.demo.config;

import com.mongodb.spark.config.ReadConfig;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReadMongoConfig {
    private final JavaSparkContext jsc;

    public ReadMongoConfig(JavaSparkContext jsc) {
        this.jsc = jsc;
    }

    @Bean
    public ReadConfig getReadConfig(){
        Map<String, String> readOverrides = new HashMap<>();
        readOverrides.put("collection", "outputData");
        readOverrides.put("readPreference.name", "secondaryPreferred");
        return ReadConfig.create(jsc).withOptions(readOverrides);
    }
}
