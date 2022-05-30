package com.diploma.demo.model;

import java.io.Serializable;

public class Task3Result implements Serializable {
    private String id;
    private String agreeableness;
    private String conscientiousness;
    private String extraversion;
    private String neuroticism;
    private String openness;
    private String cluster;

    public Task3Result() {
    }

    public Task3Result(String id, String agreeableness, String conscientiousness, String extraversion, String neuroticism, String openness, String cluster) {
        this.id = id;
        this.agreeableness = agreeableness;
        this.conscientiousness = conscientiousness;
        this.extraversion = extraversion;
        this.neuroticism = neuroticism;
        this.openness = openness;
        this.cluster = cluster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(String agreeableness) {
        this.agreeableness = agreeableness;
    }

    public String getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(String conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public String getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(String extraversion) {
        this.extraversion = extraversion;
    }

    public String getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(String neuroticism) {
        this.neuroticism = neuroticism;
    }

    public String getOpenness() {
        return openness;
    }

    public void setOpenness(String openness) {
        this.openness = openness;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
