package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.common.CommonConstant;

import android.util.AttributeSet;

public class CommonUtil {

	/**
	 * カスタマイズVIEWの属性名から属性値を取得
	 * @param attrs VIEWの属性定義
	 * @param attribute 属性名
	 * @return 取得後の属性値
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
