package com.one.roc.serialize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author diaokaibin@gmail.com on 12/26/20.
 */
class Main {


    public static void main(String[] args) throws Exception {

test01();

    }

    public static void test01() throws Exception {
        //TODO:
        Student student = new Student("Zero", "男", 18);
        Course c1 = new Course("语文", 90.2f);
        Course c2 = new Course("数学", 89.3f);
        List<Course> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        student.setCourses(list);
        //序列化
        byte[] bytes = SerializeableUtils.serialize(student);
        String path = System.getProperty("user.dir") + "\\SerializableDemo\\src\\main\\Demostudent.out";
        SerializeableUtils.saveObject(student, path);
        System.out.println(Arrays.toString(bytes));

        System.out.println("=============反序列化=====================");
        //反序列化
        Student student1 = SerializeableUtils.deserialize(bytes);

        System.out.println("Student: " + student1);

    }
}
