package com.zyl.xuezhibao.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.xuezhibao.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView addressName;
    TextView addressPhone;
    TextView addressDefault;
    TextView addressAddress;
    TextView addressDelete;
    ImageView addressEdit;
    public LinearLayout layout;
    public MyViewHolder(View itemView) {
        super(itemView);
        layout = (LinearLayout) itemView.findViewById(R.id.LinearLayout_address_item);
        addressName = (TextView) itemView.findViewById(R.id.address_name);
        addressDelete = (TextView) itemView.findViewById(R.id.address_delete);
        addressPhone = (TextView) itemView.findViewById(R.id.address_phone);
        addressDefault = (TextView) itemView.findViewById(R.id.address_default);
        addressAddress = (TextView) itemView.findViewById(R.id.address_address);
        addressEdit = (ImageView) itemView.findViewById(R.id.address_edit);
    }
}
