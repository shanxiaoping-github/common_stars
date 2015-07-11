package com.platform.advertising.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.platform.advertising.R;
import com.platform.advertising.ui.find.CompanyDetailActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;

import sxp.android.framework.adapter.MapAdapter;
import sxp.android.framework.adapter.MapAdapter.ItemHandleCallBack;
import sxp.android.framework.adapter.MapAdapter.ViewHolder;
import sxp.android.framework.ui.BaseActivity;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
public class AdvertisingMarketActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener {
	private ListView listView;
	private MapAdapter adapter;
	private List<Map<String,String>> data = new ArrayList<Map<String,String>>();

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.advertising_market_layout);
		setBackButton();
		
		Map<String,String> map1 = new HashMap<String, String>();
		map1.put("name", "北京时代华擎有限公司");
		map1.put("number", "1231245");
		data.add(map1);
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("name", "北京时代华擎有限公司");
		map2.put("number", "1231245");
		data.add(map2);
		
		adapter = new MapAdapter(getApplicationContext(), data, new ItemHandleCallBack() {
			
			public void handle(ViewHolder holder, Map<String, String> item, int position) {
				holder.tvs.get(0).setText(item.get("name"));
				holder.tvs.get(1).setText(item.get("number"));
			}
		}, R.layout.advertising_market_layout_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
		loadData();
	}
	
	private void loadData(){
		send(new BaseThreadCallBack() {
			
			public String sendData() throws Exception {
				params.put("mobile", SharedPreferencesUtil.getString("mobile"));
				params.put("pageSize", "10");
				params.put("pageNum", "1");
				return HttpUtil.post("getAdvertisingList", params);
			}
			
			public void handleSuccess(String result) throws Exception {
				closeProgressDialog();
				
			}
			
			public void handleError(String errorMessage) {
				closeProgressDialog();
				showLongToast(errorMessage);
			}
			
			public void handleEmpty() {
				showProgressDialog("正在获取广告信息...");
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regist_back:
			finishBase();
			break;

		}
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("name", data.get(position).get("name"));
		openActivity(AdvertisingMarketDetailActivity.class, bundle);
	}
}
