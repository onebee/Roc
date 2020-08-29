package com.one.librview.rview;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author diaokaibin@gmail.com on 2020/8/28.
 */
public abstract class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private RView.OnItemClickListener<T> mOnItemClickListener;
    private RView.OnItemLongClickListener<T> mOnItemLongClickListener;

    private List<T> data;

    public abstract void convert(RViewHolder holder, T t);

    public RViewAdapter(List<T> data) {
        if (data == null) {
            this.data = new ArrayList<>();
        }
        this.data = data;
    }

    public abstract int getLayoutId();

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int layoutId = getLayoutId();
        RViewHolder holder = RViewHolder.createViewHolder(viewGroup.getContext(), viewGroup, layoutId);
        setListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    void setOnItemClickListener(RView.OnItemClickListener<T> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    void setOnItemLongClickListener(RView.OnItemLongClickListener<T> mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    private void setListener(final RViewHolder holder) {
        if (holder == null) return;
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != -1) { // 防止连续点击同一条目而且是删除操作
                        mOnItemClickListener.onItemClick(v, data.get(position), position);
                    }
                }
            }
        });
        holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemLongClickListener.onItemLongClick(v, data.get(position), position);
                }
                return false;
            }
        });
    }
}
