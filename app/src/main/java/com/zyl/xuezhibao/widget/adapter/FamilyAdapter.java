package com.zyl.xuezhibao.widget.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.model.common.DetailList;
import com.zyl.xuezhibao.utils.HintShow;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by qwe on 2017/7/5.
 */

public class FamilyAdapter extends CommonAdapter<DetailList.Datail> {
    private SimpleDateFormat mFormat;
    public FamilyAdapter(Context context, List<DetailList.Datail> datas) {
        super(context, R.layout.item_avtivity_friends_list, datas);
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void convert(ViewHolder holder, DetailList.Datail bean, int position) {
        holder.setText(R.id.friends_date,mFormat.format(Long.parseLong(bean.getCreatedDate())));
        holder.setText(R.id.friends_phone,bean.getNickname());
        TextView tvUp = holder.getView(R.id.friends_up);
        if (bean.getParentNickname() != null){
            tvUp.setText("上级好友:"+bean.getParentNickname());
            tvUp.setVisibility(View.VISIBLE);
        }else {
            tvUp.setText("");
            tvUp.setVisibility(View.GONE);
        }
            //是否激活0未激活1已激活
            TextView tvActive = holder.getView(R.id.friends_active);
            if ("0".equals(bean.getIsActivation())){
                tvActive.setText("未激活");
                tvActive.setBackgroundResource(R.drawable.item_over);
            }else if("1".equals(bean.getIsActivation())){
                tvActive.setText("已激活");
                tvActive.setBackgroundResource(R.drawable.item_doing);
            }
    }

}
