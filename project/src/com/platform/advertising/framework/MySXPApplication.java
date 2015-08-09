package com.platform.advertising.framework;

import com.baidu.mapapi.SDKInitializer;
import com.platform.advertising.util.SharedPreferencesUtil;

import sxp.android.framework.application.SXPApplication;

/**
 * 自定义application
 * 
 * @author shanxiaoping
 *
 */
public class MySXPApplication extends SXPApplication {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SharedPreferencesUtil.init(getAppContext(), getPackageName());
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}
}
