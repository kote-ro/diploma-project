package com.diploma.demo.config;

import com.mongodb.spark.config.WriteConfig;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WriteMongoConfig {
    private final JavaSparkContext jsc;

    public WriteMongoConfig(JavaSparkContext jsc) {
        this.jsc = jsc;
    }

    @Bean
    public WriteConfig getWriteConfig(){
        Map<String, String> writeOverrides = new HashMap<>();
        writeOverrides.put("collection", "inputData");
        writeOverrides.put("writeConcern.w", "majority");
        return WriteConfig.create(jsc).withOptions(writeOverrides);
    }
}
