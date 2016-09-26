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
import com.huangssh.colorfulprogress.adapter.VoiceMealExListAdapter;
import com.huangssh.colorfulprogress.base.MyBaseFragment;
import com.huangssh.colorfulprogress.common.TestData;
import com.huangssh.colorfulprogress.model.JfyVoiceInfoItem;
import com.huangssh.colorfulprogress.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;


/**
 * 说明：语音查询
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
public class VoiceMealFragment extends MyBaseFragment implements OnClickListener {
    private static VoiceMealFragment instance = null;
    private CustomExpandableListView exlvGroup;
    // 同类型语音进行合并展示, 显示箭头
    private VoiceMealExListAdapter voiceMealExListAdapter;
    // 所有语音套餐
    private List<JfyVoiceInfoItem> jfyVoiceInfoItems = new ArrayList<JfyVoiceInfoItem>();
    // 详单
    private LinearLayout lLDetailQuery;
    private TextView tvTip;

    private String pageTitle;

    public static VoiceMealFragment newInstance(Context mContext) {
        if (instance == null) {
            instance = new VoiceMealFragment();
        }
        return instance;
    }

    public static VoiceMealFragment getInstance() {
        return instance;
    }

    public VoiceMealFragment() {
    }
    public VoiceMealFragment(String pageTitle) {
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
            mView = inflater.inflate(R.layout.fragment_voice_meal,
                    container, false);
            viewInit();
            updateFlowInfoTask();
        }

        return mView;
    }


    private void viewInit() {
        exlvGroup = findView(R.id.exlv_voice_meal);
        lLDetailQuery = findView(R.id.lLDetailQuery);
        tvTip = findView(R.id.tvTip);
        lLDetailQuery.setOnClickListener(this);

        voiceMealExListAdapter = new VoiceMealExListAdapter(getActivity(), jfyVoiceInfoItems);
        exlvGroup.setGroupIndicator(null);
        exlvGroup.setAdapter(voiceMealExListAdapter);
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
     * 获取流量套餐列表
     */
    public void updateFlowInfoTask() {
        jfyVoiceInfoItems.addAll(TestData.getVoiceData());
        voiceMealExListAdapter.notifyDataSetChanged();
    }
}