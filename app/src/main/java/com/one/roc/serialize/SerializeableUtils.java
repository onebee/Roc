package com.one.roc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author diaokaibin@gmail.com on 12/26/20.
 */
public class SerializeableUtils {

    public static <T> byte[] serialize(T t) throws Exception{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(t);
        return out.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes)throws Exception{
        //TODO:
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        T t = (T)ois.readObject();
        return t;
    }

    synchronized public static boolean saveObject(Object obj, String path) {
        if (obj == null) {
            return false;
        }
        ObjectOutputStream oos = null;
        try {
            // 创建序列化流对象
            oos = new ObjectOutputStream(new FileOutputStream(path));
            //序列化
            oos.writeObject(obj);
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    // 释放资源
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    synchronized public static <T> T readObject(String path) {
        ObjectInputStream ojs = null;
        try {
            // 创建反序列化对象
            ojs = new ObjectInputStream(new FileInputStream(path));
            // 还原对象
            return (T) ojs.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ojs!=null){
                try {
                    // 释放资源
                    ojs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
