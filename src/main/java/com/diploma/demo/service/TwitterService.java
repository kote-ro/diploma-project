package com.diploma.demo.service;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TwitterService {
    private final SparkSession spark;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public TwitterService(SparkSession spark) {
        this.spark = spark;
    }

    // какие хэштеги можно найти в твиттах с хэштегом #WARINUKRAINE
    public List<String> findTrendingHashtags(){
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        String path = "data/task-2/tweets.txt";

        JavaRDD<String> file = sc.textFile(path);
        List<Tuple2<Integer, String>> words = file
                .flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .filter(w -> w.startsWith("#") && !w.equals("#WARINUKRAINE"))
                .mapToPair(w -> new Tuple2<>(w, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .collect();

       return words.stream().map(x -> x._1 + "||" + x._2).collect(Collectors.toList());
    }
}