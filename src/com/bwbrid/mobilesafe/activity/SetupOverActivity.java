package com.bwbrid.mobilesafe.activity;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.SpUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SetupOverActivity extends Activity {

	private Context mContext = MyApplication.getContext();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_over);
		
		boolean isFinish = SpUtil.getBoolean(SpConstant.SETUP_FINISH_FLG, false);
		if (!isFinish) {
			Intent intent = new Intent(mContext, Setup1Activity.class);
			startActivity(intent);
			finish();
		}
	}
}
