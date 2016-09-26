package com.huangssh.colorfulprogress.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 说明：自定义拓展列表，避免与scollview冲突
 *
 * @作者 huangssh
 * @创建时间 2016/2/19 14:31
 * @版本
 * @------修改记录-------
 * @修改人
 * @版本
 * @修改内容
 */
public class CustomExpandableListView extends ExpandableListView {
    public CustomExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
    }  
  
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
  
        MeasureSpec.AT_MOST);
  
        super.onMeasure(widthMeasureSpec, expandSpec);  
    }  
}
