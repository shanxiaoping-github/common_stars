package com.platform.advertising.http;
/**
 * 添加账户
 * @author shanxiaoping
 *
 */
public class HttpAddAccountClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "updateAccount";
	}
	
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"mobile",
				"name",
				"alipayAccount"
		};
	}
	
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
	}

}
