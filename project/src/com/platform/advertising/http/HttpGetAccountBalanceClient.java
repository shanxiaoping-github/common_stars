package com.platform.advertising.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.platform.advertising.ui.home.data.Acount;

import sxp.android.framework.util.StringUtil;

/**
 * 获取账户余额
 * 
 * @author shanxiaoping
 *
 */
public class HttpGetAccountBalanceClient extends HttpAdvertisingClient {

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "mobile" };
	}

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAccountBalance";
	}

	private Acount account;

	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if (!StringUtil.isEmpty(data)) {

			try {
				account = new Acount();
				account.parser(new JSONObject(data));
				if((StringUtil.isEmpty(account.getAlipayAccount())||account.getAlipayAccount().equals("null"))||(StringUtil.isEmpty(account.getName())||account.getName().equals("null"))){
					account = null;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Acount getAccount() {
		return account;
	}

	public void setAccount(Acount account) {
		this.account = account;
	}

}
