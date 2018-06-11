package com.zyl.xuezhibao.view.channel.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadGroupEntity;
import com.arialyy.aria.core.inf.AbsEntity;
import com.arialyy.aria.core.inf.IEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.widget.ProgressButton;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static android.os.Environment.DIRECTORY_MOVIES;

public class ChannelDetialAdapter extends CommonAdapter<AbsEntity> {

    private List<AbsEntity> mList;//存放数据
    private Context mContext;
    private ProgressButton mProgressButton;
    private boolean isDown = false;
    private Map<String, Integer> mPositions = new ConcurrentHashMap<>();

    public ChannelDetialAdapter(List<AbsEntity> list, Activity context) {
        super(context, R.layout.item_layout_channel_detial, list);
        mList = list;
        mContext = context;
        int i = 0;
        for (AbsEntity entity : mList) {
            mPositions.put(getKey(entity), i);
            i++;
        }
    }

    //要显示的子项数量
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    protected void convert(ViewHolder holder, final AbsEntity absEntity, final int position) {
        holder.setText(R.id.tv_channel_detial, absEntity.getStr());
        String path = Environment.getExternalStoragePublicDirectory(DIRECTORY_MOVIES).getAbsolutePath();
//        String path1 = Environment.DIRECTORY_MOVIES;
//        final String url = mList.get(position);
        final String url = absEntity.getStr();
        final String newpath ;
        int newSum = position + 11;
        if (url.endsWith(".mp4")){
            newpath = path + "/" + newSum + ".mp4";
        }else if (url.endsWith(".flv")){
            newpath = path + "/" + newSum + ".flv";
        }else if (url.endsWith(".mov")){
            newpath = path + "/" + newSum + ".mov";
        }else if (url.endsWith(".mpg")){
            newpath = path + "/" + newSum + ".mpg";
        }else if (url.endsWith(".avi")){
            newpath = path + "/" + newSum + ".avi";
        }else if (url.endsWith(".3gp")){
            newpath = path + "/" + newSum + ".3gp";
        }else if (url.endsWith(".wmv")){
            newpath = path + "/" + newSum + ".wmv";
        }else {
            newpath = path + "/" + newSum + ".mp4";
        }
        mProgressButton = holder.getView(R.id.pb_download);

        int percent = absEntity.getPercent();
        if ( !isDown || percent == 0) {
            mProgressButton.setText("下载");
        } else if (percent >= 100) {
            mProgressButton.setText("完成");
        } else {
            mProgressButton.setProgress(percent);
        }
        mProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if((Integer)mProgressButton.getTag()==position) {
//                Aria.get(mContext).getDownloadConfig();
                mProgressButton.setTag(position);
                Aria.download(mContext)
                        .load(url)
                        .setDownloadPath(newpath)
                        .start();
//                }
            }
        });
    }

    /**
     * 更新状态
     * @param entity
     */
    public synchronized void updateState(AbsEntity entity) {
        if (entity.getState() == IEntity.STATE_CANCEL) {
            mList.remove(entity);
            mPositions.clear();
            int i = 0;
            for (AbsEntity entity_1 : mList) {
                mPositions.put(getKey(entity_1), i);
                i++;
            }
            notifyDataSetChanged();
        } else if (entity.getState() == IEntity.STATE_COMPLETE){
            int position = indexItem(getKey(entity));
            if (position == -1 || position >= mList.size()) {
                return;
            }
            mList.set(position, entity);
            mProgressButton.getTag();
            mProgressButton.setText("完成");
            mProgressButton.setClickable(false);
            mProgressButton.setBackgroundColor(Color.rgb(0,255,0));
            isDown = true;
            notifyItemChanged(position);
        } else {
            int position = indexItem(getKey(entity));
            if (position == -1 || position >= mList.size()) {
                return;
            }
            mList.set(position, entity);
            notifyItemChanged(position);
        }
    }

    private String getKey(AbsEntity entity) {
        if (entity instanceof DownloadEntity) {
            return ((DownloadEntity) entity).getUrl();
        } else if (entity instanceof DownloadGroupEntity) {
            return ((DownloadGroupEntity) entity).getGroupName();
        }
        return "";
    }

    /**
     * 更新进度
     */
    public synchronized void setProgress(AbsEntity entity) {
        String url = entity.getKey();
        int position = indexItem(url);
        if (position == -1 || position >= mList.size()) {
            return;
        }
        mList.set(position, entity);
        notifyItemChanged(position, entity);
    }

    private synchronized int indexItem(String url) {
        Set<String> keys = mPositions.keySet();
        for (String key : keys) {
            if (key.equals(url)) {
                return mPositions.get(key);
            }
        }
        return -1;
    }





    /*之下的方法都是为了方便操作，并不是必须的*/

    //在指定位置插入，原位置的向后移动一格
    public boolean addItem(int position, String msg) {
//        if (position < mList.size() && position >= 0) {
//            mList.add(position, msg);
//            notifyItemInserted(position);
//            return true;
//        }
        return false;
    }

    //去除指定位置的子项
    public boolean removeItem(int position) {
        if (position < mList.size() && position >= 0) {
            mList.remove(position);
            notifyItemRemoved(position);
            return true;
        }
        return false;
    }

    //清空显示数据
    public void clearAll() {
        mList.clear();
        notifyDataSetChanged();
    }

}
