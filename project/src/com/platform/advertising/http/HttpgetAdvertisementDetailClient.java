package com.platform.advertising.http;
/**
 * 获得广告详情
 * @author shanxiaoping
 *
 */
public class HttpgetAdvertisementDetailClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAdvertisementDetail";
	}
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"seqId"
		};
	}
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
	}

}
