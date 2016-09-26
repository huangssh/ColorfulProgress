package com.huangssh.colorfulprogress.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.adapter.FlowCardAdapter;
import com.huangssh.colorfulprogress.adapter.FlowMealExListAdapter;
import com.huangssh.colorfulprogress.adapter.FlowMealOtherExListAdapter;
import com.huangssh.colorfulprogress.base.MyBaseFragment;
import com.huangssh.colorfulprogress.common.Constants;
import com.huangssh.colorfulprogress.common.TestData;
import com.huangssh.colorfulprogress.model.JfyFlowCardInfoItem;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2Item;
import com.huangssh.colorfulprogress.widget.CustomExpandableListView;
import com.huangssh.colorfulprogress.widget.CustomListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：流量查询
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
public class FlowMealFragment extends MyBaseFragment implements OnClickListener {
    private static FlowMealFragment instance = null;
    private CustomExpandableListView exlvGroup;
    private CustomExpandableListView exlvGroupOther;
    private CustomListView lvFlowCard;
    // 国内流量、省内流量或本地流量的进行合并展示, 显示箭头
    private FlowMealExListAdapter flowMealExListAdapter;
    // 定向流量、闲时流量、国际流量的流量不进行合并展示
    private FlowMealOtherExListAdapter flowMealOtherExListAdapter;
    // 流量卡
    private FlowCardAdapter flowCardAdapter;
    // 国内流量、省内流量或本地流量和 其他
    private List<JfyFlowInfo2Item> listFlowInfos = new ArrayList<JfyFlowInfo2Item>(), listFlowInfosOther = new ArrayList<JfyFlowInfo2Item>();
    // 流量卡数据
    private List<JfyFlowCardInfoItem> jfyFlowCardInfoItems = new ArrayList<JfyFlowCardInfoItem>();
    // 所有流量套餐
    private List<JfyFlowInfo2Item> listFlowInfosAll = new ArrayList<JfyFlowInfo2Item>();
    private LinearLayout lLDetailQuery;

    // 流量提示, 流量卡标题
    private TextView tvFlowTip, tvCardTitle;
    // 加流量
    private Button btnAddFlow;

    private String pageTitle;

    public static FlowMealFragment newInstance(Context mContext) {
        if (instance == null) {
            instance = new FlowMealFragment();
        }
        return instance;
    }

    public static FlowMealFragment getInstance() {
        return instance;
    }

    public FlowMealFragment() {
    }
    public FlowMealFragment(String pageTitle) {
        this.pageTitle = pageTitle;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_flow_meal,
                    container, false);
            viewInit();
            updateFlowInfoTask();
            updateFlowCardInfoTask();
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 获取流量卡
     */
    private void updateFlowCardInfoTask() {
        jfyFlowCardInfoItems = TestData.getCardData();
        flowCardAdapter.replace(jfyFlowCardInfoItems);
    }

    private void viewInit() {
        exlvGroup = findView(R.id.exlv_group);
        exlvGroupOther = findView(R.id.exlvGroupOther);
        lLDetailQuery = findView(R.id.lLDetailQuery);
        tvFlowTip = findView(R.id.tvFlowTip);
        btnAddFlow = findView(R.id.btnAddFlow);
        tvCardTitle = findView(R.id.tvCardTitle);

        lLDetailQuery.setOnClickListener(this);
        tvFlowTip.setOnClickListener(this);
        btnAddFlow.setOnClickListener(this);

        // 国内流量、省内流量或本地流量
        flowMealExListAdapter = new FlowMealExListAdapter(getActivity(), listFlowInfos);
        exlvGroup.setGroupIndicator(null);
        exlvGroup.setAdapter(flowMealExListAdapter);

        // 其他流量
        flowMealOtherExListAdapter = new FlowMealOtherExListAdapter(getActivity(), listFlowInfosOther);
        exlvGroupOther.setGroupIndicator(null);
        exlvGroupOther.setAdapter(flowMealOtherExListAdapter);

        // 流量卡
        lvFlowCard = findView(R.id.lvFlowCard);
        flowCardAdapter = new FlowCardAdapter(getActivity());
        lvFlowCard.setAdapter(flowCardAdapter);
        lvFlowCard.setSelector(new ColorDrawable(Color.TRANSPARENT));
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
            // 流量提示
            case R.id.tvFlowTip: {
                break;
            }
            case R.id.btnAddFlow:{
                break;
            }
            default:
                break;
        }
    }

    // 回调接口
    public interface LoadListener{
        public void onPageLoadFinish(Double myUsedFlow);
    }
    private LoadListener mLoadListener;
    public void setLoadListener(LoadListener mLoadListener){
        this.mLoadListener = mLoadListener;
    }

    /**
     * 获取流量套餐列表
     */
    public void updateFlowInfoTask() {
        List<JfyFlowInfo2Item> flowInfos = new ArrayList<JfyFlowInfo2Item>();
        flowInfos.addAll(TestData.getData());
        listFlowInfosAll = flowInfos;

        // 将同属国内流量、省内流量或本地流量的进行合并展示；显示下箭头
        // 将同属定向流量、闲时流量、国际流量的流量等其他流量不进行合并展示，只是进行归类，但子套餐分别展示
        for (int i = 0; i < listFlowInfosAll.size(); i++) {
            if (listFlowInfosAll.get(i).type.equals(Constants.FLOW_TYPE.NATIONAL)
                    || listFlowInfosAll.get(i).type.equals(Constants.FLOW_TYPE.PROVINCE)
                    || listFlowInfosAll.get(i).type.equals(Constants.FLOW_TYPE.LOCAL)) {
                listFlowInfos.add(listFlowInfosAll.get(i));
            } else {
                listFlowInfosOther.add(listFlowInfosAll.get(i));
            }
        }

        flowMealExListAdapter.notifyDataSetChanged();
        flowMealOtherExListAdapter.notifyDataSetChanged();
        // 默认展开
        for (int i = 0; i < flowMealOtherExListAdapter.getGroupCount(); i++) {
            exlvGroupOther.expandGroup(i);
        }
        // 屏蔽其他流量的点击收缩
        exlvGroupOther.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });

        // 点击最后一个子项收缩
        exlvGroup.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (childPosition == listFlowInfos.get(groupPosition).details.size() - 1) {
                    exlvGroup.collapseGroup(groupPosition);
                }
                return false;
            }
        });

    }
}