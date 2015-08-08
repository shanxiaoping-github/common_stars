package com.platform.advertising.view.city.data;

import org.json.JSONObject;

/**
 * 区
 * 
 * @author xiaoping.shan
 *
 */
public class Area implements Adress {

	private static final long serialVersionUID = 1L;
	private City city;
	private String code;
	private String title;
	private boolean isSelect = false;

	public int getAdressType() {
		// TODO Auto-generated method stub
		return AREA;
	}

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
