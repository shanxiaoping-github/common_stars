package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 提现数据
 * 
 * @author shanxiaoping
 *
 */
public class WithdrawaData implements BaseData {

	private static final long serialVersionUID = 1L;
	private String id; // 提现ID
	private String bank; // 收款银行
	private String account; // 收款账号
	private String amount; // 提取金额
	private String paymentDate; // 提取日期
	private String payer; // 提款人
	private String createTime; // 创建时间
	private String updateTime; // 修改时间

	@Override
	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo, "id"); // 提现ID
		bank = JsonUtil.getJsonString(jo, "bank"); // 收款银行
		account = JsonUtil.getJsonString(jo, "account"); // 收款账号
		amount = JsonUtil.getJsonString(jo, "amount"); // 提取金额
		paymentDate = JsonUtil.getJsonString(jo, "paymentDate"); // 提取日期
		payer = JsonUtil.getJsonString(jo, "payer"); // 提款人
		createTime = JsonUtil.getJsonString(jo, "createTime"); // 创建时间
		updateTime = JsonUtil.getJsonString(jo, "updateTime"); // 修改时间

	}

	@Override
	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static ArrayList<WithdrawaData> getList(String jsonStr) {
		ArrayList<WithdrawaData> list = new ArrayList<WithdrawaData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				WithdrawaData withdrawaData = new WithdrawaData();
				withdrawaData.parser(jo);
				list.add(withdrawaData);
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}

}
