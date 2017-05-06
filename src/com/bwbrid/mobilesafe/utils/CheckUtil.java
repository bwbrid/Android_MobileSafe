package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.common.CommonConstant;


public class CheckUtil {

	/**
	 * �����񂪃u�����N���ǂ���
	 * @param str �Ώە�����
	 * @return �u�����N�̏ꍇ�Atrue
	 */
	public static boolean isEmpty(String str) {
		
		if (str == null && CommonConstant.BLANK.equals(str)) {
			return false;
		}
		return true;
	}
	
	/**
	 * �����񂪃u�����N�ȊO���ǂ���
	 * @param str �Ώە�����
	 * @return�@�u�����N�ȊO�̏ꍇ�Atrue
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
