package com.one.librview.rview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    // 当前View
    private View mConvertView;
    // View控件集合
    private SparseArray<View> mViews;

    public RViewHolder(@NonNull View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    /**
     * 通过viewId获取控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 创建RecycleViewViewHold对象
     *
     * @param context  上下文
     * @param parent   父布局
     * @param layoutId 布局ID
     * @return RViewHolder
     */
    static RViewHolder createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new RViewHolder(itemView);
    }

    View getConvertView() {
        return mConvertView;
    }
}
