package com.bwbrid.mobilesafe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.bwbrid.mobilesafe.activity.HomeActivity;
import com.bwbrid.mobilesafe.bean.UpdateBean;
import com.bwbrid.mobilesafe.commcon.CommonConstant;
import com.bwbrid.mobilesafe.utils.LogUtil;
import com.bwbrid.mobilesafe.utils.StreamUtil;
import com.bwbrid.mobilesafe.utils.ToastUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;

public class SplashActivity extends Activity {

	/**
	 * Splash画面のトータル待ち時間
	 */
	private final static long SLEEP_TIME = 3000;

	private String tag = "SplashActivity";
	private Context mContext;

	private TextView tv_version_name;
	private int mLocalVersionCode;

	private UpdateBean mUpdateBean;

	private Handler mHandle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CommonConstant.UPDATE_VERSION:
				showUpdateDialog();
				break;
			case CommonConstant.ENTER_HOME:
				enterHome();
				break;
			case CommonConstant.ERROR_URL:
				ToastUtil.debugShow("URLエラー");
				enterHome();
				break;
			case CommonConstant.ERROR_IO:
				ToastUtil.debugShow("IOエラー");
				enterHome();
				break;
			case CommonConstant.ERROR_JSON:
				ToastUtil.debugShow("JSON解析に失敗した");
				enterHome();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		mContext = this;

		initUI();
		initData();
	}

	/**
	 * メイン画面へ遷移
	 */
	protected void enterHome() {
		Intent intent = new Intent(mContext, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	protected void showUpdateDialog() {
		if (mUpdateBean == null) {
			ToastUtil.show(getString(R.string.error_update_failure));
			enterHome();
			return;
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle(R.string.version_update);
		builder.setMessage(mUpdateBean.getVersionDes());

		// キャンセル処理
		builder.setNegativeButton(R.string.button_update_cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						enterHome();
					}
				});

		// アップデート処理
		builder.setPositiveButton(R.string.button_update_confirm,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						downloadApk();
					}
				});

		// 戻るボタンを押下処理
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				enterHome();
			}
		});

		builder.show();
	}

	/**
	 * アップデート処理(APKダウンロード及びインストール)
	 */
	protected void downloadApk() {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {

			HttpUtils httpUtils = new HttpUtils();
			LogUtils.d("downloadUrl = " + mUpdateBean.getDownloadUrl());
			LogUtils.d("savePath = " + CommonConstant.DOWNLOAD_SAVE_PATH);

			httpUtils.download(mUpdateBean.getDownloadUrl(),
					CommonConstant.DOWNLOAD_SAVE_PATH,
					new RequestCallBack<File>() {

						@Override
						public void onSuccess(ResponseInfo<File> arg0) {
							LogUtils.d("ダウンロードに成功した。");
							installApk(arg0.result);
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							LogUtils.d("ダウンロードに失敗した。");
						}

						@Override
						public void onStart() {
							LogUtils.d("ダウンロード開始した");
							super.onStart();
						}

						@Override
						public void onLoading(long total, long current,
								boolean isUploading) {
							LogUtils.d("ダウンロード中");
							LogUtils.d("total = " + total);
							LogUtils.d("current = " + current);
							LogUtils.d("isUploading = " + isUploading);
							super.onLoading(total, current, isUploading);
						}
					});

		} else {
			ToastUtil.show(R.string.sdcard_not_mounted);
		}
	}

	/**
	 * インストールAPK
	 * 
	 * @param result
	 */
	protected void installApk(File file) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		// startActivity(intent);
		startActivityForResult(intent, 0);
	}

	/**
	 * APKインストール時、取消してもHOME画面へ遷移
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		enterHome();
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 画面初期処理
	 */
	private void initUI() {
		tv_version_name = (TextView) findViewById(R.id.tv_version_name);

		mLocalVersionCode = getVersionCode();
		checkVersion();

	}

	/**
	 * 画面の表示データ初期処理
	 */
	private void initData() {
		tv_version_name.setText(getString(R.string.version_name)
				+ getVersionName());
	}

	/**
	 * バージョン名を取得
	 * 
	 * @return versionName
	 */
	private String getVersionName() {
		PackageInfo packageInfo = getPackageInfo(0);
		return packageInfo.versionName;
	}

	/**
	 * バージョンコードを取得
	 * 
	 * @return versionName
	 */
	private int getVersionCode() {
		PackageInfo packageInfo = getPackageInfo(0);
		return packageInfo.versionCode;
	}

	/**
	 * サーバーに最新のバージョンがあるかどうかをチェック
	 */
	private void checkVersion() {
		new Thread() {
			public void run() {

				Message msg = Message.obtain();
				long startTime = System.currentTimeMillis();

				try {
					URL url = new URL(CommonConstant.SERVER_UPDATE_URL);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();

					// リクエストオプション設定
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					// デフォルトのため、設定不要
					// connection.setRequestMethod("GET");

					if (connection.getResponseCode() == CommonConstant.HTTP_SUCCESS_CODE) {
						InputStream inputStream = connection.getInputStream();
						String json = StreamUtil.StreamToString(inputStream);
						LogUtil.d(tag, json);

						JSONObject jsonObject = new JSONObject(json);
						mUpdateBean = new UpdateBean();
						mUpdateBean.setVersionName(jsonObject
								.getString("versionName"));
						mUpdateBean.setVersionDes(jsonObject
								.getString("versionDes"));
						mUpdateBean.setVersionCode(jsonObject
								.getString("versionCode"));
						mUpdateBean.setDownloadUrl(jsonObject
								.getString("downloadUrl"));

						LogUtil.d(tag, mUpdateBean.getVersionName());
						LogUtil.d(tag, mUpdateBean.getVersionDes());
						LogUtil.d(tag, mUpdateBean.getVersionCode());
						LogUtil.d(tag, mUpdateBean.getDownloadUrl());

						if (mLocalVersionCode < Integer.valueOf(mUpdateBean
								.getVersionCode())) {
							msg.what = CommonConstant.UPDATE_VERSION;
						} else {
							msg.what = CommonConstant.ENTER_HOME;
						}
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
					msg.what = CommonConstant.ERROR_URL;
				} catch (IOException e) {
					e.printStackTrace();
					msg.what = CommonConstant.ERROR_IO;
				} catch (JSONException e) {
					e.printStackTrace();
					msg.what = CommonConstant.ERROR_JSON;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					msg.what = CommonConstant.ERROR_NUMBER_FORMAT;
				} finally {
					long endTime = System.currentTimeMillis();
					sleepTime(endTime - startTime);
					mHandle.sendMessage(msg);
				}
			}
		}.start();
	}

	/**
	 * Splash画面での待ち時間
	 * 
	 * @param time
	 *            時間
	 */
	private void sleepTime(long time) {
		if (time < SLEEP_TIME) {
			SystemClock.sleep(SLEEP_TIME - time);
		}
	}

	/**
	 * packageInfoオブジェクトを取得
	 * 
	 * @param flags
	 *            "Additional option flags"
	 * @return packageInfoオブジェクト
	 */
	private PackageInfo getPackageInfo(int flags) {
		PackageManager pm = getPackageManager();
		PackageInfo packageInfo = null;
		try {
			packageInfo = pm.getPackageInfo(getPackageName(), flags);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return packageInfo;
	}
}