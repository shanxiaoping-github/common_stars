package com.platform.advertising.ui;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetMessageListClient;
import com.platform.advertising.ui.adapter.MessageAdapter;
import com.platform.advertising.ui.data.MessageData;
import com.platform.advertising.util.SharedPreferencesUtil;

@LAYOUT(R.layout.activity_message)
public class MessageActivity extends MyBaseActivity implements OnClickListener {
	// listView
	@ID(value = R.id.listView)
	private ListView listView;
	// 消息适配器
	private MessageAdapter messageAdapter;
	

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
		listView.setAdapter(messageAdapter);
		// 返回
		setBackButton();
		// 加载
		loadData();

	}

	private void loadData() {
		
		final HttpgetMessageListClient client = new HttpgetMessageListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				if(client.getList() != null){
					messageAdapter.setList(client.getList());
					messageAdapter.notifyDataSetChanged();
				}
				return false;
			}
			
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		client.setPramas(new Object[] { SharedPreferencesUtil
				.getString("mobile") });
		client.submitRequest();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
	}
}
