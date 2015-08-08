package com.platform.advertising.http;

import com.platform.advertising.Occupation;

/**
 * 获取所有职业列表
 * 
 * @author shanxiaoping
 *
 */
public class HttpgetOccupationListClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getOccupationList";
	}

	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		Occupation.parserList(data);
	}

}
