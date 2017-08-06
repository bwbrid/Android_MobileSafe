package com.bwbrid.mobilesafe.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bwbrid.mobilesafe.R;
import com.bwbrid.mobilesafe.common.SpConstant;
import com.bwbrid.mobilesafe.utils.CommonUtil;
import com.bwbrid.mobilesafe.utils.CommonUtil.Action;
import com.bwbrid.mobilesafe.utils.SpUtil;

public class SetupOverActivity extends BaseActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_over);

        boolean isFinish = SpUtil.getBoolean(SpConstant.SETUP_FINISH_FLG, false);
        if (!isFinish) {
            CommonUtil.gotoActivity(this, Setup1Activity.class, Action.NextActivity);
        }
        
        initUI();
    }
    
    private void initUI() {
        TextView tv_edit = (TextView) findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                CommonUtil.gotoNextActivity(this, Setup1Activity.class, false);
                break;

            default:
                break;
        }
    }
}
