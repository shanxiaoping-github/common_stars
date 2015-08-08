package com.platform.advertising.http;
/**
 * 个人信息保存
 * @author shanxiaoping
 *
 */
public class HttpPersonInformationClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "userInfoUpdate";
	}
	protected String[] getContentPramasKeys() {
		return new String[]{
				"mobile",
				"districtId",
				"provinceId",
				"cityId",
				"genderId",
				"ageGroupId",
				"ageGroupName",
				"jobId",
				"jobName",
				"onlineShopping",
				"isMarry"
				
		};
	}
}
