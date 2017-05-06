package com.bwbrid.mobilesafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwbrid.mobilesafe.MyApplication;
import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CheckUtil;
import com.bwbrid.mobilesafe.utils.SpUtil;

public class HomeActivity extends Activity {

	private Context mContext = MyApplication.getContext();
	
	private GridView gv_home;
	
	private SparseIntArray mHomeAppArray = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		initUI();
		initData();
	}

	private void initData() {
		
		mHomeAppArray = new SparseIntArray();
		
		// 機能一覧データ設定
		mHomeAppArray.put(R.string.home_module_safe, R.drawable.home_safe);
		mHomeAppArray.put(R.string.home_module_callmsgsafe, R.drawable.home_apps);
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
		if (CheckUtil.isEmpty(pwd)) {
			showSetPwdDialog();
		} else {
			showPwdDialog();
		}
	}

	/**
	 * 初期パスワード設定
	 */
	private void showSetPwdDialog() {
		Builder builder = new AlertDialog.Builder(this);
		AlertDialog dialog = builder.create();
		View view = View.inflate(mContext, R.layout.dialog_set_pwd, null);
		dialog.setView(view);
		dialog.show();
	}
	
	private void showPwdDialog() {
		// TODO Auto-generated method stub
		
	}

	private void initUI() {
		gv_home = (GridView) findViewById(R.id.gv_home);
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
