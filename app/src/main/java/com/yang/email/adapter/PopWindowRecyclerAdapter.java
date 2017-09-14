package com.yang.email.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.email.util.models.EmailServerData;
import com.yang.email.R;
import com.yang.email.adapter.viewholder.PopWindowViewHolder;
import com.yang.email.interfac.OnItemClickListener;

import java.util.List;

/**
 * Created by shiq_yang on 2017/9/1.
 * popwindow adapter
 */

public class PopWindowRecyclerAdapter extends RecyclerView.Adapter<PopWindowViewHolder> implements View.OnClickListener{

    private Context context;
    private List<EmailServerData> list;
    private OnItemClickListener onItemClickListener = null;

    public PopWindowRecyclerAdapter(Context context, List<EmailServerData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public PopWindowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PopWindowViewHolder viewHolder = new PopWindowViewHolder(LayoutInflater.from(
                context).inflate(R.layout.popwindow_one_text_layout, parent,
                false));


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PopWindowViewHolder holder, int position) {
        holder.email_tv.setText(list.get(position).getEmailNameString());
        holder.email_tv.setTag(position);
        holder.email_tv.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 增加监听器
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(view, (Integer) view.getTag());
    }

}
