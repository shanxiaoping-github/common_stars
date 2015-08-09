package com.platform.advertising.ui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.platform.advertising.R;
import com.platform.advertising.http.HttpWithdrawaRecordClient;
import com.platform.advertising.ui.adapter.WithdrawaRecordAdapter;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
/**
 * 提现记录
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.withdrawal_record_layout)
public class WithdrawalRecordActivity extends BaseActivity{
	@ID(value = R.id.back,isBindListener = true)
    private ImageButton back;
	
	@ID(value = R.id.listview)
	private ListView listView;
    
    
    private WithdrawaRecordAdapter withrecordAdapter;
    
    @Override
    protected void layout() {
    	// TODO Auto-generated method stub
    	super.layout();
    	withrecordAdapter = new WithdrawaRecordAdapter();
    	withrecordAdapter.setContext(this);
    	listView.setAdapter(withrecordAdapter);
    	loadData();
  
    }
    
    private void loadData(){
    	final HttpWithdrawaRecordClient client = new HttpWithdrawaRecordClient();
    	client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			@Override
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("获取失败");

				return false;
			}
			
			@Override
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				if(client.getList()!=null){
					withrecordAdapter.setList(client.getList());
					withrecordAdapter.notifyDataSetChanged();
					
				}else{
					showShortToast("获取失败");

				}
				return false;
			}
			
			@Override
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("获取失败");
				return false;
			}
		});
    	client.setPramas(new Object[]{
    			"1","10",SharedPreferencesUtil.getString("mobile")
    			
    	});
    	ShowUtil.openHttpDialog("获取中...");
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
}
