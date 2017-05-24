package com.bwbrid.mobilesafe.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bwbrid.mobilesafe.R;

public class ContactListActivity extends BaseActivity {

	private static final String CONTENT_URL_ID = "content://com.android.contacts/raw_contacts";
	private static final String CONTENT_URL_DATA = "content://com.android.contacts/data";
	private static final String MIME_TYPE_NAME = "vnd.android.cursor.item/name";
	
	private ListView activity_contact_list;
	private List<Map<String,String>> contactList;

	@SuppressLint("HandlerLeak") 
	private Handler mHandle = new Handler() {
		
		@SuppressWarnings("unchecked")
		public void handleMessage(Message msg) {
			
			Object object = msg.obj;
			contactList =  (List<Map<String, String>>) object;
			activity_contact_list.setAdapter(new MyAdapter());
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		
		activity_contact_list = (ListView) findViewById(R.id.lv_contact_list);
		activity_contact_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Map<String,String> contactDataMap = contactList.get(position);
				Intent intent = new Intent();
				intent.putExtra("phone", contactDataMap.get("phone"));
				setResult(RESULT_OK, intent);
				finish();
			}
		});
		
		getContactList();
	}
	
	private void getContactList() {
		
		new Thread() {
			@Override
			public void run() {
				
				// データベースから連絡帳のIDを取得
				ContentResolver resolver = getContentResolver();
				Cursor cursor = resolver.query(
						Uri.parse(CONTENT_URL_ID), new String[]{"contact_id"}, null, null, null);
				
				List<Map<String,String>> list = new ArrayList<Map<String, String>>();
				
				while (cursor.moveToNext()) {
					String id = cursor.getString(0);
					
					// 連絡帳のIDから連絡帳のデータを取得
					Cursor dataCursor = resolver.query(Uri.parse(CONTENT_URL_DATA),
							new String[]{"data1", "mimetype"},
							"raw_contact_id = ?",
							new String[]{id}, null);
					
					Map<String, String> contactMap = new HashMap<String, String>();
					
					while (dataCursor.moveToNext()) {
						
						if (MIME_TYPE_NAME.equals(dataCursor.getString(1))) {
							contactMap.put("name", dataCursor.getString(0));
						} else {
							contactMap.put("phone", dataCursor.getString(0));
						}
					}
					
					list.add(contactMap);
					dataCursor.close();
				}
				cursor.close();
				
				// データをメインスレートに転送
				Message msg = Message.obtain();
				msg.obj = list;
				mHandle.sendMessage(msg);
			}
		}.start();
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return contactList.size();
		}

		@Override
		public Object getItem(int position) {
			return contactList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = null;
			if (convertView == null) {
				view = View.inflate(getApplicationContext(), R.layout.listview_contact_item, null);
			} else {
				view = convertView;
			}
			
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
			
			tv_name.setText(contactList.get(position).get("name"));
			tv_phone.setText(contactList.get(position).get("phone"));
			
			return view;
		}
		
	}
}
