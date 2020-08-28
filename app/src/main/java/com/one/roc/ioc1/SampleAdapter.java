package com.one.roc.ioc1;

import android.widget.TextView;

import com.one.librview.rview.RViewAdapter;
import com.one.librview.rview.RViewHolder;
import com.one.roc.R;

import java.util.List;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public class SampleAdapter extends RViewAdapter<UserInfo> {

    public SampleAdapter(List<UserInfo> datas) {
        super(datas);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_item;
    }

    @Override
    public void convert(RViewHolder holder, UserInfo info) {
        TextView textView = holder.getView(R.id.item_tv);
        textView.setText(info.toString());
    }
}