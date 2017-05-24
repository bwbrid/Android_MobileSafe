package com.bwbrid.mobilesafe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil.Action;

public class Setup1Activity extends BaseSetupActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup1);

        // 次へボタン処理
        Button bt_next_page = (Button) findViewById(R.id.bt_next_page);
        bt_next_page.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next_page:
                gotoNextPage();
                break;
            default:
                break;
        }
    }

    @Override
    protected void gotoPrePage() {}

    @Override
    protected void gotoNextPage() {
        CommonUtil.gotoActivity(this, Setup2Activity.class, Action.NextActivity);
    }
}
