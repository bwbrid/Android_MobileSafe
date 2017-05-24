package com.bwbrid.mobilesafe.activity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class BaseSetupActivity extends BaseActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // ジェスチャイベント初期化
        createGestureDetector();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * ジェスチャインスタンス生成
     */
    private void createGestureDetector() {

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // 左から右へ移動(次へ)
                if (e1.getX() - e2.getX() > 0) {
                    gotoNextPage();
                } else {
                    gotoPrePage();
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    protected abstract void gotoPrePage();

    protected abstract void gotoNextPage();
}
