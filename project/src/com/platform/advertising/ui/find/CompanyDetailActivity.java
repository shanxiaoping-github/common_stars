package com.platform.advertising.ui.find;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.MathUtil;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.CompanyData;
import com.platform.advertising.util.ImageUtil;
import com.squareup.picasso.Picasso;

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
	
	@ID(value = R.id.company_detail_img)
	private ImageView show_img;
	
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
		if(companyData.getImageList()!=null){
			Picasso.with(this).load(ImageUtil.getImageUrl(companyData.getImageList().get(0).getImagePath())).centerCrop().placeholder(R.drawable.show_img).error(R.drawable.show_img).resize(SXPApplication.getWindowWidth(this), MathUtil.diptopx(this,200)).into(show_img);
		}else{
			Picasso.with(this).load(ImageUtil.getImageUrl("")).centerCrop().placeholder(R.drawable.show_img).error(R.drawable.show_img).resize(SXPApplication.getWindowWidth(this), MathUtil.diptopx(this,200)).into(show_img);
		}
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
