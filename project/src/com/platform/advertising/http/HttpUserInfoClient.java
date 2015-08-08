package com.platform.advertising.http;

import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.application.SXPApplication;

import com.platform.advertising.ui.login.data.UserInfo;

/**
 * 个人信息获取
 * 
 * @author shanxiaoping
 *
 */
public class HttpUserInfoClient extends HttpAdvertisingClient {

	private UserInfo userInfo;

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getUserInfo";
	}

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "mobile" };
	}

	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if (isSuccess()){
			userInfo = new UserInfo();
			try {
				userInfo.parser(new JSONObject(data));
				SXPApplication.getInstance().getSXPRuntimeContext().savaData(UserInfo.class.getName(),userInfo);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	

}
