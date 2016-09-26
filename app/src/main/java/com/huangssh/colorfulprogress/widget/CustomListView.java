package com.huangssh.colorfulprogress.widget;

import android.content.Context;
import android.widget.ListView;


/**
 * 说明：自定义列表，避免与scollview冲突
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class CustomListView extends ListView {
    public CustomListView(Context context) {
        super(context);
    }


    public CustomListView(Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }


    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
