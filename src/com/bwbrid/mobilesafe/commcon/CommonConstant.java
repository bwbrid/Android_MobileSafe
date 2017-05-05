package com.bwbrid.mobilesafe.commcon;

import java.io.File;

import android.os.Environment;

public class CommonConstant {

	/**
	 * �ŐV�o�[�W�����̍X�VURL
	 */
	public final static String SERVER_UPDATE_URL = "http://android.bwbrid.com/update.json";
	
	/**
	 * APK��
	 */
	public final static String APK_NAME = "MobileSafe.apk";
	
	/**
	 * �_�E�����[�h�����t�@�C���̕ۑ��p�X
	 */
	// public final static String DOWNLOAD_SAVE_PATH = Environment.getDownloadCacheDirectory() + File.separator + CommonConstant.APK_NAME;
	public final static String DOWNLOAD_SAVE_PATH = Environment.getExternalStorageDirectory() + File.separator + CommonConstant.APK_NAME;
	
	/**
	 * HTTP�̃��N�G�X�g�R�[�h(200)
	 */
	public final static int HTTP_SUCCESS_CODE = 200;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: �o�[�W�����X�V����
	 */
	public final static int UPDATE_VERSION = 10;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: �o�[�W�����X�V����
	 */
	public final static int ENTER_HOME = 11;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: MalformedURLException
	 */
	public final static int ERROR_URL = 101;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: IOException
	 */
	public final static int ERROR_IO = 102;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: JSONException
	 */
	public final static int ERROR_JSON = 103;
	
	/**
	 * �J�X�^�}�C�Y�X�R�[�h: NumberFormatException
	 */
	public final static int ERROR_NUMBER_FORMAT = 104;
}
