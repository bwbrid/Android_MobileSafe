package com.bwbrid.mobilesafe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil.Action;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.utils.ToastUtil;

public class Setup4Activity extends BaseSetupActivity implements OnClickListener,
        OnCheckedChangeListener {

    private CheckBox cb_security;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);

        initUI();
    }

    /**
     * 画面の初期処理
     */
    private void initUI() {

        Button bt_previous_page = (Button) findViewById(R.id.bt_previous_page);
        Button bt_finish = (Button) findViewById(R.id.bt_finish);
        cb_security = (CheckBox) findViewById(R.id.cb_security);

        bt_previous_page.setOnClickListener(this);
        bt_finish.setOnClickListener(this);
        cb_security.setOnCheckedChangeListener(this);

        // チェックボックスの初期設定
        boolean initState = SpUtil.getBoolean(SpConstant.SETUP_CHECK_SECURITY, false);
        cb_security.setChecked(initState);
        setCheckBoxText(initState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_previous_page:
                gotoPrePage();
                break;

            case R.id.bt_finish:
                gotoNextPage();
                break;

            default:
                break;
        }
    }

    /**
     * チェックボタンのチェック動作処理
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCheckBoxText(isChecked);
        SpUtil.putBoolean(SpConstant.SETUP_CHECK_SECURITY, isChecked);
    }

    @Override
    protected void gotoPrePage() {
        CommonUtil.gotoActivity(this, Setup3Activity.class, Action.PreActivity);        
    }
    
    /**
     * 次へボタンの処理
     */
    protected void gotoNextPage() {

        if (!cb_security.isChecked()) {
            ToastUtil.show(R.string.error_check_security);
            return;
        }

        // 設定完了のため、フラグを設定
        SpUtil.putBoolean(SpConstant.SETUP_FINISH_FLG, true);
        CommonUtil.gotoActivity(this, SetupOverActivity.class, Action.NextActivity);
    }

    /**
     * チェックボックスのラベル変更処理
     * 
     * @param isChecked
     */
    private void setCheckBoxText(boolean isChecked) {
        cb_security.setText(isChecked ? R.string.setup4_security_on : R.string.setup4_security_off);
    }
}
