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

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetCompanyClient;
import com.platform.advertising.ui.data.CompanyData;
import com.platform.advertising.util.ShowUtil;

/**
 * 查找结果
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.find_result_layout)
public class FindResultActivity extends MyBaseActivity implements OnClickListener,OnRefreshListener<ListView>, OnLastItemVisibleListener{

	private ImageButton back;
	private PullToRefreshListView resultPullrefresh;
	private CompanyAdapter companyAdapter;
	
	
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
		resultPullrefresh = (PullToRefreshListView) findViewById(R.id.find_result_pullrefreshlistview);
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
		resultPullrefresh.getRefreshableView().setAdapter(companyAdapter);
		resultPullrefresh.setOnRefreshListener(this);
		resultPullrefresh.setOnLastItemVisibleListener(this);
		back.setOnClickListener(this);
		pageNum=1;
		loadData(pageNum,0);
	}
	//state 0刷新，1更多
	private void loadData(int page,final int state){
		
		final HttpgetCompanyClient client = new HttpgetCompanyClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener(){
			
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				resultPullrefresh.onRefreshComplete();
				return false;
			}
			
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				resultPullrefresh.onRefreshComplete();
				if(client.getList()!=null){
					if(state==0){
						companyAdapter.setList(client.getList());
					}else if(state==1){
						companyAdapter.getList().addAll(client.getList());
						pageNum++;
					}
					companyAdapter.notifyDataSetChanged();
					
				}else if(state==1){
					showShortToast("没有更多数据");
				}
				return false;
			}
			
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				resultPullrefresh.onRefreshComplete();
				return false;
			}
		});
		client.setPramas(new Object[]{
				page,"10",
				districtId,provinceId,cityId,keyword
		});
		if(state==1){
			ShowUtil.openHttpDialog("获取中...");
		}
		client.submitRequest();	
	}
	
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_result_back:
			finishBase();
			break;

		}
	}

	@Override
	public void onLastItemVisible() {
		// TODO Auto-generated method stub
		int nextPage = pageNum++;
		loadData(nextPage,1);
		
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		pageNum=1;
		loadData(pageNum,0);
		
	}

}
