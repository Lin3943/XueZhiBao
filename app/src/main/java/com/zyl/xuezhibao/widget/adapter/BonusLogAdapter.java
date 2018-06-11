package com.zyl.xuezhibao.widget.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.model.common.DetailList;
import com.zyl.xuezhibao.network.Token;
import com.zyl.xuezhibao.utils.Globals;
import com.zyl.xuezhibao.utils.HintShow;
import com.zyl.xuezhibao.utils.ProgressDialogUtils;
import com.zyl.xuezhibao.utils.ToastUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

import static com.zyl.xuezhibao.base.Constants.Url.receiveBonusMsg_URL;
import static com.zyl.xuezhibao.base.Constants.Url.receiveBonus_URL;

/**
 * Created by qwe on 2017/7/5.
 */

public class BonusLogAdapter extends CommonAdapter<DetailList.Datail> {
    private SimpleDateFormat mFormat;
    private Context mContext;

    protected OnRefreshClickListener mOnRefreshClickListener;
    public interface OnRefreshClickListener {
        void onRefreshClick();
    }
    public void setOnRefreshClickListener(OnRefreshClickListener mOnRefreshClickListener) {
        this.mOnRefreshClickListener = mOnRefreshClickListener;
    }

    public BonusLogAdapter(Context context,List<DetailList.Datail> datas) {
        super(context, R.layout.bouns_list_item, datas);
        mContext = context;
        mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    @Override
    protected void convert(ViewHolder holder, final DetailList.Datail bean, int position) {
        holder.setText(R.id.trade_name,"推荐好友【" + bean.getNickname() + "】");
        holder.setText(R.id.trade_order_name,""+bean.getRemark()+"");
        holder.setText(R.id.trade_money,"+" + HintShow.subZeroAndDot(bean.getPrice()));
        holder.setText(R.id.trade_fee,mFormat.format(Long.parseLong(bean.getCreatedDate())));
        //状态-1已错失0等待领取1已领取
        TextView tvStatus = holder.getView(R.id.trade_img);
        if ("-1".equals(bean.getStatus())){
            tvStatus.setText("已错失");
            tvStatus.setBackgroundResource(R.drawable.tv_shape_tuka_hui);
        }else if ("0".equals(bean.getStatus())){
            tvStatus.setText("领取");
            tvStatus.setBackgroundResource(R.drawable.shape_radius_red);
            tvStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ReceiveMsg(bean.getOrderCode());
                }
            });
        }else if ("1".equals(bean.getStatus())){
            tvStatus.setText("已领取");
            tvStatus.setBackgroundResource(R.drawable.tv_shape_bg_blue);
        }
    }

    /**
     * 领取奖金
     */
    private void ReceiveBound(String mOrederNum) {
        ProgressDialogUtils.setProgressDialog(mContext, "正在加载中...");
        HashMap<String, Object> data = new HashMap<>();
        data.put("orderCode", mOrederNum); //订单编号
        data.put("type", 0);//0购股奖金1直推奖金
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(receiveBonus_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ProgressDialogUtils.cancelProgressDialog();
            }
            @Override
            public void onResponse(String response, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    JSONObject object = JSONObject.parseObject(resultData);
                    ToastUtils.showShort(mContext,object.getString("message"));
                    if ("success".equals(object.getString("status"))){
                        if (mOnRefreshClickListener != null){
                            mOnRefreshClickListener.onRefreshClick();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 领取奖金的条件
     */
    private void ReceiveMsg(final String mOrederNum) {
        ProgressDialogUtils.setProgressDialog(mContext, "正在加载中...");
        HashMap<String, Object> data = new HashMap<>();
        data.put("orderCode", mOrederNum); //订单编号
        HashMap<String, String> requestMap = Token.getRequestMap(data);
        OkHttpUtils.post().url(receiveBonusMsg_URL).params(requestMap).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                ProgressDialogUtils.cancelProgressDialog();
            }
            @Override
            public void onResponse(String response, int id) {
                ProgressDialogUtils.cancelProgressDialog();
                try {
                    JSONObject json = JSONObject.parseObject(response);
                    String resultData = new String(Base64.decode(json.getString("data"), Base64.DEFAULT));
                    JSONObject object = JSONObject.parseObject(resultData);
                    Globals.log("log xwj领取奖金条件" + resultData.toString());
                    if ("success".equals(object.getString("status"))) {
                        setDialog("", object.getString("message"),mOrederNum);
                    } else {
                        setDialog("暂不满足领取条件", object.getString("message"),null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 是否满足条件---
     */
    private void setDialog(String title, String mString, final String mOrederNum) {
        View viewDialog = LayoutInflater.from(mContext).inflate(R.layout.out_dialog, null);
        final Dialog dialog = new Dialog(mContext, R.style.dialog_style);
        dialog.addContentView(viewDialog, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setContentView(viewDialog);
        TextView mDialogTitle = (TextView) viewDialog.findViewById(R.id.dialog_title);
        TextView mDialogMessage = (TextView) viewDialog.findViewById(R.id.dialog_message);
        mDialogTitle.setText(title);
        mDialogMessage.setText(mString);
        //取消
        viewDialog.findViewById(R.id.dialog_cancel)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
        //确定
        viewDialog.findViewById(R.id.dialog_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOrederNum != null){
                    ReceiveBound(mOrederNum);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }



}
