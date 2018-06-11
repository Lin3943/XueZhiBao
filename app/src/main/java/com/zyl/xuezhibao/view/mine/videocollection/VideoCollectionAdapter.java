package com.zyl.xuezhibao.view.mine.videocollection;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyl.xuezhibao.R;

import java.util.List;

public class VideoCollectionAdapter extends CommonAdapter<String> {

    private List<String> list;//存放数据
    private Context context;

    public VideoCollectionAdapter(List<String> list, Context context) {
        super(context,R.layout.item_layout_channel,list);
        this.list = list;
        this.context = context;
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        String fileName = null;
        try {
            fileName = splitVideoName(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.setText(R.id.tv_channel,fileName);
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
    /**
     * 根据路径获取文件名
     *
     * @param path 路径
     * @return 文件名字符串
     */
    private String splitVideoName(String path) throws Exception {
        // 判空操作必须要有 , 处理方式不唯一 , 根据实际情况可选其一 。
        if (path == null) {
            throw new Exception("路径不能为null"); // 处理方法一
        }

        int start = path.lastIndexOf("/");
        int end = path.lastIndexOf(".");
        if (start != -1 && end != -1) {
//            return path.substring(start + 1, end);//包含头不包含尾 , 故:头 + 1
            return path.substring(start + 1);//包含头不包含尾 , 故:头 + 1
        } else {
//            return null; 返回 null 还是 "" 根据情况抉择吧
            return "";
        }
    }

}
