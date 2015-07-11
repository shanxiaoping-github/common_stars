package com.platform.advertising.ui.find.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;

public class Company implements BaseData {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 地址
	 */
	private String adress;
	/**
	 * 介绍
	 */
	private String introduce;
	/**
	 * 手机号码
	 */
	private String telphone;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

}
