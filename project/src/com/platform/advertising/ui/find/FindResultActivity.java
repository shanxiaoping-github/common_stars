package com.platform.advertising.ui.find;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetAdvertisementListClient;
import com.platform.advertising.http.HttpgetCompanyClient;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.ui.data.CompanyData;
import com.platform.advertising.util.ShowUtil;

/**
 * 查找结果
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.find_result_layout)
public class FindResultActivity extends MyBaseActivity implements OnClickListener {

	private ImageButton back;
	private ListView resultListView;
	private CompanyAdapter companyAdapter;
	
	private int pageSize = 10;
	private int pageNum = 1;
	
	@RESOURE("provinceId")
	private String provinceId;
	@RESOURE("cityId")
	private String cityId;
	@RESOURE("districtId")
	private String districtId;
	@RESOURE("keyword")
	private String keyword;
	

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		
		back = (ImageButton) findViewById(R.id.find_result_back);
		resultListView = (ListView) findViewById(R.id.find_result_listview);
		companyAdapter = new CompanyAdapter();
		companyAdapter.setContext(this);
		companyAdapter.setListener(new AdapterItemListener() {

		
			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				CompanyData companyData = (CompanyData)objects[0];
				Bundle bundle = new Bundle();
				bundle.putSerializable("CompanyData",companyData);
				openActivity(CompanyDetailActivity.class, bundle);
				return false;
			}
		});
		resultListView.setAdapter(companyAdapter);
		back.setOnClickListener(this);
		loadData();
	}

	private void loadData(){
		
		final HttpgetCompanyClient client = new HttpgetCompanyClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}
			
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				if(client.getList()!=null){
					companyAdapter.setList(client.getList());
					companyAdapter.notifyDataSetChanged();
				}
				return false;
			}
			
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}
		});
		client.setPramas(new Object[]{
				"1","10"
				//districtId,provinceId,cityId,keyword
		});
		
		ShowUtil.openHttpDialog("获取中...");
		client.submitRequest();
//		final HttpgetAdvertisementListClient client = new HttpgetAdvertisementListClient();
//		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
//			
//			public boolean onTimeout() {
//				// TODO Auto-generated method stub
//				ShowUtil.closeHttpDialog();
//				return false;
//			}
//			
//			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
//				// TODO Auto-generated method stub
//				ShowUtil.closeHttpDialog();
//				if(client.getList()!=null){
//					companyAdapter.setList(client.getList());
//					companyAdapter.notifyDataSetChanged();
//				}
//				return false;
//			}
//			
//			public boolean onEmpty() {
//				// TODO Auto-generated method stub
//				ShowUtil.closeHttpDialog();
//				return false;
//			}
//		});
////		client.setPramas(new Object[]{
////				pageNum,
////				pageSize,
////				districtId,
////				provinceId,
////				cityId,
////				keyword
////		});
//		ShowUtil.openHttpDialog("加载中...");
//		client.submitRequest();
		
	}
	
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_result_back:
			finishBase();
			break;

		}
	}

}
