package com.platform.advertising.http;


/**
 * 登陆client
 * 
 * @author shanxiaoping
 *
 */
public class HttpLoginClient extends HttpAdvertisingClient {
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"mobile","password"
		};
	}
	
	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "login";
	}
	
}
