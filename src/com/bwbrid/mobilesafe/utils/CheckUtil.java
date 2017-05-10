package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.common.CommonConstant;


public class CheckUtil {

	/**
	 * 文字列がブランクかどうか
	 * @param str 対象文字列
	 * @return ブランクの場合、true
	 */
	public static boolean isEmpty(String str) {
		
		if (str == null || CommonConstant.BLANK.equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 文字列がブランク以外かどうか
	 * @param str 対象文字列
	 * @return　ブランク以外の場合、true
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
