package com.platform.advertising.ui;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.activity_messagedetail)
public class MessageDetailActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value=R.id.tvTitle)
	private TextView tvTitle;
	
	@ID(value=R.id.tvContent)
	private TextView tvContent;
	
	@ID(value = R.id.lReply)
	private View lReply;
	
	@ID(value = R.id.lPhone)
	private View  lPhone;
	
	private String msgId = null;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		//setContentView(R.layout.activity_messagedetail);
		setBackButton();

		msgId = getIntent().getExtras().getString("id");
		tvTitle.setText(getIntent().getExtras().getString("title"));
		tvContent.setText(getIntent().getExtras().getString("content"));
		lReply.setOnClickListener(this);
		lPhone.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lReply:
			Bundle bundle = new Bundle();
			bundle.putSerializable("originalId", msgId);
			openActivity(ContractActivity.class, bundle);
			break;
		}
	}

}
