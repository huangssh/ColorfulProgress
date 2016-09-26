package com.huangssh.colorfulprogress.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MyBaseFragment extends Fragment {
	protected String TAG = "BaseFragment";
	private String ACTIVITY_NAME = this.getClass().getSimpleName() + ":";
	public  boolean IS_TRACE = false;
	protected boolean mIsFragmentVisible = false;//fragment当前是否可见状态
	
	// 当前显示的内容
	protected View mView;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
        Log.v(TAG, ACTIVITY_NAME + " onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
    
	@Override
	public void onPause() {
		super.onPause();
		
		if(!this.isHidden()){
			onShowChanged(false);
		}
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {

		super.onResume();

		if(!this.isHidden()){
			onShowChanged(true);
		}
	}

	@Override
	public void onDestroyView() {
        Log.v(TAG, ACTIVITY_NAME + "onDestroy  View");
		super.onDestroyView();
	}

	/**
	 * fragment切换造成的隐藏与可见的改变
	 */
    @Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if(hidden){
			onShowChanged(false);
		}else{
			onShowChanged(true);
		}
	}
    
    /**
     * 页面可见变化
     * 1.当前页面可见时触发onpase->onresume 算页面不可见->可见
     * 2.页面由于fragment切换造成不可见与可见时也算
     * 
     * @作者 jiangwenxin
     * @创建时间 2015年12月7日 下午4:50:17  
     * @版本 
     */
    public void onShowChanged(boolean isShow) {
    	mIsFragmentVisible = isShow;
    }

	public void showToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
    public void showToastLong(String text){
		Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }
    
	/**
	 * UI绑定
	 * 
	 * @作者 huangssh
	 * @创建时间 2015-8-6 下午4:10:47  
	 * @param id
	 * @return
	 */
    public <T extends View> T findView(int id) {
        T view = null;
        View genericView = mView.findViewById(id);
        try {
            view = (T) (genericView);
        } catch (Exception ex) {
            String message = "Can't cast view (" + id + ") to a " + view.getClass() + ".  Is actually a " + genericView.getClass() + ".";
            Log.e("PercolateAndroidUtils", message);
            throw new ClassCastException(message);
        }

        return view;
    }
    
    /**
     * 删除layout下的所有的view: 
     * 
     * @作者 huangssh
     * @创建时间 2015-8-6 下午4:54:38  
     * @param layout
     */
	public void deleteAllView(LinearLayout layout) {
		int size = layout.getChildCount();
		for (int i = 0; i < size; i++) {
			layout.removeViewAt(0);
		}
	}
    

    @Override
	public void onDestroy() {
		super.onDestroy();
	}


}
