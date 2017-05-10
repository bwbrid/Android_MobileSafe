package com.bwbrid.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CheckUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.utils.ToastUtil;

public class HomeActivity extends Activity {

	private Context mContext = MyApplication.getContext();
	
	// private String tag = "HomeActivity";
	
	private GridView gv_home;
	
	private SparseIntArray mHomeAppArray = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		initUI();
		initData();
	}

	private void initUI() {
		gv_home = (GridView) findViewById(R.id.gv_home);
	}

	private void initData() {
		
		mHomeAppArray = new SparseIntArray();
		
		// 機能一覧データ設定
		mHomeAppArray.put(R.string.home_module_safe, R.drawable.home_safe);
		mHomeAppArray.put(R.string.home_module_callmsgsafe, R.drawable.home_callmsgsafe);
		mHomeAppArray.put(R.string.home_module_apps, R.drawable.home_apps);
		mHomeAppArray.put(R.string.home_module_taskmanager, R.drawable.home_taskmanager);
		mHomeAppArray.put(R.string.home_module_netmanager, R.drawable.home_netmanager);
		mHomeAppArray.put(R.string.home_module_trojan, R.drawable.home_trojan);
		mHomeAppArray.put(R.string.home_module_sysoptimize, R.drawable.home_sysoptimize);
		mHomeAppArray.put(R.string.home_module_tools, R.drawable.home_tools);
		mHomeAppArray.put(R.string.home_module_settings, R.drawable.home_settings);
		
		gv_home.setAdapter(new MyAdapter());
		
		gv_home.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					secutirySetting();
					break;
				case 8:
					Intent intent = new Intent(mContext, SettingActivity.class);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * セキュリティ設定画面前処理
	 */
	protected void secutirySetting() {
		String pwd = SpUtil.getString(SpConstant.SECURITY_PWD, CommonConstant.BLANK);
		showPasswordDialog(pwd);
	}

	/**
	 * パスワード設定
	 * @param pwd パスワード
	 */
	private void showPasswordDialog(final String pwd) {
		
		Builder builder = new AlertDialog.Builder(this);
		final AlertDialog dialog = builder.create();
		final int rid = CheckUtil.isEmpty(pwd) ?  R.layout.dialog_set_pwd : R.layout.dialog_confim_pwd;
		final View view = View.inflate(mContext, rid, null);
		dialog.setView(view);
		dialog.show();
		
		Button et_dialog_set_pwd = (Button) view.findViewById(R.id.bt_dialog_ok);
		Button bt_dialog_cancel = (Button) view.findViewById(R.id.bt_dialog_cancel);
		
		// 確認ボタン処理
		et_dialog_set_pwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String password = ((TextView) view.findViewById(R.id.et_dialog_set_pwd)).getText().toString();
				
				// パスワード初期設定画面
				if (rid == R.layout.dialog_set_pwd) {
					
					String confimPassword = ((TextView) view.findViewById(R.id.et_dialog_confim_pwd)).getText().toString();
					
					if (CheckUtil.isEmpty(password) || CheckUtil.isEmpty(confimPassword)) {
						ToastUtil.show(R.string.error_pwd_empty);
						return;
					}
					
					if (!password.equals(confimPassword)) {
						ToastUtil.show(R.string.error_confim_pwd, Toast.LENGTH_LONG);
						return;
					}
					
					// パスワード保存処理
					SpUtil.putString(SpConstant.SECURITY_PWD, CommonUtil.encoder(password));
				
				// パスワード入力認証画面
				} else {
					
					if (CheckUtil.isEmpty(password)) {
						ToastUtil.show(R.string.error_pwd_empty);
						return;
					}
					
					if (!pwd.equals(CommonUtil.encoder(password))) {
						ToastUtil.show(R.string.error_pwd_wrong);
						return;
					}
				}
				
				// セキュリティガイド画面へ遷移
				dialog.dismiss();
				Intent intent = new Intent(mContext, SetupOverActivity.class);
				startActivity(intent);
			}
		});
		
		// 取消ボタン処理
		bt_dialog_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mHomeAppArray.size();
		}

		@Override
		public Object getItem(int position) {
			return mHomeAppArray.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = null;
			if (convertView != null) {
				view = convertView;
			} else {
				view = View.inflate(mContext, R.layout.gridview_home_item, null);
			}
			
			TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
			ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			
			tv_title.setText(mHomeAppArray.keyAt(position));
			iv_icon.setBackgroundResource(mHomeAppArray.valueAt(position));
			
			
			return view;
		}
	}
}
