package com.platform.advertising.ui;

import sxp.android.framework.ui.BaseActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.platform.advertising.R;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
public class AdvertisingMarketDetailActivity extends BaseActivity implements
		OnClickListener {
	private TextView tvTitle;
	private View startAnswer;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.advertising_market_detail_layout);
		setBackButton();

		tvTitle.setText(getIntent().getExtras().getString("name"));
		startAnswer.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startAnswer:
			Bundle bundle = new Bundle();
			bundle.putSerializable("name", tvTitle.getText().toString());
			openActivity(AnswerStartActivity.class, bundle);
			break;
		}
	}

}
