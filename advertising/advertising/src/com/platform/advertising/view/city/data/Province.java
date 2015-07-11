package com.platform.advertising.view.city.data;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * 省
 * @author xiaoping.shan
 *
 */
public class Province implements Adress{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<City> cityList;
	private String code;
	private String title;
	private boolean isSelect=false;

	public int getAdressType() {
		// TODO Auto-generated method stub
		return PROVINCE;
	}
	
	public void addCity(City city){
		if(cityList==null){
			cityList = new ArrayList<City>();
		}
		city.setProvince(this);
		cityList.add(city);
			
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

	public ArrayList<City> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<City> cityList) {
		this.cityList = cityList;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
