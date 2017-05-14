package com.bwbrid.mobilesafe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.view.SettingItewView;

public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		initUpdate();
	}

	/**
	 * アプリが自動更新設定処理
	 */
	private void initUpdate() {
		
		final SettingItewView stv_upadte = (SettingItewView) findViewById(R.id.stv_upadte);
		
		//　自動更新の選択状態を設定
		boolean isUpdate = SpUtil.getBoolean(SpConstant.OPEN_UPDATE, false);
		stv_upadte.setCheckState(isUpdate);
		
		// チェックボックスの選択イベント
		stv_upadte.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean isCheck = stv_upadte.isCheck();
				stv_upadte.setCheckState(!isCheck);
				
				// 設定ファイルに選択の状態を更新
				SpUtil.putBoolean(SpConstant.OPEN_UPDATE, !isCheck);
			}
		});
	}
}
