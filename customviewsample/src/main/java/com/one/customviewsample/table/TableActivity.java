package com.one.customviewsample.table;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.one.customviewsample.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author diaokaibin@gmail.com on 2020/10/11.
 */
public class TableActivity extends AppCompatActivity {

    SmartTable table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
