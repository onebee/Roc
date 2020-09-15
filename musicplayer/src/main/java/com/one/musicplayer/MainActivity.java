package com.one.musicplayer;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.one.musicplayer.ui.UIUtils;
import com.one.musicplayer.ui.ViewCalculateUtil;
import com.one.musicplayer.view.MyNestedScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }
    Toolbar toolbar;
    ImageView toolbar_bg;
    ImageView header_bg;
    RecyclerView music_recycler;
    LinearLayout lv_header_contail;
    ImageView header_music_log;
    ImageView header_image_item;
    MyNestedScrollView myNestedScrollView;
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.more));



        toolbar_bg = findViewById(R.id.toolbar_bg);
        header_bg = findViewById(R.id.header_bg);
        music_recycler = findViewById(R.id.music_recycler);
        myNestedScrollView = findViewById(R.id.nsv_scrollview);
        header_music_log = findViewById(R.id.header_music_log);
        LinearLayout lv_header_detail = findViewById(R.id.lv_header_detail);
        RelativeLayout rv_header_container = findViewById(R.id.rv_header_container);
        lv_header_contail = findViewById(R.id.lv_header_contail);
        header_image_item = findViewById(R.id.header_image_item);

        ViewCalculateUtil.setViewLayoutParam(toolbar, 1080, 164, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(rv_header_container,1080,770,164,0,0,0);
        ViewCalculateUtil.setViewLayoutParam(toolbar,1080, 164, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(toolbar_bg,1080,164+ UIUtils.getInstance().getSystemBarHeight(this),0,0,0,0);
        ViewCalculateUtil.setViewLayoutParam(header_bg, 1080, 850, 0, 0, 0, 0);
        ViewCalculateUtil.setViewLayoutParam(lv_header_detail, 1080, 380, 72, 0, 52, 0);
        ViewCalculateUtil.setViewLinearLayoutParam(header_image_item,380,380);
        ViewCalculateUtil.setViewLayoutParam(header_music_log,60,60,59,0,52,0);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }
}
