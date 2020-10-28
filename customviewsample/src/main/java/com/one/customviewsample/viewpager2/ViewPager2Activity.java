package com.one.customviewsample.viewpager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.one.customviewsample.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author diaokaibin@gmail.com on 2020/10/27.
 */
public class ViewPager2Activity extends AppCompatActivity {
    ViewPager2 mViewPager2;
    Button mButton1;
    Button mButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager2 = findViewById(R.id.view_pager);
        mButton1 = findViewById(R.id.add);
        mButton2 = findViewById(R.id.add2);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AdapterFragmentPager adapterFragmentPager = new AdapterFragmentPager(this);
        mViewPager2.setAdapter(adapterFragmentPager);
        adapterFragmentPager.update();


        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterFragmentPager.update1();
//                adapterFragmentPager.notifyDataSetChanged();
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterFragmentPager.update2();
//                adapterFragmentPager.notifyDataSetChanged();
            }
        });

    }
}
