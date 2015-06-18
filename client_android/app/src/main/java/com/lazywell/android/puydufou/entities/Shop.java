package com.lazywell.android.puydufou.entities;

/**
 * Created by victor on 19/06/2015.
 */
public class Shop {

    String name;
    String description;
    double score;

    public Shop(String name, String description, double score){
        this.name = name;
        this.description = description;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
