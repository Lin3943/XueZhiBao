package com.zyl.xuezhibao.view.find;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zyl.xuezhibao.R;
import com.zyl.xuezhibao.base.BaseApplication;
import com.zyl.xuezhibao.view.find.CustomView.HotSearchView;
import com.zyl.xuezhibao.view.find.crowdraise.CrowdRaiseActivity;
import com.zyl.xuezhibao.view.find.psychagogy.PsychagogyActivity;
import com.zyl.xuezhibao.view.find.publicbenefit.PublicBenifitActivity;
import com.zyl.xuezhibao.view.find.recorded.RecordedActivity;
import com.zyl.xuezhibao.view.find.schools.SchoolsActivity;
import com.zyl.xuezhibao.view.find.teacher.TeachersActivity;
import com.zyl.xuezhibao.view.find.teachingresearch.TeachingResearchActivity;
import com.zyl.xuezhibao.view.find.webcast.WebcastActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zyl.xuezhibao.view.Constant.FIND;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    private List<String> messageList;
    private List<String> mFindList;
    private View mView;
    private HotSearchView mFlowLayout;
    private String[] mLabels = {"购物", "美食", "游玩", "北京", "教育", "高考", "新春送祝福"};
    private ImageButton mBack;
    private Button mBtnGoSearch;
    private EditText mEdtSearch;
    private TextView mTvSearchResult;
    private RecyclerView mRcvSearchFind;
    private long mExitTime = 0;
    private Context mContext;
    private FindAdapter mFindAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public FindFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_find, container, false);
            initUI();
            initData();
            initListener();
        }
        return mView;
    }

    private void initUI() {
        mFlowLayout = (HotSearchView) mView.findViewById(R.id.flowLayout);
        mBack = (ImageButton) mView.findViewById(R.id.img_btn_back);
        mBtnGoSearch = (Button) mView.findViewById(R.id.btn_go_search);
        mEdtSearch = (EditText) mView.findViewById(R.id.edt_search_content);
        mTvSearchResult = (TextView) mView.findViewById(R.id.tv_search_result);
        mRcvSearchFind = (RecyclerView) mView.findViewById(R.id.rcv_search_find);

    }

    private void initListener() {
        mBack.setOnClickListener(this);
        mBtnGoSearch.setOnClickListener(this);
    }

    protected void initData() {
        initLabel();
        initFind();
    }

    private void initFind() {
        mFindList = new ArrayList<>();
        mFindList.add("网络直播");
        mFindList.add("名师众筹");
        mFindList.add("网络录播");
        mFindList.add("公益课程");
        mFindList.add("心里教育");
        mFindList.add("教研直播");
        mFindList.add("网络学校");
        mFindList.add("网络教师");
        mFindList.add("英语外教");
        mFindAdapter = new FindAdapter(mFindList, mContext);
        mRcvSearchFind.setLayoutManager(new GridLayoutManager(mContext, 3));
        mRcvSearchFind.setAdapter(mFindAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        mFindAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String string = mFindList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString(FIND, string);
                switch (position) {
                    case 0:
                        gotoActivity(bundle,WebcastActivity.class);
                        break;
                    case 1:
                        gotoActivity(bundle,CrowdRaiseActivity.class);
                        break;
                    case 2:
                        gotoActivity(bundle,RecordedActivity.class);
                        break;
                    case 3:
                        gotoActivity(bundle,PublicBenifitActivity.class);
                        break;
                    case 4:
                        gotoActivity(bundle,PsychagogyActivity.class);
                        break;
                    case 5:
                        gotoActivity(bundle,TeachingResearchActivity.class);
                        break;
                    case 6:
                        gotoActivity(bundle,SchoolsActivity.class);
                        break;
                    case 7:
                        gotoActivity(bundle,TeachersActivity.class);
                        break;
                    case 8:
                        String url = "http://www.baidu.com";
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri uri = Uri.parse(url);
                        intent.setData(uri);
                        startActivity(intent);
                        break;
                }

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    // 初始化标签
    private void initLabel() {
        ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 10, 10);// 设置边距
        for (int i = 0; i < mLabels.length; i++) {
            final TextView textView = new TextView(getContext());
            textView.setTag(i);
            textView.setTextSize(15);
            textView.setText(mLabels[i]);
            textView.setPadding(24, 11, 24, 11);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.bg_search_label);
            mFlowLayout.addView(textView, layoutParams);
            // 标签点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getContext(), mLabels[(int) textView.getTag()], Toast.LENGTH_SHORT).show();
                    mEdtSearch.setText(mLabels[(int) textView.getTag()]);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_back:
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    Toast.makeText(mContext, "再次点击退出", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    BaseApplication.getInstance().exit();
                }
                break;
            case R.id.btn_go_search:
                mTvSearchResult.setText("搜索结果：" + mEdtSearch.getText());
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 跳转具体页面
     */
    private void gotoActivity(Bundle bundle ,Class<?> class1) {
        Intent intent = new Intent(mContext, class1);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
