package com.huangssh.colorfulprogress.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MyFragmentActivity extends FragmentActivity {
	protected String TAG = "MyFragmentActivity";
	protected String ACTIVITY_NAME = this.getClass().getSimpleName() + ":";;
	protected MyFragmentActivity mContext = this;
	private boolean mTrace = true;
    protected boolean isUseCommTrackingHelper = true;//false 表示onreaume的startactivity自定义
    protected boolean isUseCommPauseTrackingHelper = true;//false 表示onpause的stopactivity自定义
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	


	@Override
	protected void onResume() {
		super.onResume();
	}

	
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	public void showToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	public void showToastLong(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
	}

	 /**
     * UI绑定
     * 
     * @作者 huangssh
     * @创建时间 2015-8-6 上午11:20:48  
     * @param id
     * @return
     */
    public <T extends View> T findView(int id) {
        T view = null;
        View genericView = findViewById(id);
        try {
            view = (T) (genericView);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return view;
    }
    
    /**
     * 通过Class跳转界面
     * 
     * @作者 huangssh
     * @创建时间 2015-8-6 上午9:19:29  
     * @param cls 跳转到的class
     */
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/**
	 * 含有Bundle通过Class跳转界面
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-6 上午9:21:52  
	 * @param cls 跳转到的class
	 * @param bundle
	 */
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}
	
	/**
	 * 通过action跳转界面
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-6 上午9:38:09  
	 * @param action
	 */
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/**
	 * 含有bundle通过action跳转界面
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-6 上午9:38:36  
	 * @param action
	 * @param bundle
	 */
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}
}
