package com.platform.advertising.ui;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.ShowUtil;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetMessageListClient;
import com.platform.advertising.ui.adapter.MessageAdapter;
import com.platform.advertising.ui.data.MessageData;
import com.platform.advertising.util.SharedPreferencesUtil;

@LAYOUT(R.layout.activity_message)
public class MessageActivity extends MyBaseActivity implements OnClickListener,OnRefreshListener<ListView>,OnLastItemVisibleListener {
	// listView
	@ID(value = R.id.listView)
	private PullToRefreshListView listView;
	// 消息适配器
	private MessageAdapter messageAdapter;
	
	private int currentPage;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.activity_message);
		messageAdapter = new MessageAdapter();
		messageAdapter.setContext(this);
		messageAdapter.setListener(new AdapterItemListener() {

			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				MessageData msg = (MessageData)objects[0];
				Bundle bundle = new Bundle();
				bundle.putSerializable("message",msg);
				openActivity(MessageDetailActivity.class,bundle);
				return false;
			}
		});
		listView.getRefreshableView().setAdapter(messageAdapter);
		listView.setOnRefreshListener(this);
		listView.setOnLastItemVisibleListener(this);
		// 返回
		setBackButton();
		// 加载
		currentPage = 1;
		loadData(currentPage,0);

	}

	private void loadData(int page,final int state) {
		
		final HttpgetMessageListClient client = new HttpgetMessageListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				com.platform.advertising.util.ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				return false;
			}
			
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				com.platform.advertising.util.ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				if(client.getList() != null){
					if(state==0){
					messageAdapter.setList(client.getList());
					}else if(state==1){
						messageAdapter.getList().addAll(client.getList());
						currentPage++;
					}
					messageAdapter.notifyDataSetChanged();
				}else if(state==1){
					ShowUtil.showShortToast(MessageActivity.this,"没有更多数据");
				}
				return false;
			}
			
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				com.platform.advertising.util.ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				return false;
			}
		});
		if(state==1){
			com.platform.advertising.util.ShowUtil.openHttpDialog("获取中...");
		}
		client.setPramas(new Object[] { SharedPreferencesUtil
				.getString("mobile"),page,"10" });
		client.submitRequest();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
	}

	@Override
	public void onLastItemVisible() {
		// TODO Auto-generated method stub
		int nextPage = currentPage++;
		loadData(nextPage,1);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		currentPage = 1;
		loadData(currentPage,0);
	}
}
