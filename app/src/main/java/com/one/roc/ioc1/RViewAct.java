package com.one.roc.ioc1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.one.librview.InjectManager;
import com.one.librview.annotation.ContentView;
import com.one.librview.annotation.InjectView;
import com.one.librview.annotation.OnItemClick;
import com.one.librview.annotation.OnItemLongClick;
import com.one.librview.rview.RView;
import com.one.roc.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */

@ContentView(R.layout.rview_act)
public class RViewAct extends BaseActivity {


    @InjectView(R.id.recyclerView)
    private RView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRView();
    }

    // 点击单个Item，会通过InjectManager的injectEvents方法，找到OnItemClick 注解的注解 EventBase
    // 然后通过代理将 RViewAdapter中holder.getConvertView()设置的点击，长按监听回调到 RViewAct 中
    // itemClick itemLongClick方法
    // 实现RecycleView单条目点击回调
    @OnItemClick(R.id.recyclerView)
    public void itemClick(View view, UserInfo info, int position) {
        Toast.makeText(this, "OnItemClick\n" + info.getPassword(), Toast.LENGTH_SHORT).show();
    }

    // 实现RecycleView单条目长按
    @OnItemLongClick(R.id.recyclerView)
    public boolean itemLongClick(View view, UserInfo info, int position) {
        Toast.makeText(this, "OnItemLongClick\n" + info.getPassword(), Toast.LENGTH_SHORT).show();
        return true;
    }

    private void initRView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        SampleAdapter adapter = new SampleAdapter(initDatas());
        recyclerView.setRViewAdapter(adapter);
        recyclerView.setAdapter(adapter);
        InjectManager.injectEvents(this);
    }

    // 模拟真实列表数据
    private List<UserInfo> initDatas() {
        List<UserInfo> datas = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            datas.add(new UserInfo("netease", "p" + i));
        }
        return datas;
    }

}
