package com.platform.advertising.http;
/**
 * 提现
 * @author shanxiaoping
 *
 */
public class HttpWithdrawalClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "withdrawals";
	}
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"mobile",
				"amount"
		};
	}
	

}
