package com.zyl.xuezhibao.view.channel.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyl.xuezhibao.R;

import java.util.List;

public class ChannelAdapter extends CommonAdapter<String> {

    private List<String> list;//存放数据
    private Context context;

    public ChannelAdapter(List<String> list, Context context) {
        super(context,R.layout.item_layout_channel,list);
        this.list = list;
        this.context = context;
    }
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout_channel, parent, false));
//        return holder;
//    }
//
//    //在这里可以获得每个子项里面的控件的实例，比如这里的TextView,子项本身的实例是itemView，
//    // 在这里对获取对象进行操作
//    //holder.itemView是子项视图的实例，holder.textView是子项内控件的实例
//    //position是点击位置
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        //设置textView显示内容为list里的对应项
//        holder.textView.setText(list.get(position));
//        //子项的点击事件监听
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "点击子项" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
////这里定义的是子项的类，不要在这里直接对获取对象进行操作
//public class MyViewHolder extends RecyclerView.ViewHolder {
//
//    TextView textView;
//
//    public MyViewHolder(View itemView) {
//        super(itemView);
//        textView = (TextView) itemView.findViewById(R.id.tv_channel);
//    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_channel,s);
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
