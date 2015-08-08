package com.platform.advertising.http;
/**
 * 
 * @author shanxiaoping 
 *
 */
public class HttpAdvertisingMarketClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAdvertisementList";
	}
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"pageNumber",
				"pageSize",
				"districtId",
				"provinceId",
				"cityId",
				"keyword"
		};
	}
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
	}
}
