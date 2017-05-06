package com.bwbrid.mobilesafe.common;

import java.io.File;

import android.os.Environment;

public class CommonConstant {

	/**
	 * �ŐV�o�[�W�����̍X�VURL
	 */
	public static final String SERVER_UPDATE_URL = "http://android.bwbrid.com/update.json";

	/**
	 * APK��
	 */
	public static final String APK_NAME = "MobileSafe.apk";

	/**
	 * NameSpace
	 */
	public static final String NAME_SPACE = "http://schemas.android.com/apk/res/com.bwbrid.mobilesafe";
	
	/**
	 * �_�E�����[�h�����t�@�C���̕ۑ��p�X
	 */
	// public final static String DOWNLOAD_SAVE_PATH = Environment.getDownloadCacheDirectory() + File.separator + CommonConstant.APK_NAME;
	public static final String DOWNLOAD_SAVE_PATH = Environment.getExternalStorageDirectory() + File.separator + CommonConstant.APK_NAME;

	/**
	 * HTTP�̃��N�G�X�g�R�[�h(200)
	 */
	public static final int HTTP_SUCCESS_CODE = 200;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: �o�[�W�����X�V����
	 */
	public static final int UPDATE_VERSION = 10;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: �o�[�W�����X�V����
	 */
	public static final int ENTER_HOME = 11;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: MalformedURLException
	 */
	public static final int ERROR_URL = 101;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: IOException
	 */
	public static final int ERROR_IO = 102;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: JSONException
	 */
	public static final int ERROR_JSON = 103;

	/**
	 * �J�X�^�}�C�Y�X�R�[�h: NumberFormatException
	 */
	public static final int ERROR_NUMBER_FORMAT = 104;
	
	/**
	 * �u�����N
	 */
	public static final String BLANK = "";
}
