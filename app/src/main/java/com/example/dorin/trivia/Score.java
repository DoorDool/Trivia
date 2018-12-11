package com.example.dorin.trivia;

import java.io.Serializable;

public class Score implements Serializable {

    String name, points;

    public Score(String name, String points) {
        this.name = name;
        this.points = points;
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public void setName(String question) {this.name = name;}
    public String getPoints() { return points; }
    public void setPoints(String points) {this.points = points;}

}


