package com.diploma.demo.service;

import com.diploma.demo.model.Task1Result;
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
public class LogsService {
    private final SparkSession spark;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public LogsService(SparkSession spark) {
        this.spark = spark;
    }


    public Task1Result findWarningsAndErrors(){
        JavaSparkContext sc = JavaSparkContext.fromSparkContext(spark.sparkContext());
        String path = "data/task-1/logs.txt";

        JavaRDD<String> file = sc.textFile(path);
        List<Tuple2<Integer, String>> words1 = file
                .flatMap(line -> Arrays.asList(line.split("\n")).iterator())
                .filter(w -> w.contains("ERROR ") || w.contains("WARN "))
                .mapToPair(w -> (w.contains("ERROR ")) ? new Tuple2<>("ERROR ", 1) : new Tuple2<>("WARN ", 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .collect();

        List<Tuple2<Integer, String>> words2 = file
                .flatMap(line -> Arrays.asList(line.split("\n")).iterator())
                .filter(w -> w.contains("ERROR ") || w.contains("WARN "))
                .mapToPair(w -> new Tuple2<>(w, 1))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .collect();

        List<String> wordsStr1 = words1.stream().map(x -> x._1 + "||" + x._2).collect(Collectors.toList());
        List<String> wordsStr2 = words2.stream().map(x -> x._1 + "||" + x._2).collect(Collectors.toList());

        return new Task1Result(wordsStr1, wordsStr2);
    }
}
