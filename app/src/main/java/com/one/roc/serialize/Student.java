package com.one.roc.serialize;

import java.io.Serializable;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 12/25/20.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -447370927919353594L;

    String name;
    String sex;

    int age;

    List<Course> mCourses;

    public Student(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return mCourses;
    }

    public void setCourses(List<Course> courses) {
        mCourses = courses;
    }
}
