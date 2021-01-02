package com.one.roc.serialize;

import java.io.Serializable;

/**
 * @author diaokaibin@gmail.com on 12/25/20.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = -1160532192732332548L;

    private String name;
    private float score;

    public Course(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {

    }
}
