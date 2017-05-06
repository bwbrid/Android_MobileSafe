package com.bwbrid.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.bwbrid.mobilesafe.utils.CommonUtil;

public class SettingItewView extends RelativeLayout {

	private CheckBox cb_setting_check;
	private TextView tv_setting_desc;
	private String mTitle;
	private String mDescOn;
	private String mDescOff;

	public SettingItewView(Context context) {
		this(context, null);
	}

	public SettingItewView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SettingItewView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		View.inflate(context, R.layout.setting_item_view, this);

		// layout.xml����Ώۑ����l���擾
		getAttribute(attrs);

		// �^�C�g����ݒ�
		TextView tv_setting_title = (TextView) findViewById(R.id.tv_setting_title);
		tv_setting_title.setText(mTitle);

		tv_setting_desc = (TextView) findViewById(R.id.tv_setting_desc);
		cb_setting_check = (CheckBox) findViewById(R.id.cb_setting_check);
	}

	/**
	 * layout.xml����Ώۑ����l���擾
	 * 
	 * @param attrs
	 */
	private void getAttribute(AttributeSet attrs) {
		mTitle = CommonUtil.getStringByLayoutXmlAttr(attrs, "title");
		mDescOn = CommonUtil.getStringByLayoutXmlAttr(attrs, "desc_on");
		mDescOff = CommonUtil.getStringByLayoutXmlAttr(attrs, "desc_off");
	}

	/**
	 * �`�F�b�N�{�b�N�X�̑I����ԁitrue: �I���A�@false: ���I���j
	 * 
	 * @return
	 */
	public boolean isCheck() {
		return cb_setting_check.isChecked();
	}

	/**
	 * �`�F�b�N�{�b�N�X�̑I����Ԃɂ�鏈��
	 * 
	 * @param isCheck
	 *            �`�F�b�N�{�b�N�X�̑I�����
	 */
	public void setCheckState(boolean isCheck) {
		cb_setting_check.setChecked(isCheck);
		tv_setting_desc.setText(isCheck ? mDescOn : mDescOff);
	}
}
