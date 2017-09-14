package com.yang.email.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yang.email.R;

/**
 * Created by shiq_yang on 2017/9/1.
 * popwindow viewholder
 */

public class PopWindowViewHolder extends RecyclerView.ViewHolder{

    public TextView email_tv;

    public PopWindowViewHolder(View itemView) {
        super(itemView);
        email_tv = itemView.findViewById(R.id.popwindow_one_text_view);

    }

}
