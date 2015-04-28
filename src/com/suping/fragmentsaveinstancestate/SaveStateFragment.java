package com.suping.fragmentsaveinstancestate;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SaveStateFragment extends Fragment {
	private String name = "SilWanase";
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		//保存name字段
		outState.putString("name", name);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//恢复name字段
		if(savedInstanceState!=null){
			String s = savedInstanceState.getString("name");
			if(s!=null){
				name = s;
			}
		}
		super.onActivityCreated(savedInstanceState);
	}
	
	/**
	 * onInflate()方法会在fragment和activity绑定之前调用，所以可以在该方法中
	 * 调用setArgments()方法设置Bundle对象，并设置name参数的默认值。setArgments()方法
	 * 必须在onAttach()方法之前调用，onInflate()正好合适。
	 */
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		if(getArguments() == null){
			Bundle b = new Bundle();
			b.putString("name", "arg:default");
			setArguments(b);
		}
		super.onInflate(activity, attrs, savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment,container,false);
		return view;
	}
	
}
