package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.MyApplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtil {

	private static SharedPreferences sp;
	private static final String FILE_NAME = "config";
	
	/**
	 * Set a boolean value in the preferences
	 * 
	 * @param key The name of the preference to modify
	 * @param value The new value for the preference.
	 */
	public static void putBoolean(String key, boolean value) {
		Editor edit = getSharedPreferences().edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	/**
	 * The new value for the preference.
	 * 
	 * @param key The name of the preference to retrieve.
	 * @param defValue Value to return if this preference does not exist.
	 * @return
	 * 		Returns the preference value if it exists, or defValue. 
	 * 		Throws ClassCastException if there is a preference with this name that is not a boolean.
	 */
	public static boolean getBoolean(String key, boolean defValue) {
		return getSharedPreferences().getBoolean(key, defValue);
	}
	
	/**
	 * Set a String value in the preferences
	 * 
	 * @param key The name of the preference to modify
	 * @param value The new value for the preference.
	 */
	public static void putString(String key, String value) {
		Editor edit = getSharedPreferences().edit();
		edit.putString(key, value);
		edit.commit();
	}
	
	/**
	 * The new value for the preference.
	 * 
	 * @param key The name of the preference to retrieve.
	 * @param defValue Value to return if this preference does not exist.
	 * @return
	 * 		Returns the preference value if it exists, or defValue. 
	 * 		Throws ClassCastException if there is a preference with this name that is not a String.
	 */
	public static String getString(String key, String defValue) {
		return getSharedPreferences().getString(key, defValue);
	}
	
	/**
	 * SharedPreferencesオブジェクトを取得
	 * @return SharedPreferences
	 */
	private static SharedPreferences getSharedPreferences() {
		if (sp == null) {
			sp = MyApplication.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		}
		return sp;
	}
}
