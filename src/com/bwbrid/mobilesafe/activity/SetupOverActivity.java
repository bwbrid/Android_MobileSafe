package com.bwbrid.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.SpUtil;

public class SetupOverActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup_over);
		
		boolean isFinish = SpUtil.getBoolean(SpConstant.SETUP_FINISH_FLG, false);
		if (!isFinish) {
			Intent intent = new Intent(getApplicationContext(), Setup1Activity.class);
			startActivity(intent);
			finish();
		}
	}
}
