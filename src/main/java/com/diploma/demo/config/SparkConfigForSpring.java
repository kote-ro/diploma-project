package com.diploma.demo.config;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SparkConfigForSpring {

    @Value("${app.name.spark-spring-boot}")
    private String appName;

    @Value("${master.uri.local}")
    private String masterUri;

    @Bean
    @Profile("LOCAL")
    public SparkSession sparkSessionLocal(){
        return SparkSession.builder()
                .master(masterUri)
                .appName(appName)
                .config("spark.mongodb.input.uri", "mongodb://localhost:27017/task-3.inputData")
                .config("spark.mongodb.output.uri", "mongodb://localhost:27017/task-3.inputData")
                .getOrCreate();
    }

    @Bean
    @Profile("CLUSTER")
    public SparkSession sparkSessionCluster(){
        return SparkSession.builder()
                .master(masterUri)
                .appName(appName)
                .config("spark.mongodb.input.uri", "mongodb://localhost:27017/task-3.inputData")
                .config("spark.mongodb.output.uri", "mongodb://localhost:27017/task-3.inputData")
                .getOrCreate();
    }

    @Bean
    @Profile("LOCAL")
    public JavaSparkContext sparkContextLocal(){
        return new JavaSparkContext(sparkSessionLocal().sparkContext());
    }

    @Bean
    @Profile("CLUSTER")
    public JavaSparkContext sparkContextCluster(){
        return new JavaSparkContext(sparkSessionLocal().sparkContext());
    }
}
