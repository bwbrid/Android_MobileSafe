package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.common.CommonConstant;

import android.util.AttributeSet;

public class CommonUtil {

	/**
	 * �J�X�^�}�C�YVIEW�̑��������瑮���l���擾
	 * @param attrs VIEW�̑�����`
	 * @param attribute ������
	 * @return �擾��̑����l
	 */
	public static String getStringByLayoutXmlAttr(AttributeSet attrs, String attribute) {
		
		int resId = attrs.getAttributeResourceValue(CommonConstant.NAME_SPACE, attribute, 0);
		String result = null;
		
		if (resId != 0) {
			result = MyApplication.getContext().getResources().getString(resId);
		} else {
			result = attrs.getAttributeValue(CommonConstant.NAME_SPACE, attribute);
		}
		return result;
	}
}
