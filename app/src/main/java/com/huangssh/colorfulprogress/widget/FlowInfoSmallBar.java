package com.huangssh.colorfulprogress.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.Util.UtilPhoneParam;
import com.huangssh.colorfulprogress.Util.UtilText;


/**
 * 说明：自定义流量进度条（小版本）
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class FlowInfoSmallBar extends RelativeLayout {

    private TextView tvPackageName, tvPackageFlow, tvPackageMaxFlow, tvPercent, tvPackageMidFlow, tvTime, tvMinflow;
    private RelativeLayout relativePercent, rlMealBar, packageMidRelative;
    private ImageView ivBgGreen, ivBgOrange;
    private double mPercent = 85.00;

    public FlowInfoSmallBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.widget_flow_info_small_bar, this, true);
        initView();
    }

    public FlowInfoSmallBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.widget_flow_info_small_bar, this, true);
        initView();
    }

    private void initView() {
        tvPackageName = (TextView) findViewById(R.id.tv_packagename);
        tvPackageFlow = (TextView) findViewById(R.id.tv_packageflow);
        tvPackageMaxFlow = (TextView) findViewById(R.id.packagemaxflow);
        tvMinflow = (TextView) findViewById(R.id.tv_minflow);
        tvPercent = (TextView) findViewById(R.id.tv_percent);
        relativePercent = (RelativeLayout) findViewById(R.id.relative_percent);
        tvPackageMidFlow = (TextView) findViewById(R.id.packagemidflow);
        ivBgGreen = (ImageView) findViewById(R.id.iv_bg_green);
        ivBgOrange = (ImageView) findViewById(R.id.iv_bg_orange);
        tvTime = (TextView) findViewById(R.id.tv_time);
        rlMealBar = (RelativeLayout) findViewById(R.id.rlMealBar);
        packageMidRelative = (RelativeLayout) findViewById(R.id.package_mid_elative);

        setProgressPercent(0);
    }

    public void setProgressPercent(double green) {
        // 避免微小值不显示
        if (green > 0 && green < 1){
            green = 1;
        }else if(green > 99 && green < 100){
            green = 99;
        }

        ivBgGreen.setLayoutParams(new LinearLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT, (float) green));
        ivBgOrange.setLayoutParams(new LinearLayout.LayoutParams(0,
                LayoutParams.MATCH_PARENT, (float) (100 - green)));
        if ((int) green == 0) {
            ivBgOrange.setBackgroundResource(R.drawable.progressbar_red_color_whole);
        } else if ((int) green > 99) {
            ivBgGreen.setBackgroundResource(R.drawable.progressbar_green_color_whole);
        } else {
            ivBgGreen.setBackgroundResource(R.drawable.progressbar_green_color);
            ivBgOrange.setBackgroundResource(R.drawable.progressbar_red_color);
        }
    }

    public void setPackageName(String packageName) {
        tvPackageName.setText(packageName);
    }

    public void setPackageFlow(String packageFlow, String uint, Double percentShow) {
        // 超过总量的80%，剩余用红色显示
        if (percentShow < 80){
            tvPackageFlow.setTextColor(getResources().getColor(R.color.contentColor));
        }else{
            tvPackageFlow.setTextColor(getResources().getColor(R.color.text_red));
        }
        tvPackageFlow.setText(packageFlow + uint);
    }

    public void setPackageSumFlow(String packageSumFlow, String uint) {
        tvPackageMaxFlow.setText(packageSumFlow + uint);
    }

    public void setPackageMin(String packagemin) {
        tvMinflow.setText(packagemin);
    }

    public void setUsedPercent(double percent) {
        tvPercent.setText((int) percent + "%");
    }

    public void setUsedPercentPadding(int pad) {
        relativePercent.setPadding(pad, 0, 0, 0);
    }

    public void setRlMealBarPadding(int pad) {
        rlMealBar.setPadding(pad, 0, 0, 0);
    }

    public void setLastMonthUnusedFlowPadding(double percentLastShow, int paddingValue, String lastFlow, String maxFlow) {
        if (UtilText.isEmptyOrNull(lastFlow) || "0".equals(lastFlow)) {
            tvPackageMidFlow.setVisibility(GONE);
        }
        tvPackageMidFlow.setText(lastFlow + "M");

        // 结转流量文字
        int textPaintText = (int)tvPackageMidFlow.getPaint().measureText(lastFlow + "M");
        // 流量总量
        int textPaintMax = (int)tvPackageMaxFlow.getPaint().measureText(maxFlow + "M");
        // 初始流量
        int textMinflow = (int)tvMinflow.getPaint().measureText("0M");
        // padding 空间
        int padSum = UtilPhoneParam.screenWidth - paddingValue - textPaintText - textPaintMax;
        // 偏移距离
        double leftLast = (UtilPhoneParam.screenWidth - paddingValue - textPaintText) * percentLastShow / 100;

        // 当结转数据未与其他数据重叠
        if (leftLast > textMinflow && leftLast < padSum){
            packageMidRelative.setPadding((int)leftLast - textMinflow + 20, 0, 0, 0);
            // 当结转数据与总量重叠
        }else if(leftLast >= padSum){
            packageMidRelative.setPadding(padSum - textMinflow - 20, 0, 0, 0);
        }else{
            // 未超过0M
            packageMidRelative.setPadding(20, 0, 0, 0);
        }
    }

    public void setTime(String time) {
        tvTime.setText(time);
    }

    public void setTimeVisible(int visible) {
        tvTime.setVisibility(visible);
    }
}
