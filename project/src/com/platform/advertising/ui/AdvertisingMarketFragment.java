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

import com.platform.advertising.R;
import com.platform.advertising.http.HttpgetAdvertisementListClient;
import com.platform.advertising.ui.adapter.AdvertisingAdapter;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.ui.data.UploadDataActivity;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.advertising_market_layout)

public class AdvertisingMarketFragment extends BaseFragment implements
		OnClickListener {
	@ID(value = R.id.listView)
	private ListView listView;
	
	@ID(value = R.id.business_regist,isBindListener = true)
	private ImageView businessRegist;
	
	

	private AdvertisingAdapter adapter;

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
		listView.setAdapter(adapter);
		loadData();
		return super.layout(inflater);
	}

	private void loadData() {
		final HttpgetAdvertisementListClient client = new HttpgetAdvertisementListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				if (client.getList() != null) {
					adapter.setList(client.getList());
					adapter.notifyDataSetChanged();
				}
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				return false;
			}
		});
		// client.setPramas(new Object[]{
		// pageNum,
		// pageSize,
		// districtId,
		// provinceId,
		// cityId,
		// keyword
		// });
		ShowUtil.openHttpDialog("加载中...");
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

}
