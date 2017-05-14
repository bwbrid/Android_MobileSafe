package com.bwbrid.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bwbrid.mobilesafe.R;

public class Setup1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup1);
		
		Button activity_setup1 = (Button) findViewById(R.layout.activity_setup1);
		activity_setup1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Setup2Activity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
