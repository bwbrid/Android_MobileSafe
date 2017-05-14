package com.bwbrid.mobilesafe.activity;

import com.bwbrid.mobilesafe.utils.LogUtil;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.d("BaseActivity", getClass().getSimpleName());
	}
	
	public String getTag() {
		return getClass().getSimpleName();
	}
}
