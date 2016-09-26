package com.huangssh.colorfulprogress.model;

import java.util.ArrayList;
import java.util.List;

public class JfyFlowInfo2Item {
	public String name="";
	/**
	 * 说明：
	 * 21 国际流量 22 国内流量 23 省内流量 26 本地流量 25 闲时流量 24 定向流量 27 其他
	 * @作者 zhangyi
	 * @创建时间 2016/3/1 19:14
	 * @版本
	 * @------修改记录-------
	 * @修改人
	 * @版本
	 * @修改内容
	 */

	public String type="";
	public String currentMonthFlow="";
	public String lastMonthUnusedFlow="";
	public String currentUsedFlow="";
	public List<JfyFlowInfo2DetailItem> details = new ArrayList<JfyFlowInfo2DetailItem>();

}
