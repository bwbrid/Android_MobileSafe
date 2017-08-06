package com.bwbrid.mobilesafe.activity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.CommonConstant;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil.Action;
import com.bwbrid.mobilesafe.utils.SpUtil;
import com.bwbrid.mobilesafe.utils.ToastUtil;
import com.bwbrid.mobilesafe.view.SettingItewView;

public class Setup2Activity extends BaseSetupActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);

        initUI();
        setSimCardBind();
    }

    /**
     * 画面の初期処理
     */
    private void initUI() {

        Button bt_previous_page = (Button) findViewById(R.id.bt_previous_page);
        Button bt_next_page = (Button) findViewById(R.id.bt_next_page);

        bt_previous_page.setOnClickListener(this);
        bt_next_page.setOnClickListener(this);
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

            default:
                break;
        }
    }
    

    @Override
    protected void gotoPrePage() {
        CommonUtil.gotoActivity(this, Setup1Activity.class, Action.PreActivity);
    }

    @Override
    protected void gotoNextPage() {
        String simNumber = SpUtil.getString(SpConstant.SETUP_SIMCARD_NUMBER, CommonConstant.BLANK);

        if (CommonConstant.BLANK.equals(simNumber)) {
            ToastUtil.show(R.string.error_check_sim);
            return;
        }

        CommonUtil.gotoActivity(this, Setup3Activity.class, Action.NextActivity);        
    }

    /**
     * SIMカードバンド設定処理
     */
    private void setSimCardBind() {

        final SettingItewView setup2_bind_simcard =
                (SettingItewView) findViewById(R.id.setup2_bind_simcard);

        // SIMカード番号取得
        String simNumber = SpUtil.getString(SpConstant.SETUP_SIMCARD_NUMBER, CommonConstant.BLANK);

        // SIMカード番号によるチェックボックスの状態を設定（false: 未チェック, true: チェック）
        setup2_bind_simcard.setCheckState(CommonConstant.BLANK.equals(simNumber) ? false : true);

        // チェックボックスの選択イベント
        setup2_bind_simcard.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean isCheck = setup2_bind_simcard.isCheck();
                setup2_bind_simcard.setCheckState(!isCheck);

                // 未チェックの状態からチェックの状態へ変更時
                if (!isCheck) {

                    // SIMカード番号取得
                    TelephonyManager manager =
                            (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String simSerialNumber = manager.getSimSerialNumber();
                    // SpUtil.putString(SpConstant.SETUP_SIMCARD_NUMBER, simSerialNumber);
                    simSerialNumber = "89014103211118510720";
                    SpUtil.putString(SpConstant.SETUP_SIMCARD_NUMBER, simSerialNumber);

                } else {
                    // 未チェックの状態はSPから対象のSIMカード項目を削除
                    SpUtil.remove(SpConstant.SETUP_SIMCARD_NUMBER);
                }
            }
        });
    }
}
