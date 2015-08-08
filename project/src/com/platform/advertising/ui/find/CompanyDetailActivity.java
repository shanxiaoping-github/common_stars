package com.platform.advertising.ui.find;

import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.ui.BaseActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.CompanyData;

/**
 * 公司详情
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.company_detail_layout)
public class CompanyDetailActivity extends BaseActivity implements
		OnClickListener {

	private ImageButton back;
	private TextView introduce;
	private TextView adress;
	private View msg;
	private View telphone;
	
	@RESOURE("CompanyData")
	private CompanyData companyData;
	
	private TextView title;

	@Override
	protected void layout() {
		
		// TODO Auto-generated method stub
		
		back = (ImageButton) findViewById(R.id.company_detail_back);
		title = (TextView) findViewById(R.id.company_detail_title);
		introduce = (TextView) findViewById(R.id.company_detail_introduce);
		adress = (TextView) findViewById(R.id.company_detail_adress);
		msg = findViewById(R.id.company_detail_msg_cotact);
		telphone = findViewById(R.id.company_detail_telphone);
		msg.setOnClickListener(this);
		telphone.setOnClickListener(this);
		findViewById(R.id.company_detail_back).setOnClickListener(this);

		title.setText(companyData.getCompanyName());
		introduce.setText(companyData.getIntroduce());
		adress.setText(companyData.getAddress());

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.company_detail_back:
			finishBase();
			break;
		case R.id.company_detail_msg_cotact:
			cotact();
			break;
		case R.id.company_detail_telphone:
			telphone();
			break;

		}
	}

	/**
	 * 联系
	 */
	private void cotact() {
		Uri uri = Uri.parse("smsto://"+companyData.getMobil());

		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

		intent.putExtra("sms_body", "send detail");

		startActivity(intent);
	}

	/**
	 * 电话
	 */
	private void telphone() {

		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ companyData.getPhone()));
		startActivity(intent);

	}

}
