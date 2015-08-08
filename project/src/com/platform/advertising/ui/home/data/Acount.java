package com.platform.advertising.ui.home.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 账户信息
 * 
 * @author shanxiaoping
 *
 */
public class Acount implements BaseData {

	private static final long serialVersionUID = 1L;
	// 账户资金
	private String amount;
	// 账户余额
	private String balance;
	// 用户名
	private String username;
	// 真实姓名
	private String name;
	// 支付宝账户
	private String alipayAccount;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		amount = JsonUtil.getJsonString(jo, "amount");
		balance = JsonUtil.getJsonString(jo, "balance");
		username = JsonUtil.getJsonString(jo, "username");
		name = JsonUtil.getJsonString(jo, "name");
		alipayAccount = JsonUtil.getJsonString(jo, "alipayAccount");
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	
}
