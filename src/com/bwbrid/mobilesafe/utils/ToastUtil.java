package com.bwbrid.mobilesafe.utils;

import com.bwbrid.mobilesafe.MyApplication;

import android.widget.Toast;

public class ToastUtil {

	public static final boolean DEBUG = true;

	public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
	public static final int LENGTH_LONG = Toast.LENGTH_LONG;

	/**
	 * Make a standard toast that just contains a text view.
	 * 
	 * @param msg
	 *            The text to show. Can be formatted text.
	 */
	public static void show(String msg) {
		Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * Make a standard toast that just contains a text view with the text from a
	 * resource.
	 * 
	 * @param resId
	 *            The resource id of the string resource to use. Can be
	 *            formatted text.
	 */
	public static void show(int resId) {
		Toast.makeText(MyApplication.getContext(), resId, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * �@�\�����Ԑݒ�\
	 * 
	 * @param msg
	 *            ���b�Z�[�W���e
	 * @param duration
	 *            �\�����Ԃ̊Ԋu�i LENGTH_SHORT or LENGTH_LONG�j
	 */
	public static void show(int resId, int duration) {
		Toast.makeText(MyApplication.getContext(), resId, duration).show();
	}

	/**
	 * �@�\�����Ԑݒ�\
	 * 
	 * @param msg
	 *            ���b�Z�[�W���e
	 * @param duration
	 *            �\�����Ԃ̊Ԋu�i LENGTH_SHORT or LENGTH_LONG�j
	 */
	public static void show(String msg, int duration) {
		Toast.makeText(MyApplication.getContext(), msg, duration).show();
	}

	/**
	 * �f�o�b�N�pToast
	 * 
	 * @param msg
	 *            ���b�Z�[�W���e
	 */
	public static void debugShow(String msg) {
		if (DEBUG) {
			Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT)
					.show();
		}
	}
}
