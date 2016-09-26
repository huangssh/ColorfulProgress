package com.huangssh.colorfulprogress.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.adapter.MessageMealExListAdapter;
import com.huangssh.colorfulprogress.base.MyBaseFragment;
import com.huangssh.colorfulprogress.common.TestData;
import com.huangssh.colorfulprogress.model.JfySmsInfoItem;
import com.huangssh.colorfulprogress.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 说明：短信查询
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
@SuppressLint("ValidFragment")
public class MessageMealFragment extends MyBaseFragment implements OnClickListener {
    private static MessageMealFragment instance = null;
    private CustomExpandableListView exlvGroup;
    // 同类型语音进行合并展示, 显示箭头
    private MessageMealExListAdapter messageMealExListAdapter;
    // 所有流量套餐
    private List<JfySmsInfoItem> jfySmsInfoItems = new ArrayList<JfySmsInfoItem>();
    // 本月详单查询
    private LinearLayout lLDetailQuery;
    private TextView tvTip;

    private String pageTitle;

    public static MessageMealFragment newInstance(Context mContext) {
        if (instance == null) {
            instance = new MessageMealFragment();
        }
        return instance;
    }

    public static MessageMealFragment getInstance() {
        return instance;
    }

    public MessageMealFragment() {
    }
    public MessageMealFragment(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_message_meal,
                    container, false);
            viewInit();
            updateFlowInfoTask();
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void viewInit() {
        exlvGroup = findView(R.id.exlv_message_meal);
        lLDetailQuery = findView(R.id.lLDetailQuery);
        tvTip = findView(R.id.tvTip);
        lLDetailQuery.setOnClickListener(this);

        messageMealExListAdapter = new MessageMealExListAdapter(getActivity(), jfySmsInfoItems);
        exlvGroup.setGroupIndicator(null);
        exlvGroup.setAdapter(messageMealExListAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 本月详单查询
            case R.id.lLDetailQuery: {
                break;
            }
            default:
                break;
        }
    }

    /**
     * 获取套餐列表
     */
    public void updateFlowInfoTask() {
        jfySmsInfoItems.addAll(TestData.getMessageData());
        messageMealExListAdapter.notifyDataSetChanged();
    }
}