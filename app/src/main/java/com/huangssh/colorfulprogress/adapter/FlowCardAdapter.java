package com.huangssh.colorfulprogress.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.Util.UtilOther;
import com.huangssh.colorfulprogress.Util.UtilPhoneParam;
import com.huangssh.colorfulprogress.Util.UtilText;
import com.huangssh.colorfulprogress.Util.UtilUnitConversion;
import com.huangssh.colorfulprogress.base.SimpleAdapter;
import com.huangssh.colorfulprogress.model.JfyFlowCardInfoItem;
import com.huangssh.colorfulprogress.widget.FlowInfoBar;


/**
 * 说明：流量卡适配器
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class FlowCardAdapter extends SimpleAdapter<JfyFlowCardInfoItem> {

	private static Context mContext;
	// 剩余流量百分比值
	double percentShow = 0.00;
	int paddingValue;
	// 标签游标距离
	int leftLable = 0;

	public FlowCardAdapter(Context mContext){
		this.mContext = mContext;
		paddingValue = UtilUnitConversion.dip2px(mContext, 20 + 20 + 24);
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			Context context = parent.getContext();
			contentView = LayoutInflater.from(context).inflate(
					R.layout.item_listview_flow_meal, null);
			holder = new ViewHolder(contentView);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		JfyFlowCardInfoItem item = mDatas.get(position);
		holder.bindViews(item,position);
		return contentView;
	}

	class ViewHolder {
		FlowInfoBar flowInfoBar;

		public ViewHolder(View contentView) {
			flowInfoBar = (FlowInfoBar) contentView
					.findViewById(R.id.flowInfoBar);
		}

		private void bindViews(JfyFlowCardInfoItem item,int position) {

			// 剩余量百分比：
			percentShow = 100 - UtilOther.getpercentShow(item.flowChargeAmount, item.flowBalanceAmount);
			//计算百分比标签距离
			leftLable = (UtilPhoneParam.screenWidth - paddingValue) * (int) percentShow / 100;

			flowInfoBar.setPackageName(item.flowName);
			flowInfoBar.setUsedPercent(percentShow);
			try {
				flowInfoBar.setPackageFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(item.flowBalanceAmount), 2), "", percentShow);
				flowInfoBar.setPackageSumFlow(UtilUnitConversion.getSimpleFlowFromByteMathFloor(Double.parseDouble(item.flowChargeAmount), 2), "");
			}catch (Exception e){
				e.printStackTrace();
			}

			// bar偏移
			flowInfoBar.setRlMealBarPadding(leftLable);
			// 标签偏移
			flowInfoBar.setUsedPercentPadding(leftLable);
			// 无结转流量
			flowInfoBar.setProgressPercent(0);
			//如果流量冲入时间不为空则正常展示
			if(!UtilText.isEmptyOrNull(item.flowChargeTime)){
				// 时间
				flowInfoBar.setTime(item.flowChargeTime + "-" + item.flowEndTime);
			}else{
				flowInfoBar.setTime("有效期截止:" + item.flowEndTime);
			}
			flowInfoBar.setTimeVisible(View.VISIBLE);

			flowInfoBar.setPackageMin("0M");
		}
	}

}
