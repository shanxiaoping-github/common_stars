package com.platform.advertising.http;

import com.platform.advertising.Age;

/**
 * 获得age 
 * @author shanxiaoping
 *
 */
public class HttpAgeClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAgeGroupList";
	}
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		Age.parserList(data);
		
	}

}
