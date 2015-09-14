package com.platform.advertising.ui;
import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.platform.advertising.R;
import com.platform.advertising.http.HttpgetAdvertisementListClient;
import com.platform.advertising.ui.adapter.AdvertisingAdapter;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.util.ShowUtil;
/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.advertising_market_layout)

public class AdvertisingMarketFragment extends BaseFragment implements
		OnClickListener,OnRefreshListener<ListView>,OnLastItemVisibleListener {
	@ID(value = R.id.listView)
	private PullToRefreshListView listView;
	
	@ID(value = R.id.business_regist,isBindListener = true)
	private ImageView businessRegist;
	
	private AdvertisingAdapter adapter;
	
	private int currentPage;

	@Override
	public View layout(LayoutInflater inflater) {
		// TODO Auto-generated method stub

		adapter = new AdvertisingAdapter();
		adapter.setContext(getContext());
		adapter.setListener(new AdapterItemListener() {
			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				AdvertisingData data = (AdvertisingData) objects[0];
				Bundle bundle = new Bundle();
				bundle.putSerializable("AdvertisingData", data);
				((BaseActivity)getContext()).openActivity(AdvertisingMarketDetailActivity.class, bundle);
				return false;
			}
		});
		listView.getRefreshableView().setAdapter(adapter);
		listView.setOnRefreshListener(this);
		listView.setOnLastItemVisibleListener(this);
		currentPage=1;
		loadData(currentPage,0);
		return super.layout(inflater);
	}

	private void loadData(int page,final int state) {
		final HttpgetAdvertisementListClient client = new HttpgetAdvertisementListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				if (client.getList() != null) {
					if(state==0){
						adapter.setList(client.getList());
					}else if(state==1){
						adapter.getList().addAll(client.getList());
						currentPage++;
					}
					adapter.notifyDataSetChanged();
				}else if(state==1){
					sxp.android.framework.util.ShowUtil.showShortToast(getContext(),"没有更多数据");
				}
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				return false;
			}
		});
		if(state==1){
			ShowUtil.openHttpDialog("加载中...");
		}
		client.setPramas(new Object[]{
				page,10
		});
		client.submitRequest();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		super.onClick(arg0);
		switch (arg0.getId()) {
		case R.id.business_regist:
			((BaseActivity) getActivity())
			.openActivity(UploadDataActivity.class);
			break;
		}
	}

	@Override
	public void onLastItemVisible() {
		// TODO Auto-generated method stub
		int nextPage = currentPage+1;
		loadData(nextPage,1);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		currentPage=1;
		loadData(currentPage,0);
	}

}
