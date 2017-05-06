package com.bwbrid.mobilesafe.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.view.SettingItewView;

public class SettingActivity extends Activity {

	private Context mContext = MyApplication.getContext();
	
	private String tag = "SettingActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		initUpdate();
	}

	/**
	 * �A�v���������X�V�ݒ菈��
	 */
	private void initUpdate() {
		
		final SettingItewView stv_upadte = (SettingItewView) findViewById(R.id.stv_upadte);
		
		//�@�����X�V�̑I����Ԃ�ݒ�
		boolean isUpdate = SpUtil.getBoolean(SpConstant.OPEN_UPDATE, false);
		stv_upadte.setCheckState(isUpdate);
		
		// �`�F�b�N�{�b�N�X�̑I���C�x���g
		stv_upadte.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				boolean isCheck = stv_upadte.isCheck();
				stv_upadte.setCheckState(!isCheck);
				
				// �ݒ�t�@�C���ɑI���̏�Ԃ��X�V
				SpUtil.putBoolean(SpConstant.OPEN_UPDATE, !isCheck);
			}
		});
	}
}
