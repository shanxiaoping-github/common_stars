package com.platform.advertising.ui;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.platform.advertising.R;
import com.platform.advertising.baidu_sdk.LBSActivity;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpUpdataLocationClient;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
@LAYOUT(R.layout.activity_location)
public class LocationActivity extends MyBaseActivity implements OnClickListener {

	@ID(value = R.id.activity_location_infomation,isBindListener = true)
	private TextView locationInformation;
	
	@ID(value = R.id.activity_location_infomation,isBindListener = true)
	private TextView locationInfomation;
	
	
	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.activity_location);
		setBackButton();
		findViewById(R.id.lLocation).setOnClickListener(this);
		String bdlocationStr = (String)SXPApplication.getInstance().getSXPConfigurationContext().getData("bdlocation");
		locationInfomation.setText(bdlocationStr);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lLocation: // 确认
			location();
			break;
		}
	}

	/**
	 * 定位
	 */
	private void location() {
		openActivityResult(LBSActivity.class);
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if(arg2 != null){
			BDLocation bdlocation = (BDLocation)arg2.getExtras().getParcelable(BDLocation.class.getName());
			String bdlocationStr = "纬度"+bdlocation.getLatitude()+","+"经度"+bdlocation.getLongitude()+" "+bdlocation.getAddrStr();
			SXPApplication.getInstance().getSXPConfigurationContext().savaData("bdlocation",bdlocationStr);
			locationInfomation.setText(bdlocationStr);
			uploadLocation(bdlocation);
			
		}
	}
	/**
	 * 更新位置信息
	 * @param bdlocation  位置信息
	 */
	public void uploadLocation(BDLocation bdlocation){
		final HttpUpdataLocationClient client = new HttpUpdataLocationClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
			
			@Override
			public boolean onTimeout() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("更新失败");
				return false;
			}
			
			@Override
			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				if(client.isSuccess()){
					showShortToast("更新成功");
				}else{
					showShortToast("更新失败");
				}
				return false;
			}
			
			@Override
			public boolean onEmpty() {
				// TODO Auto-generated method stub
				ShowUtil.closeHttpDialog();
				showShortToast("更新失败");
				return false;
			}
		});
		client.setPramas(new Object[]{
				SharedPreferencesUtil.getString("mobile"),bdlocation.getLongitude(),bdlocation.getLatitude()
		});
		ShowUtil.openHttpDialog("定位信息更新中...");
		client.submitRequest();
	}

}
