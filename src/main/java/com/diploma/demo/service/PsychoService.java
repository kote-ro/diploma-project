package com.diploma.demo.service;

import com.diploma.demo.model.Task3Result;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.WriteConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PsychoService {
    private final JavaSparkContext jsc;
    private final WriteConfig writeConfig;
    private final Logger logger = Logger.getLogger(PsychoService.class.getName());
    @Value("${amount.of.tests}")
    private Integer amountOfTests;
    @Value("${lower.value.limit}")
    private Integer lowerValueLimit;
    @Value("${upper.value.limit}")
    private Integer upperValueLimit;

    public PsychoService(JavaSparkContext jsc, WriteConfig writeConfig) {
        this.jsc = jsc;
        this.writeConfig = writeConfig;
    }

    public void generateData(){
        List<Document> tests = new ArrayList<>();

        for(int i = 1; i <= amountOfTests; i++){
            Document test = initData();
            tests.add(test);
        }

        JavaRDD<Document> documents = jsc.parallelize(tests);
        MongoSpark.save(documents, writeConfig);

        jsc.close();
    }

    public List<Task3Result> processData() {
        Dataset<Row> dataset = MongoSpark.load(jsc).toDF();

        VectorAssembler assembler = new VectorAssembler()
                .setInputCols(new String[]{"openness", "conscientiousness", "extraversion", "agreeableness", "neuroticism"})
                .setOutputCol("features");

        dataset = assembler.transform(dataset);

        KMeans kmeans = new KMeans().setK(5).setSeed(1L);
        KMeansModel model = kmeans.fit(dataset);
        Dataset<Row> predictions = model.transform(dataset);


        return beautifyResults(predictions);
    }

    private Document initData() {
        Integer openness = lowerValueLimit + (int) (Math.random() * upperValueLimit);
        Integer conscientiousness = lowerValueLimit + (int) (Math.random() * upperValueLimit);
        Integer extraversion = lowerValueLimit + (int) (Math.random() * upperValueLimit);
        Integer agreeableness = lowerValueLimit + (int) (Math.random() * upperValueLimit);
        Integer neuroticism = lowerValueLimit + (int) (Math.random() * upperValueLimit);

        Document test = new Document();
        test.append("openness", openness);
        test.append("conscientiousness", conscientiousness);
        test.append("extraversion", extraversion);
        test.append("agreeableness", agreeableness);
        test.append("neuroticism", neuroticism);
        return test;
    }

    private List<Task3Result> beautifyResults(Dataset<Row> results){
        return results
                .map((MapFunction<Row, Task3Result>) row ->
                                new Task3Result(row.get(0).toString(), row.get(1).toString(), row.get(2).toString(),
                                        row.get(3).toString(), row.get(4).toString(), row.get(5).toString(), row.get(7).toString())
                        , Encoders.bean(Task3Result.class))
                .limit(100)
                .collectAsList();
    }

}
