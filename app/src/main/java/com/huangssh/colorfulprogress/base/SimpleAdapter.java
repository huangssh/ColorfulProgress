/**
 * 版权所有 (c)2015 福富软件
 * 文件名称：SimpleAdapter.java
 * 内容摘要：适配器基类
 * 
 * 
 * 
 * 作者：Linbo  
 *  我只喜欢有你，有我的小世界。
 *
 * 创建时间：2015-6-26 上午8:48:17 
 * 修改记录:
 * 时间	:  
 * 修改人	:  
 * 内容	:
 * 
 * Review历史：
 *  时间    ：
 *       Reviewed By：Linbo   Audited By：
 * 
 */
package com.huangssh.colorfulprogress.base;

import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleAdapter<T> extends BaseAdapter {
	protected List<T> mDatas = new ArrayList<T>();

	@Override
	public int getCount() {
		return mDatas == null ? 0 : mDatas.size();
	}

	protected List<T> getDataList() {
		return mDatas;
	}

	@Override
	public T getItem(int position) {
		return mDatas == null ? null : mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void replace(List<T> items) {
		mDatas.clear();
		mDatas.addAll(items);
		notifyDataSetChanged();
	}

	public void append(List<T> items) {
		if (items != null) {
			mDatas.addAll(items);
			notifyDataSetChanged();
		}
	}

	public void add(T item) {
		if (item != null) {
			mDatas.add(item);
			notifyDataSetChanged();
		}
	}

	public void remove(T item) {
		if (item != null) {
			mDatas.remove(item);
			notifyDataSetChanged();
		}
	}

	public void remove(int position) {
		mDatas.remove(position);
		notifyDataSetChanged();
	}

	public void clear() {
		mDatas.clear();
		notifyDataSetChanged();
	}

	public void setDatas(List<T> items) {
		if (items != null) {
			mDatas = items;
			notifyDataSetChanged();
		}
	}

}
