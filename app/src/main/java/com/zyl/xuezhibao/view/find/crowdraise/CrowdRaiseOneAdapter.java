package com.zyl.xuezhibao.view.find.crowdraise;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyl.xuezhibao.R;

import java.util.List;

public class CrowdRaiseOneAdapter extends CommonAdapter<String> {

    private List<String> list;//存放数据
    private Context mContext;

    public CrowdRaiseOneAdapter(List<String> list, Context context) {
        super(context,R.layout.item_layout_find_crowdraise_one,list);
        this.list = list;
        this.mContext = context;
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
//        holder.setText(R.id.tv_find_webcast,s);
//        holder.setImageResource(R.id.iv_find_webcast,R.drawable.wangluozaixianfudao_0);
    }

    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, String msg) {
        if (position < list.size() && position >= 0) {
            list.add(position, msg);
            notifyItemInserted(position);
            return true;
        }
        return false;
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < list.size() && position >= 0) {
            list.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }
}
