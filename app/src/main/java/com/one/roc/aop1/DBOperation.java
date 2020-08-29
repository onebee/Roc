package com.one.roc.aop1;

/**
 * @author diaokaibin@gmail.com on 2020/8/29.
 */
interface DBOperation {

    void insert();

    void delete();

    void update();

    // 数据备份
    void save();
}
