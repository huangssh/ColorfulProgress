package com.huangssh.colorfulprogress.common;

import com.huangssh.colorfulprogress.model.JfyFlowCardInfoItem;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2DetailItem;
import com.huangssh.colorfulprogress.model.JfyFlowInfo2Item;
import com.huangssh.colorfulprogress.model.JfySmsAccInfoItem;
import com.huangssh.colorfulprogress.model.JfySmsInfoItem;
import com.huangssh.colorfulprogress.model.JfyVoiceAccInfoItem;
import com.huangssh.colorfulprogress.model.JfyVoiceInfoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @作者 huangssh
 * @创建时间 2016/9/23 10:03
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */

public class TestData {
    private static List<JfyFlowInfo2Item> flowInfos = new ArrayList<JfyFlowInfo2Item>();

    public static List<JfyFlowInfo2Item> getData(){
        JfyFlowInfo2Item jfyFlowInfo2Item = new JfyFlowInfo2Item();
        jfyFlowInfo2Item.name = "国内流量--限国内使用";
        jfyFlowInfo2Item.type = "22";
        jfyFlowInfo2Item.currentMonthFlow = "512000.00";
        jfyFlowInfo2Item.lastMonthUnusedFlow = "256000.00";
        jfyFlowInfo2Item.currentUsedFlow = "512000.00";
        List<JfyFlowInfo2DetailItem> details = new ArrayList<JfyFlowInfo2DetailItem>();
        JfyFlowInfo2DetailItem jfyFlowInfo2DetailItem = new JfyFlowInfo2DetailItem();
        jfyFlowInfo2DetailItem.name = "乐享4G201407 59元-主套餐";
        jfyFlowInfo2DetailItem.currentMonthFlow = "256000.00";
        jfyFlowInfo2DetailItem.lastMonthUnusedFlow = "128000.00";
        jfyFlowInfo2DetailItem.currentUsedFlow = "256000.00";
        details.add(jfyFlowInfo2DetailItem);
        jfyFlowInfo2DetailItem = new JfyFlowInfo2DetailItem();
        jfyFlowInfo2DetailItem.name = "乐享4G201407 59元-次套餐";
        jfyFlowInfo2DetailItem.currentMonthFlow = "256000.00";
        jfyFlowInfo2DetailItem.lastMonthUnusedFlow = "256000.00";
        jfyFlowInfo2DetailItem.currentUsedFlow = "128000.00";
        details.add(jfyFlowInfo2DetailItem);
        jfyFlowInfo2Item.details = details;
        flowInfos.add(jfyFlowInfo2Item);

        jfyFlowInfo2Item = new JfyFlowInfo2Item();
        jfyFlowInfo2Item.name = "本地流量--限国内使用";
        jfyFlowInfo2Item.type = "27";
        jfyFlowInfo2Item.currentMonthFlow = "512000.00";
        jfyFlowInfo2Item.lastMonthUnusedFlow = "256000.00";
        jfyFlowInfo2Item.currentUsedFlow = "512000.00";
         details = new ArrayList<JfyFlowInfo2DetailItem>();
        jfyFlowInfo2DetailItem = new JfyFlowInfo2DetailItem();
        jfyFlowInfo2DetailItem.name = "乐享4G201407 59元-主套餐";
        jfyFlowInfo2DetailItem.currentMonthFlow = "256000.00";
        jfyFlowInfo2DetailItem.lastMonthUnusedFlow = "128000.00";
        jfyFlowInfo2DetailItem.currentUsedFlow = "256000.00";
        details.add(jfyFlowInfo2DetailItem);
        jfyFlowInfo2DetailItem = new JfyFlowInfo2DetailItem();
        jfyFlowInfo2DetailItem.name = "乐享4G201407 59元-次套餐";
        jfyFlowInfo2DetailItem.currentMonthFlow = "256000.00";
        jfyFlowInfo2DetailItem.lastMonthUnusedFlow = "256000.00";
        jfyFlowInfo2DetailItem.currentUsedFlow = "128000.00";
        details.add(jfyFlowInfo2DetailItem);
        jfyFlowInfo2Item.details = details;
        flowInfos.add(jfyFlowInfo2Item);

        return flowInfos;
    }

    public static List<JfyFlowCardInfoItem> getCardData(){
        // 流量卡数据
        List<JfyFlowCardInfoItem> jfyFlowCardInfoItems = new ArrayList<JfyFlowCardInfoItem>();
        JfyFlowCardInfoItem jfyFlowCardInfoItem = new JfyFlowCardInfoItem();
        jfyFlowCardInfoItem.flowName = "50元1g流量卡";
        jfyFlowCardInfoItem.flowBalanceAmount = "128000.00";
        jfyFlowCardInfoItem.flowChargeAmount = "256000.00";
        jfyFlowCardInfoItem.flowChargeTime = "2016-09-26";
        jfyFlowCardInfoItem.flowEndTime = "2016-10-26";
        jfyFlowCardInfoItems.add(jfyFlowCardInfoItem);

        return jfyFlowCardInfoItems;
    }

    public static List<JfySmsInfoItem> getMessageData(){
        List<JfySmsInfoItem> jfySmsInfoItems = new ArrayList<JfySmsInfoItem>();
        JfySmsInfoItem jfySmsInfoItem = new JfySmsInfoItem();
        jfySmsInfoItem.accAmount = "100";
        jfySmsInfoItem.balanceAmount = "50";
        jfySmsInfoItem.amountName = "套餐内短信";
        List<JfySmsAccInfoItem> accList = new ArrayList<JfySmsAccInfoItem>();
        JfySmsAccInfoItem jfySmsAccInfoItem = new JfySmsAccInfoItem();
        jfySmsAccInfoItem.accuRscName = "彩信套餐包";
        jfySmsAccInfoItem.accAmount = "100";
        jfySmsAccInfoItem.balanceAmount = "50";
        accList.add(jfySmsAccInfoItem);
        jfySmsInfoItem.accList = accList;
        jfySmsInfoItems.add(jfySmsInfoItem);

        return jfySmsInfoItems;
    }

    public static List<JfyVoiceInfoItem> getVoiceData(){
        List<JfyVoiceInfoItem> jfyVoiceInfoItems = new ArrayList<JfyVoiceInfoItem>();
        JfyVoiceInfoItem voiceInfoItem = new JfyVoiceInfoItem();
        voiceInfoItem.accAmount = "100";
        voiceInfoItem.balanceAmount = "90";
        voiceInfoItem.title = "国内语音";
        List<JfyVoiceAccInfoItem> accList = new ArrayList<JfyVoiceAccInfoItem>();
        JfyVoiceAccInfoItem jfyVoiceAccInfoItem = new JfyVoiceAccInfoItem();
        jfyVoiceAccInfoItem.accuRscName = "国内通话分钟数";
        jfyVoiceAccInfoItem.accAmount = "100";
        jfyVoiceAccInfoItem.balanceAmount = "90";
        accList.add(jfyVoiceAccInfoItem);
        voiceInfoItem.accList = accList;
        jfyVoiceInfoItems.add(voiceInfoItem);

        return jfyVoiceInfoItems;
    }

}
