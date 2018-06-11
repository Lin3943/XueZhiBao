package com.zyl.xuezhibao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.zyl.xuezhibao.base.BaseApplication;
import com.zyl.xuezhibao.model.eventBusModel.EventBusWXMessage;
import com.zyl.xuezhibao.utils.Globals;
import com.zyl.xuezhibao.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
    private IWXAPI api;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		api = BaseApplication.api;
        api.handleIntent(getIntent(), this);
		EventBus.getDefault().register(this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}
	@Override
	public void onReq(BaseReq req) {
		Globals.log("log zh WXPayEntryActivity", "req:" + req);
	}

	@Override
	public void onResp(BaseResp resp) {
		int errCode = resp.errCode;
		Globals.log("log zh WXPayEntryActivity", "errCode:" + errCode);
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (errCode) {
			case 0:// 支付成功后的界面
//				Intent intent = new Intent(this, SucceedPayActivity.class);
//				startActivity(intent);
				ToastUtils.showShort(this,"支付成功");
				break;
			case -1:
				break;
			case -2:// 用户取消支付后的界面
				ToastUtils.showShort(this,"取消了支付");
				break;
			}
		}
		finish();
	}

	/**
	 * EventBus接收微信的支付类型（黏性任务）
	 * @param eventBusWXMessage
	 */
	@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
	public void onMessageWxEvent(EventBusWXMessage eventBusWXMessage){
		int mPayType = eventBusWXMessage.getPayType();
		Globals.log("log zh WXPayEntryActivity,onMessageEvent","类型" + mPayType);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}