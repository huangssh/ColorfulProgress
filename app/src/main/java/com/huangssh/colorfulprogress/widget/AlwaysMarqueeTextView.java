package com.huangssh.colorfulprogress.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 走马灯效果text，为了保证走马灯时刻获取焦点所以 isFocused 返回永远为true
 * 在activity设置setFocused并不会时刻获取焦点，因为界面是焦点随时在变化
 * @作者 zhuofq
 * @创建时间  2015-8-4 下午4:44:45
 *
 * @修改人
 * @修改时间
 * @修改内容
 */
public class AlwaysMarqueeTextView extends TextView {

	public AlwaysMarqueeTextView(Context context) {
		super(context);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AlwaysMarqueeTextView(Context context, AttributeSet attrs,
								 int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}