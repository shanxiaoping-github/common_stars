package com.platform.advertising.http;

/**
 * 更新经纬度信息
 * 
 * @author shanxiaoping
 *
 */
public class HttpUpdataLocationClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "userInfoUpdate";
	}

	protected String[] getContentPramasKeys() {
		return new String[] { 
				"mobile", 
				"longitude", 
				"latitude"
		};
	}
}
