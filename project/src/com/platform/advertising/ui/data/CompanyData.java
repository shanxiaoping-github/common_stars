package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 公司数据
 * 
 * @author shanxiaoping
 *
 */
public class CompanyData implements BaseData {
	private static final long serialVersionUID = 1L;
	// 创建时间
	private String createTime;
	// 手机
	private String phone;
	// 办公电话
	private String fax;
	// 城市id
	private String cityId;
	// 更新时间
	private String updateTime;
	// 介绍
	private String introduce;
	// 公司名称
	private String companyName;
	// id
	private String id;
	// imageList
	private String imageList;
	// 区id
	private String districtId;
	// 地址
	private String address;
	// 联系人
	private String contactPerson;
	// 移动电话
	private String mobil;
	

	
	

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

		// 创建时间
		createTime = JsonUtil.getJsonString(jo, "createTime");
		// 手机
		phone = JsonUtil.getJsonString(jo, "phone");
		// 办公电话
		fax = JsonUtil.getJsonString(jo, "fax");
		// 城市id
		cityId = JsonUtil.getJsonString(jo, "cityId");
		// 更新时间
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		// 介绍
		introduce = JsonUtil.getJsonString(jo, "introduce");
		// 公司名称
		companyName = JsonUtil.getJsonString(jo, "companyName");
		// id
		id = JsonUtil.getJsonString(jo, "id");
		// imageList
		imageList = JsonUtil.getJsonString(jo, "imageList");
		// 区id
		districtId = JsonUtil.getJsonString(jo, "districtId");
		// 地址
		address = JsonUtil.getJsonString(jo, "address");
		// 联系人
		contactPerson = JsonUtil.getJsonString(jo, "contactPerson");
		// 移动电话
		mobil = JsonUtil.getJsonString(jo, "mobil");

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageList() {
		return imageList;
	}

	public void setImageList(String imageList) {
		this.imageList = imageList;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static ArrayList<CompanyData> getList(String jsonStr){
		ArrayList<CompanyData> list = new ArrayList<CompanyData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				CompanyData companyData = new CompanyData();
				companyData.parser(jo);
				list.add(companyData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
