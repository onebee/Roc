package com.one.roc.ioc1;

import android.os.Bundle;

import com.one.librview.InjectManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 帮助子类进行，布局、控件、事件的注入
        InjectManager.inject(this);
    }
}
