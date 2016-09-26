package com.huangssh.colorfulprogress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.Util.UtilOther;
import com.huangssh.colorfulprogress.Util.UtilPhoneParam;
import com.huangssh.colorfulprogress.Util.UtilUnitConversion;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2DetailItem;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2Item;
import com.huangssh.colorfulprogress.widget.FlowInfoBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：不可展开的流量套餐适配器
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class FlowMealOtherExListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<JfyFlowInfo2Item> mGroupList = new ArrayList<JfyFlowInfo2Item>();
    // 剩余流量
    String canUsedFlowValue = "0", sumFlowValue = "0";
    // 剩余流量百分比值
    double percentShow = 0.00;
    // 结转流量百分比值
    double percentLastShow = 0.00;
    // 父级进度偏移量
    int paddingValue;
    // 标签游标距离
    double leftLable = 0;
    // 内层rel宽度
    int rlWitch;

    public FlowMealOtherExListAdapter(Context context, List<JfyFlowInfo2Item> mGroupList) {
        this.context = context;
        this.mGroupList = mGroupList;

        paddingValue = UtilUnitConversion.dip2px(context, 20 + 20 + 24);
        rlWitch = UtilUnitConversion.dip2px(context, 20 + 20);
    }

    @Override
    public int getGroupCount() {
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getChildData(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return getChildData(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolder holder;
        JfyFlowInfo2Item jfyFlowInfo2Item = mGroupList.get(groupPosition);

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_listview_flow_meal_other, null);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(jfyFlowInfo2Item.name);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        JfyFlowInfo2DetailItem jfyFlowInfo2DetailItem = getChildData(groupPosition).get(childPosition);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_listview_flow_meal_other_son, null);

            holder.rlItemFlowMealSon = (RelativeLayout) convertView.findViewById(R.id.rlItemFlowMealOtherSon);
            holder.flowInfoBarSonOther = (FlowInfoBar) convertView.findViewById(R.id.flowInfoBarSonOther);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isLastChild){
            holder.rlItemFlowMealSon.setBackgroundResource(R.drawable.bg_item_flow_meal_other_bottom);
        }else{
            holder.rlItemFlowMealSon.setBackgroundResource(R.color.white);
        }

        //计算需要用到的值：
        sumFlowValue = UtilOther.getSumFlow(jfyFlowInfo2DetailItem.currentMonthFlow, jfyFlowInfo2DetailItem.lastMonthUnusedFlow);
        canUsedFlowValue = UtilOther.getCanUsedFlow(sumFlowValue, jfyFlowInfo2DetailItem.currentUsedFlow);
        percentShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2DetailItem.currentUsedFlow);
        percentLastShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2DetailItem.lastMonthUnusedFlow);
        //计算百分比标签距离
        leftLable = (UtilPhoneParam.screenWidth - paddingValue) * percentShow / 100;

        holder.flowInfoBarSonOther.setPackageName(jfyFlowInfo2DetailItem.name);

        try {
            holder.flowInfoBarSonOther.setPackageFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(canUsedFlowValue), 2), "", percentShow);
            holder.flowInfoBarSonOther.setPackageSumFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(sumFlowValue), 2), "");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 已使用百分比
        holder.flowInfoBarSonOther.setUsedPercent(percentShow);
        // 进度条颜色显示
        holder.flowInfoBarSonOther.setProgressPercent(percentLastShow);
        // bar偏移
        holder.flowInfoBarSonOther.setRlMealBarPadding((int)leftLable);
        // 标签偏移
        holder.flowInfoBarSonOther.setUsedPercentPadding((int)leftLable);
        holder.flowInfoBarSonOther.setLastMonthUnusedFlowPadding(percentLastShow, rlWitch, UtilOther.changeStringto2Point(jfyFlowInfo2DetailItem.lastMonthUnusedFlow), UtilOther.changeStringto2Point(sumFlowValue));
        holder.flowInfoBarSonOther.setTimeVisible(View.GONE);

        // 最小值
        holder.flowInfoBarSonOther.setPackageMin("0M");
        return convertView;
    }

    class ViewHolder {
        RelativeLayout rlItemFlowMealSon;
        FlowInfoBar flowInfoBarSonOther;
        TextView tvName;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private List<JfyFlowInfo2DetailItem> getChildData(int groupPosition){

        List<JfyFlowInfo2DetailItem> childData = null;

        if (mGroupList == null || mGroupList.size() <= groupPosition) {
            return childData;
        }

        JfyFlowInfo2Item groupItemData = mGroupList.get(groupPosition);

        if(groupItemData == null){
            return childData;
        }

        childData =  groupItemData.details;


        return childData;
    }
}
