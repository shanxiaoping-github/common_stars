package com.platform.advertising.view.city.data;

import java.util.ArrayList;

import org.json.JSONObject;

/**
 * 市
 * @author xiaoping.shan
 *
 */
public class City implements Adress{


	private static final long serialVersionUID = 1L;
	private Province province;
	private ArrayList<Area> areaList;
	private String code;
	private String title;
	private boolean isSelect=false;
	public int getAdressType() {
		// TODO Auto-generated method stub
		return CITY;
	}

	
	public void addArea(Area area){
		if(areaList == null){
			areaList = new ArrayList<Area>();
		}
		area.setCity(this);
		areaList.add(area);
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


	public Province getProvince() {
		return province;
	}


	public void setProvince(Province province) {
		this.province = province;
	}


	public ArrayList<Area> getAreaList() {
		return areaList;
	}


	public void setAreaList(ArrayList<Area> areaList) {
		this.areaList = areaList;
	}


	public boolean isSelect() {
		return isSelect;
	}


	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

}
