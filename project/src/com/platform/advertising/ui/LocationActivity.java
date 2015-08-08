package com.platform.advertising.ui;

import sxp.android.framework.annotation.LAYOUT;
import android.view.View;
import android.view.View.OnClickListener;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
@LAYOUT(R.layout.activity_location)
public class LocationActivity extends MyBaseActivity implements OnClickListener {

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		//setContentView(R.layout.activity_location);
		setBackButton();
		findViewById(R.id.lLocation).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lLocation: // 确认
			break;
		}
	}

}
