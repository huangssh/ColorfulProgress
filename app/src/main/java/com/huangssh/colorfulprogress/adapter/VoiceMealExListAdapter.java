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
import com.huangssh.colorfulprogress.model.JfyVoiceAccInfoItem;
import com.huangssh.colorfulprogress.model.JfyVoiceInfoItem;
import com.huangssh.colorfulprogress.widget.FlowInfoBar;
import com.huangssh.colorfulprogress.widget.FlowInfoSmallBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：语音套餐适配器
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class VoiceMealExListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<JfyVoiceInfoItem> mGroupList = new ArrayList<JfyVoiceInfoItem>();

    // 百分比值
    double percentShow = 0.00;
    int paddingValue;
    int paddingValueSon;
    // 标签游标距离
    int leftLable = 0;

    public VoiceMealExListAdapter(Context context, List<JfyVoiceInfoItem> mGroupList) {
        this.context = context;
        this.mGroupList = mGroupList;
        paddingValue = UtilUnitConversion.dip2px(context, 20 + 20 + 24);
        paddingValueSon = UtilUnitConversion.dip2px(context, 20 + 20 + 20 + 24);
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
        JfyVoiceInfoItem jfyFlowInfo2Item = mGroupList.get(groupPosition);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_listview_voice_meal, null);
            holder.ivArrowDown = (ImageView) convertView.findViewById(R.id.ivArrowDown);
            holder.flowInfoBar = (FlowInfoBar) convertView.findViewById(R.id.flowInfoBar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 有子元素的时候显示箭头
        if (getChildrenCount(groupPosition) > 0) {
            if (isExpanded) {
                holder.ivArrowDown.setVisibility(View.GONE);
            } else {
                holder.ivArrowDown.setVisibility(View.VISIBLE);
            }
        }

        //计算需要用到的值：
        percentShow = 100 - UtilOther.getpercentShow(jfyFlowInfo2Item.accAmount, jfyFlowInfo2Item.balanceAmount);
        //计算百分比标签距离
        leftLable = (UtilPhoneParam.screenWidth - paddingValue) * (int) percentShow / 100;

        holder.flowInfoBar.setPackageName(jfyFlowInfo2Item.title);
        holder.flowInfoBar.setPackageFlow(jfyFlowInfo2Item.balanceAmount, "min", percentShow);
        holder.flowInfoBar.setPackageSumFlow(jfyFlowInfo2Item.accAmount, "min");
        // 已使用百分比
        holder.flowInfoBar.setUsedPercent(percentShow);
        // bar偏移
        holder.flowInfoBar.setRlMealBarPadding(leftLable);
        // 标签偏移
        holder.flowInfoBar.setUsedPercentPadding(leftLable);

        // 最小值
        holder.flowInfoBar.setPackageMin("0min");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder holder;
        JfyVoiceAccInfoItem jfyFlowInfo2Item = getChildData(groupPosition).get(childPosition);
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
        percentShow = 100 - UtilOther.getpercentShow(jfyFlowInfo2Item.accAmount, jfyFlowInfo2Item.balanceAmount);
        //计算百分比标签距离
        leftLable = (UtilPhoneParam.screenWidth - paddingValueSon) * (int) percentShow / 100;

        holder.flowInfoBarSon.setPackageName(jfyFlowInfo2Item.accuRscName);
        holder.flowInfoBarSon.setPackageFlow(jfyFlowInfo2Item.balanceAmount , "min", percentShow);
        holder.flowInfoBarSon.setPackageSumFlow(jfyFlowInfo2Item.accAmount, "min");
        // 已使用百分比
        holder.flowInfoBarSon.setUsedPercent(percentShow);
        // bar偏移
        holder.flowInfoBarSon.setRlMealBarPadding(leftLable);
        // 标签偏移
        holder.flowInfoBarSon.setUsedPercentPadding(leftLable);

        // 最小值
        holder.flowInfoBarSon.setPackageMin("0min");
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

    private List<JfyVoiceAccInfoItem> getChildData(int groupPosition) {

        List<JfyVoiceAccInfoItem> childData = null;

        if (mGroupList == null || mGroupList.size() <= groupPosition) {
            return childData;
        }

        JfyVoiceInfoItem groupItemData = mGroupList.get(groupPosition);

        if (groupItemData == null) {
            return childData;
        }

        childData = groupItemData.accList;


        return childData;
    }

}
