package com.platform.advertising.http;
/**
 * 企业资料提交
 * @author shanxiaoping
 *
 */
public class HttpEnterpriseInformationSubmitClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "submitEnterpriseData";
	}
	
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
			"companyName",
			"districtId",
			"provinceId",
			"cityId",
			"address",
			"phone",
			"contactPerson",
			"introduce",
			"longitude",
			"latitude",
			"uploadFile"
		};
	}
	

}
