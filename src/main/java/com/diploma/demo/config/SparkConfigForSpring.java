package com.diploma.demo.config;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SparkConfigForSpring {

    @Value("${spark.app.name}")
    private String appName;

    @Value("${spark.uri.local}")
    private String masterUriLocal;

    @Value("${spark.uri.cluster}")
    private String masterUriCluster;

    @Value("${spark.mongodb.input.uri}")
    private String inputIri;

    @Value("${spark.mongodb.output.uri}")
    private String outputIri;

    @Bean
    @Profile("LOCAL")
    public SparkSession sparkSessionLocal(){
        return SparkSession.builder()
                .master(masterUriLocal)
                .appName(appName)
                .config("spark.mongodb.input.uri", inputIri)
                .config("spark.mongodb.output.uri", outputIri)
                .getOrCreate();
    }

    @Bean
    @Profile("CLUSTER")
    public SparkSession sparkSessionCluster(){
        return SparkSession.builder()
                .master(masterUriCluster)
                .appName(appName)
                .config("spark.mongodb.input.uri", inputIri)
                .config("spark.mongodb.output.uri", outputIri)
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
