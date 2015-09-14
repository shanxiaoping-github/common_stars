package com.platform.advertising.ui;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.platform.advertising.R;
import com.platform.advertising.http.HttpWithdrawaRecordClient;
import com.platform.advertising.ui.adapter.WithdrawaRecordAdapter;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;
/**
 * 提现记录
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.withdrawal_record_layout)
public class WithdrawalRecordActivity extends BaseActivity implements OnRefreshListener<ListView>,OnLastItemVisibleListener{
	@ID(value = R.id.back,isBindListener = true)
    private ImageButton back;
	
	@ID(value = R.id.listview)
	private PullToRefreshListView listView;
    
    private WithdrawaRecordAdapter withrecordAdapter;
    
    private int currentPage;
    
    @Override
    protected void layout() {
    	// TODO Auto-generated method stub
    	super.layout();
    	withrecordAdapter = new WithdrawaRecordAdapter();
    	withrecordAdapter.setContext(this);
    	listView.getRefreshableView().setAdapter(withrecordAdapter);
    	listView.setOnRefreshListener(this);
    	listView.setOnLastItemVisibleListener(this);
    	currentPage=1;
    	loadData(currentPage,0);
  
    }
    
    private void loadData(int page,final int state){
    	final HttpWithdrawaRecordClient client = new HttpWithdrawaRecordClient();
    	client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			@Override
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				showShortToast("获取失败");

				return false;
			}
			
			@Override
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				if(client.getList()!=null){
					if(state==0){
						withrecordAdapter.setList(client.getList());
					}else if(state==1){
						withrecordAdapter.getList().addAll(client.getList());
						currentPage++;
					}
					withrecordAdapter.notifyDataSetChanged();
					
				}else if(state==1){
					showShortToast("没有更多数据");

				}
				return false;
			}
			
			@Override
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				listView.onRefreshComplete();
				showShortToast("获取失败");
				return false;
			}
		});
    	client.setPramas(new Object[]{
    			page,"10",SharedPreferencesUtil.getString("mobile")
    			
    	});
    	if(state==1){
    		ShowUtil.openHttpDialog("获取中...");
    	}
    	client.submitRequest();
    	
    }
    
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	super.onClick(v);
    	switch (v.getId()) {
		case R.id.back:
			finishBase();
			break;
		}
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
		currentPage=1;
    	loadData(currentPage,0);
	}
}
