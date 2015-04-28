package com.suping.fragmentsaveinstancestate;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {
	private SaveStateFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager manager = getFragmentManager();
		if(savedInstanceState != null){
			//恢复fragment
			fragment = (SaveStateFragment) manager
					.getFragment(savedInstanceState, "f");
		}
		//如果没有保存状态，创建新的Fragment对象
		if(fragment==null){
			fragment = (SaveStateFragment) getFragmentManager()
					.findFragmentById(R.id.frag);
		}
		Button set = (Button) findViewById(R.id.set);
		Button get = (Button) findViewById(R.id.get);
		
		set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "fragment"+fragment,0).show();
				if(fragment != null){
					fragment.setName("nevermore");
					fragment.getArguments().putString("name", "新设置的值！！！");
					Toast.makeText(getApplicationContext(), "重新设值",0).show();
				}
			}
		});
		
		get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(fragment!=null){
					EditText et = (EditText) findViewById(R.id.et);
					et.setText(fragment.getArguments().getString("name"));
					Toast.makeText(getApplicationContext(), fragment.getName(),0).show();
					Toast.makeText(getApplicationContext(), fragment.getArguments().getString("name"),1).show();
				}
			}
		});
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		if(fragment!=null){
			//保存fragment
			getFragmentManager().putFragment(outState, "f", fragment);
		}
		super.onSaveInstanceState(outState);
	}
}
