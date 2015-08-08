package com.platform.advertising.framework;

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
		SharedPreferencesUtil.init(getAppContext(),getPackageName());
	}
}
