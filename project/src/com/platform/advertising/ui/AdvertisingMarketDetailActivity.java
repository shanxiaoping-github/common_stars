package com.platform.advertising.ui;

import java.util.ArrayList;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetAdvertisementTopicListClient;
import com.platform.advertising.ui.data.AdverTitleData;
import com.platform.advertising.ui.data.AdvertisingData;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.advertising_market_detail_layout)
public class AdvertisingMarketDetailActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value = R.id.tvTitle)
	private TextView tvTitle;

	@ID(value = R.id.content)
	private TextView content;

	@ID(value = R.id.startAnswer, isBindListener = true)
	private View startAnswer;

	@RESOURE("AdvertisingData")
	private AdvertisingData adverData;

	private ArrayList<AdverTitleData> adverTitleList;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.advertising_market_detail_layout);
		setBackButton();
		tvTitle.setText(adverData.getTitle());
		content.setText(adverData.getContent());
		loadData();

	}

	/**
	 * 加载数据
	 */
	private void loadData() {
		final HttpgetAdvertisementTopicListClient client = new HttpgetAdvertisementTopicListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				if (client.getList() != null) {
					adverTitleList = client.getList();
				}
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		client.setPramas(new Object[] { adverData.getId() });
		client.submitRequest();

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startAnswer:
			startAnswer();

			break;
		}
	}

	/**
	 * 开始答题
	 */
	private void startAnswer() {
		if (adverTitleList != null) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("AdvertisingData", adverData);
			bundle.putSerializable("adverTitleList", adverTitleList);
			openActivity(AnswerStartActivity.class, bundle);
			finishBase();
		}else{
			showShortToast("没有题目可答");
		}

	}

}
