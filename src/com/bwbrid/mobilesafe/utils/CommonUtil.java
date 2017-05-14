package com.bwbrid.mobilesafe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.lidroid.xutils.util.LogUtils;

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
	
	/**
	 * 文字列暗号化
	 * @param password 文字列
	 * @return 暗号化後文字列
	 */
	public static String encoder(String password) {
		
		StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bs = digest.digest(getSalt(password).getBytes());
			for (byte b : bs) {
				int i = b & 0xff;
				String hexString = Integer.toHexString(i);
				if (hexString.length() < 2) {
					hexString = zeroPadding(hexString, 2);
				}
				sb.append(hexString);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	/**
	 * ゼロパディング
	 * @param target 対象文字列
	 * @param paddingLength パディング後の文字長
	 * @return 処理後文字列
	 */
	public static String zeroPadding(String target, int paddingLength) {
		if (target.length() >= paddingLength) return target;
		
		StringBuffer sb = new StringBuffer();
		int length = paddingLength - target.length();
		for (int i = 0; i < length; i++) {
			sb.append(CommonConstant.STR_ZERO);
		}
		
		sb.append(target);
		return sb.toString();
	}
	
	public static String getSalt(String str) {
		return CommonConstant.MD5_SALT + str;
	}
}
