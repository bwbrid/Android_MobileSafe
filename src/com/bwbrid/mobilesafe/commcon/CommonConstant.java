package com.bwbrid.mobilesafe.commcon;

import java.io.File;

import android.os.Environment;

public class CommonConstant {

	/**
	 * 最新バージョンの更新URL
	 */
	public final static String SERVER_UPDATE_URL = "http://android.bwbrid.com/update.json";
	
	/**
	 * APK名
	 */
	public final static String APK_NAME = "MobileSafe.apk";
	
	/**
	 * ダウンロードしたファイルの保存パス
	 */
	// public final static String DOWNLOAD_SAVE_PATH = Environment.getDownloadCacheDirectory() + File.separator + CommonConstant.APK_NAME;
	public final static String DOWNLOAD_SAVE_PATH = Environment.getExternalStorageDirectory() + File.separator + CommonConstant.APK_NAME;
	
	/**
	 * HTTPのリクエストコード(200)
	 */
	public final static int HTTP_SUCCESS_CODE = 200;
	
	/**
	 * カスタマイズスコード: バージョン更新処理
	 */
	public final static int UPDATE_VERSION = 10;
	
	/**
	 * カスタマイズスコード: バージョン更新処理
	 */
	public final static int ENTER_HOME = 11;
	
	/**
	 * カスタマイズスコード: MalformedURLException
	 */
	public final static int ERROR_URL = 101;
	
	/**
	 * カスタマイズスコード: IOException
	 */
	public final static int ERROR_IO = 102;
	
	/**
	 * カスタマイズスコード: JSONException
	 */
	public final static int ERROR_JSON = 103;
	
	/**
	 * カスタマイズスコード: NumberFormatException
	 */
	public final static int ERROR_NUMBER_FORMAT = 104;
}
