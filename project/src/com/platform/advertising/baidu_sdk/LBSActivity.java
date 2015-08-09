package com.platform.advertising.baidu_sdk;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.ui.BaseActivity;
import android.content.Intent;
import android.view.View;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.platform.advertising.R;
import com.platform.advertising.baidu_sdk.BaiduLBSManager.LbsResultListener;

/**
 * 百度定位并显示
 * 
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.lbs_layout)
public class LBSActivity extends BaseActivity {
	@ID(value = R.id.back,isBindListener = true)
	private View back;

	@ID(value = R.id.sure,isBindListener = true)
	private View sure;

	@ID(value = R.id.bmapView)
	private MapView mapView;

	private LbsResultListener lbsListener;

	private boolean isFirstLoc;
	private boolean isLBSsuccess;
	private BDLocation bdLocation;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		super.layout();
		initMap();
		isFirstLoc = true;
		isLBSsuccess = false;
		lbsListener = new LbsResultListener() {

			@Override
			public void success(BDLocation bdLocation) {
				// TODO Auto-generated method stub
				
				if (bdLocation != null && mapView != null) {
					isLBSsuccess  = true;
					LBSActivity.this.bdLocation = bdLocation;
					MyLocationData locData = new MyLocationData.Builder()
							.accuracy(bdLocation.getRadius())
							// 此处设置开发者获取到的方向信息，顺时针0-360
							.direction(100).latitude(bdLocation.getLatitude())
							.longitude(bdLocation.getLongitude()).build();
					baiduMap.setMyLocationData(locData);
					if (isFirstLoc) {
						isFirstLoc = false;
						LatLng ll = new LatLng(bdLocation.getLatitude(),
								bdLocation.getLongitude());
						MapStatusUpdate u = MapStatusUpdateFactory
								.newLatLng(ll);
						baiduMap.animateMapStatus(u);
					}
				}

			}

			@Override
			public void error(String errorMsg) {
				// TODO Auto-generated method stub
				showShortToast(errorMsg);
			}
		};
		BaiduLBSManager.getInstance().setLbsListener(lbsListener);
		BaiduLBSManager.getInstance().startLBS();

	}

	/**
	 * 地图初始化
	 */
	private BaiduMap baiduMap;

	private void initMap() {
		// 地图初始化
		baiduMap = mapView.getMap();
		baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
				LocationMode.NORMAL, true, null));
		// 开启定位图层
		baiduMap.setMyLocationEnabled(true);
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		BaiduLBSManager.getInstance().stopLBS();
		// 关闭定位图层
		baiduMap.setMyLocationEnabled(false);
		mapView.onDestroy();
		mapView = null;
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.back:
			finishBase();
			break;

		case R.id.sure:
			sure();
			break;
		}
	}

	/**
	 * 确定
	 */
	private void sure() {
		if(isLBSsuccess){
			Intent intent = new Intent();
			intent.putExtra(BDLocation.class.getName(),bdLocation);
			setResult(0,intent);
			finishBase();
		}else{
			showShortToast("定位不成功,请按返回键退出");
		}

	}

}
