package com.bwbrid.mobilesafe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FocusTextView extends TextView {

	public FocusTextView(Context context) {
		super(context);
	}

	public FocusTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FocusTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	// フォーカス取得処理
	@Override
	public boolean isFocused() {
		return true;
	}
}
