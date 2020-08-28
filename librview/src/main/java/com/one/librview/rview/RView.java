package com.one.librview.rview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public class RView extends RecyclerView {


    private RViewAdapter adapter;

    public RView(@NonNull Context context) {
        super(context);
    }

    public RView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param adapter 设置适配器
     */
    public void setRViewAdapter(RViewAdapter adapter) {
        this.adapter = adapter;
    }

    // 条目点击监听接口
    public interface OnItemClickListener<T> {
        // 点击事件监听
        void onItemClick(View view, T entity, int position);
    }

    // 条目长按监听接口
    public interface OnItemLongClickListener<T> {
        // 长按事件监听
        boolean onItemLongClick(View view, T entity, int position);
    }

    /** 设置点击监听属性 */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        adapter.setmOnItemClickListener(onItemClickListener);
    }

    /** 设置长按监听属性 */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        adapter.setmOnItemLongClickListener(onItemLongClickListener);
    }
}
