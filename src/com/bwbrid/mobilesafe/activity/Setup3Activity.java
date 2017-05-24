package com.bwbrid.mobilesafe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CheckUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.utils.ToastUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil.Action;

public class Setup3Activity extends BaseSetupActivity implements OnClickListener {

    private EditText et_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);

        initUI();
    }

    /**
     * 画面の初期処理
     */
    private void initUI() {

        et_phone_number = (EditText) findViewById(R.id.et_phone_number);

        Button bt_previous_page = (Button) findViewById(R.id.bt_previous_page);
        Button bt_next_page = (Button) findViewById(R.id.bt_next_page);
        Button bt_select_contact = (Button) findViewById(R.id.bt_select_contact);

        bt_select_contact.setOnClickListener(this);
        bt_previous_page.setOnClickListener(this);
        bt_next_page.setOnClickListener(this);

        String number = SpUtil.getString(SpConstant.SETUP_PHONE_NUMBER, CommonConstant.BLANK);
        setPhoneNumber(number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_previous_page:
                gotoPrePage();
                break;

            case R.id.bt_next_page:
                gotoNextPage();
                break;

            case R.id.bt_select_contact:
                Intent intent = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivityForResult(intent, 1);

            default:
                break;
        }
    }
    
    @Override
    protected void gotoPrePage() {
        CommonUtil.gotoActivity(this, Setup2Activity.class, Action.PreActivity);
    }
    
    @Override
    protected void gotoNextPage() {

        String phoneNumber = et_phone_number.getText().toString().trim();

        if (CheckUtil.isEmpty(phoneNumber)) {
            ToastUtil.show(R.string.error_phone_number);
            return;
        }

        SpUtil.putString(SpConstant.SETUP_PHONE_NUMBER, phoneNumber);
        CommonUtil.gotoActivity(this, Setup4Activity.class, Action.NextActivity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String phoneNumber = data.getStringExtra("phone").replace("-", CommonConstant.BLANK);
            setPhoneNumber(phoneNumber);
        }
    }

    /**
     * 画面のテキストボックス値設定
     * 
     * @param number
     */
    private void setPhoneNumber(String number) {
        et_phone_number.setText(number);
        et_phone_number.requestFocus();
        et_phone_number.setSelection(number.length());
    }
}
