package com.platform.advertising.ui;

import org.json.JSONObject;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.platform.advertising.R;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseActivity.BaseThreadCallBack;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
public class LocationActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_location);
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
