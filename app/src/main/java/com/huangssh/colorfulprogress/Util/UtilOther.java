/**   
 * @Title: UtilOther.java 
 * @Package com.ct.client.common.utils 
 * @Description: 工具包
 * @author linwen@ffcs.cn   
 * @date 2015年7月30日 上午11:15:36 
 * @version V1.0   
 */
package com.huangssh.colorfulprogress.Util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 当无法确定其类别时，存放的 工具类
 * 
 * @ClassName: UtilOther
 * @author linwen@ffcs.cn
 * @date 2015年7月30日 上午11:15:36
 * 
 */
public class UtilOther {

	/**
	 * 
	 * 处理服务器返回resultDesc为null时的提示信息
	 * 
	 * @author zhangyi
	 * @createTime 2014-10-21 上午11:10:56
	 * @param resultDesc
	 * @return String
	 */
	public static String getResultDescFilterNull(String resultDesc) {
		if (resultDesc.equals("null")) {
			resultDesc = "服务器异常，请稍后再试";
		}
		return resultDesc;
	}

	/**
	 * 收起输入法键盘
	 * 
	 * @author zhuofq
	 * @param activity
	 */
	public static void closeIMS(Activity activity) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (activity.getCurrentFocus() != null) {
				inputMethodManager.hideSoftInputFromWindow(activity
						.getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 收起输入法键盘 注意 ：当焦点在对话框中，仅仅传Activity无法获得焦点时
	 * 
	 * @author zhuofq
	 * @param activity
	 * @param view
	 */
	public static void closeIMS(Activity activity, View view) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (view != null) {
				inputMethodManager.hideSoftInputFromWindow(
						view.getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showIMS(Activity activity, View view) {
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (view != null) {
				inputMethodManager.showSoftInputFromInputMethod(
						view.getWindowToken(),
						InputMethodManager.SHOW_FORCED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-26 上午9:16:18
	 * @版本
	 * @param s
	 *            原字符串
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * 最多保留n位有效数字（不包含小数点）
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-26 上午9:24:23
	 * @版本
	 * @param s
	 * @param n
	 * @return
	 */
	public static String keepEffectiveDigital(String s, int n) {
		int i = s.indexOf(".");
		s = s.replace(".", "");
		if (s.length() > n) {
			s = s.substring(0, n);
		}

		s = Stringinsert(s, ".", i);
		return s;
	}

	/**
	 * 插入字符串
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-26 上午9:25:06
	 * @版本
	 * @param a
	 *            原字符
	 * @param b
	 *            插入的字符
	 * @param t
	 *            插入位置
	 * @return
	 */
	public static String Stringinsert(String a, String b, int t) {
		return a.substring(0, t) + b + a.substring(t, a.length());
	}

	/**
	 * 去掉重复项
	 * 
	 * @作者 HRX
	 * @创建时间 2015-8-26 下午5:24:44
	 * @版本
	 * @param list
	 * @return
	 */
	public static List<String> removeDuplicate(List<String> list) {
		List<String> newList = new ArrayList<String>();
		Set set = new LinkedHashSet<String>();
		set.addAll(list);
		list.clear();
		list.addAll(set);

		for (String string : list) {
			if (string != null)
				newList.add(string);

		}
		return newList;
	}

	/**
	 * kb转换到mb，保留2未小数并去除末尾0
	 * @param string
	 * @return
	 */
	public static String changeStringto2Point(String string){
		if(UtilText.isEmptyOrNull(string)){
			string = "0";
		}
		double fb = Double.parseDouble(string);
		String s1 = UtilUnitConversion.getFlowFromByteWithoutMB(fb, 2);
		String s2 = UtilText.remove0(s1);
		return s2;
	}

	/**
	 * 流量使用百分比计算
	 *
	 * @param sum
	 * @param used
	 * @return
	 */
	public static double getpercentShow(String sum, String used) {
		//当月使用的当月流量= 当月使用的总流量-上个月未使用的
		if (UtilText.isEmptyOrNull(sum)) {
			sum = "0";
		} else if (UtilText.isEmptyOrNull(used)) {
			used = "0";
		}
		double mySum = Double.parseDouble(sum);
		double myused = Double.parseDouble(used);
		double result = myused * 100 / mySum;
		if (result < 0) {
			result = 0;
		}
		return result;
	}

	/**
	 * 总流量计算
	 *
	 * @param current
	 * @param last
	 * @return
	 */
	public static String getSumFlow(String current, String last) {
		//防止空串或者null转化异常
		if (UtilText.isEmptyOrNull(current)) {
			current = "0";
		} else if (UtilText.isEmptyOrNull(last)) {
			last = "0";
		}
		double myCurrent = Double.parseDouble(current);
		double myLast = Double.parseDouble(last);
		double result = myCurrent + myLast;
		if (result < 0) {
			result = 0;
		}
		return "" + result;
	}

	/**
	 * 剩余流量计算
	 *
	 * @param sum
	 * @param used
	 * @return
	 */
	public static String getCanUsedFlow(String sum, String used) {
		//防止空串或者null转化异常
		if (UtilText.isEmptyOrNull(sum)) {
			sum = "0";
		} else if (UtilText.isEmptyOrNull(used)) {
			used = "0";
		}
		double mySum = Double.parseDouble(sum);
		double myused = Double.parseDouble(used);
		double result = mySum - myused;
		if (result < 0) {
			result = 0;
		}
		return "" + result;
	}


}
