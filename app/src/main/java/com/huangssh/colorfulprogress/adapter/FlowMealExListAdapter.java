package com.huangssh.colorfulprogress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.Util.UtilOther;
import com.huangssh.colorfulprogress.Util.UtilPhoneParam;
import com.huangssh.colorfulprogress.Util.UtilUnitConversion;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2DetailItem;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2Item;
import com.huangssh.colorfulprogress.widget.FlowInfoBar;
import com.huangssh.colorfulprogress.widget.FlowInfoSmallBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：可展开流量套餐适配器
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class FlowMealExListAdapter extends BaseExpandableListAdapter {

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
    // 子级进度偏移量
    int paddingValueSon;
    // 内层rel宽度
    int rlWitch;
    // 子级rel宽度
    int rlSonWitch;
    // 标签游标距离
    double leftLable = 0.00;

    public FlowMealExListAdapter(Context context, List<JfyFlowInfo2Item> mGroupList) {
        this.context = context;
        this.mGroupList = mGroupList;
        paddingValue = UtilUnitConversion.dip2px(context, 20 + 20 + 24);
        paddingValueSon = UtilUnitConversion.dip2px(context, 20 + 20 + 20 + 24);
        rlWitch = UtilUnitConversion.dip2px(context, 20 + 20);
        rlSonWitch = UtilUnitConversion.dip2px(context, 20 + 20 + 20);
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
        final ViewHolder holder;
        JfyFlowInfo2Item jfyFlowInfo2Item = mGroupList.get(groupPosition);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_listview_flow_meal, null);
            holder.ivArrowDown = (ImageView) convertView.findViewById(R.id.ivArrowDown);
            holder.flowInfoBar = (FlowInfoBar) convertView.findViewById(R.id.flowInfoBar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 有子元素的时候显示箭头
        if (getChildrenCount(groupPosition) > 0){
            // 父元素展开的情况下隐藏箭头
            if (isExpanded) {
                holder.ivArrowDown.setVisibility(View.GONE);
            } else {
                holder.ivArrowDown.setVisibility(View.VISIBLE);
            }
        }

        // 结转 + 本月 = 总流量
        sumFlowValue = UtilOther.getSumFlow(jfyFlowInfo2Item.currentMonthFlow, jfyFlowInfo2Item.lastMonthUnusedFlow);
        // 总量 - 已用流量 = 当前可用流量
        canUsedFlowValue = UtilOther.getCanUsedFlow(sumFlowValue, jfyFlowInfo2Item.currentUsedFlow);
        // 已用流量百分比
        percentShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2Item.currentUsedFlow);
        // 结转流量百分比
        percentLastShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2Item.lastMonthUnusedFlow);
        // 计算百分比标签距离
        leftLable = (UtilPhoneParam.screenWidth - paddingValue) * percentShow / 100;

        // 设置流量包名称
        holder.flowInfoBar.setPackageName(jfyFlowInfo2Item.name);
        try {
            // 设置当前可用流量
            holder.flowInfoBar.setPackageFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(canUsedFlowValue), 2), "", percentShow);
            // 设置总量
            holder.flowInfoBar.setPackageSumFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(sumFlowValue), 2), "");
        }catch (Exception e){
            e.printStackTrace();
        }
        // 已使用百分比
        holder.flowInfoBar.setUsedPercent(percentShow);
        // 进度条颜色显示
        holder.flowInfoBar.setProgressPercent(percentLastShow);
        // bar偏移
        holder.flowInfoBar.setRlMealBarPadding((int)leftLable);
        // 标签偏移
        holder.flowInfoBar.setUsedPercentPadding((int)leftLable);
        // 设置结转流量
        holder.flowInfoBar.setLastMonthUnusedFlowPadding(percentLastShow, rlWitch, UtilOther.changeStringto2Point(jfyFlowInfo2Item.lastMonthUnusedFlow), UtilOther.changeStringto2Point(sumFlowValue));

        holder.flowInfoBar.setPackageMin("0M");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        JfyFlowInfo2DetailItem jfyFlowInfo2Item = getChildData(groupPosition).get(childPosition);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_listview_flow_meal_son, null);
            holder.ivArrowUp = (ImageView) convertView.findViewById(R.id.ivArrowUp);
            holder.flowInfoBarSon = (FlowInfoSmallBar) convertView.findViewById(R.id.flowInfoBarSon);
            holder.rlItemFlowMealSon = (RelativeLayout) convertView.findViewById(R.id.rlItemFlowMealSon);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 最后一个子项设置箭头
        if (getChildData(groupPosition).size() > 1){
            if (isLastChild) {
                holder.ivArrowUp.setVisibility(View.VISIBLE);
                holder.rlItemFlowMealSon.setBackgroundResource(R.drawable.bg_item_flow_meal_other_bottom);
            } else {
                if (childPosition == 0){
                    holder.rlItemFlowMealSon.setBackgroundResource(R.drawable.bg_item_flow_meal_other_top);
                }else{
                    holder.rlItemFlowMealSon.setBackgroundResource(R.color.white);
                }
                holder.ivArrowUp.setVisibility(View.GONE);
            }
        }else{
            holder.rlItemFlowMealSon.setBackgroundResource(R.drawable.bg_item_flow_meal);
            holder.ivArrowUp.setVisibility(View.VISIBLE);
        }

        //计算需要用到的值：
        sumFlowValue = UtilOther.getSumFlow(jfyFlowInfo2Item.currentMonthFlow, jfyFlowInfo2Item.lastMonthUnusedFlow);
        canUsedFlowValue = UtilOther.getCanUsedFlow(sumFlowValue, jfyFlowInfo2Item.currentUsedFlow);
        percentShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2Item.currentUsedFlow);
        percentLastShow = UtilOther.getpercentShow(sumFlowValue, jfyFlowInfo2Item.lastMonthUnusedFlow);
        //计算百分比标签距离
        leftLable = (UtilPhoneParam.screenWidth - paddingValueSon) * percentShow / 100;

        holder.flowInfoBarSon.setPackageName(jfyFlowInfo2Item.name);
        holder.flowInfoBarSon.setPackageFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(canUsedFlowValue), 2), "", percentShow);
        holder.flowInfoBarSon.setPackageSumFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(sumFlowValue), 2), "");
        // 已使用百分比
        holder.flowInfoBarSon.setUsedPercent(percentShow);
        // 进度条颜色显示
        holder.flowInfoBarSon.setProgressPercent(percentLastShow);
        // bar偏移
        holder.flowInfoBarSon.setRlMealBarPadding((int)leftLable);
        // 标签偏移
        holder.flowInfoBarSon.setUsedPercentPadding((int)leftLable);
        holder.flowInfoBarSon.setLastMonthUnusedFlowPadding(percentLastShow, rlSonWitch, UtilOther.changeStringto2Point(jfyFlowInfo2Item.lastMonthUnusedFlow), UtilOther.changeStringto2Point(sumFlowValue));

        // 最小值
        holder.flowInfoBarSon.setPackageMin("0M");
        return convertView;
    }

    class ViewHolder {
        ImageView ivArrowDown, ivArrowUp;
        FlowInfoBar flowInfoBar;
        FlowInfoSmallBar flowInfoBarSon;
        RelativeLayout rlItemFlowMealSon;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private List<JfyFlowInfo2DetailItem> getChildData(int groupPosition) {

        List<JfyFlowInfo2DetailItem> childData = null;

        if (mGroupList == null || mGroupList.size() <= groupPosition) {
            return childData;
        }

        JfyFlowInfo2Item groupItemData = mGroupList.get(groupPosition);

        if (groupItemData == null) {
            return childData;
        }

        childData = groupItemData.details;


        return childData;
    }
}
