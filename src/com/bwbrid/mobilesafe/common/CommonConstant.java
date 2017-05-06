package com.bwbrid.mobilesafe.common;

import java.io.File;

import android.os.Environment;

public class CommonConstant {

	/**
	 * 最新バージョンの更新URL
	 */
	public static final String SERVER_UPDATE_URL = "http://android.bwbrid.com/update.json";

	/**
	 * APK名
	 */
	public static final String APK_NAME = "MobileSafe.apk";

	/**
	 * NameSpace
	 */
	public static final String NAME_SPACE = "http://schemas.android.com/apk/res/com.bwbrid.mobilesafe";
	
	/**
	 * ダウンロードしたファイルの保存パス
	 */
	// public final static String DOWNLOAD_SAVE_PATH = Environment.getDownloadCacheDirectory() + File.separator + CommonConstant.APK_NAME;
	public static final String DOWNLOAD_SAVE_PATH = Environment.getExternalStorageDirectory() + File.separator + CommonConstant.APK_NAME;

	/**
	 * HTTPのリクエストコード(200)
	 */
	public static final int HTTP_SUCCESS_CODE = 200;

	/**
	 * カスタマイズスコード: バージョン更新処理
	 */
	public static final int UPDATE_VERSION = 10;

	/**
	 * カスタマイズスコード: バージョン更新処理
	 */
	public static final int ENTER_HOME = 11;

	/**
	 * カスタマイズスコード: MalformedURLException
	 */
	public static final int ERROR_URL = 101;

	/**
	 * カスタマイズスコード: IOException
	 */
	public static final int ERROR_IO = 102;

	/**
	 * カスタマイズスコード: JSONException
	 */
	public static final int ERROR_JSON = 103;

	/**
	 * カスタマイズスコード: NumberFormatException
	 */
	public static final int ERROR_NUMBER_FORMAT = 104;
	
	/**
	 * ブランク
	 */
	public static final String BLANK = "";
}
