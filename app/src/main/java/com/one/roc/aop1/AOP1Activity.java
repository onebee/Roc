package com.one.roc.aop1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.one.librview.Common;
import com.one.librview.annotation.ContentView;
import com.one.roc.R;
import com.one.roc.ioc1.BaseActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import androidx.annotation.Nullable;


/**
 * @author diaokaibin@gmail.com on 2020/8/29.
 */
@ContentView(R.layout.activity_aop1)
public class AOP1Activity extends BaseActivity implements DBOperation {

    private DBOperation db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = (DBOperation) Proxy.newProxyInstance(DBOperation.class.getClassLoader(), new Class[]{

                DBOperation.class,
        }, new DbClass(this));
    }

    public void jump(View view) {
        db.delete();
    }

    @Override
    public void insert() {
        Log.e(Common.TAG, "新增数据");
    }

    @Override
    public void delete() {
        Log.e(Common.TAG, "删除数据");
    }

    @Override
    public void update() {
        Log.e(Common.TAG, "更新数据");
    }

    @Override
    public void save() {
        Log.e(Common.TAG, "保存数据");
    }

    class DbClass implements InvocationHandler {

        private DBOperation mOperation;

        public DbClass(DBOperation operation) {
            mOperation = operation;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (mOperation != null) {
                Log.i(Common.TAG, "开始备份");
                save();
                Log.i(Common.TAG, "数据备份完成,等待操作");
            }
            return null;
        }
    }
}
