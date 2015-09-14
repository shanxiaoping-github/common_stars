package com.platform.advertising.ui.login.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 个人信息
 * 
 * @author shanxiaoping
 *
 */
public class UserInfo implements BaseData {
	private String id;
	private String provinceId;
	private String isMarry;
	private String createTime;
	private String districtId;
	private String jobId;
	private String ageGroupId;
	private String cityId;
	private String onlineShopping;
	private String ageGroupName;
	private String updateTime;
	private String gender;
	private String jobName;
	
	private String provinceName;
	private String cityName;
	private String districtName;	

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo, "id");
		provinceId = JsonUtil.getJsonString(jo, "provinceId");
		isMarry = JsonUtil.getJsonString(jo, "isMarry");
		createTime = JsonUtil.getJsonString(jo, "createTime");
		districtId = JsonUtil.getJsonString(jo, "districtId");
		jobId = JsonUtil.getJsonString(jo, "jobId");
		ageGroupId = JsonUtil.getJsonString(jo, "ageGroupId");
		cityId = JsonUtil.getJsonString(jo, "cityId");
		onlineShopping = JsonUtil.getJsonString(jo, "onlineShopping");
		ageGroupName = JsonUtil.getJsonString(jo, "ageGroupName");
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		gender = JsonUtil.getJsonString(jo, "gender");
		jobName = JsonUtil.getJsonString(jo, "jobName");
		
		provinceName = JsonUtil.getJsonString(jo, "provinceName");
		cityName = JsonUtil.getJsonString(jo, "cityName");
		districtName = JsonUtil.getJsonString(jo, "districtName");

	}

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

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getAgeGroupId() {
		return ageGroupId;
	}

	public void setAgeGroupId(String ageGroupId) {
		this.ageGroupId = ageGroupId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getOnlineShopping() {
		return onlineShopping;
	}

	public void setOnlineShopping(String onlineShopping) {
		this.onlineShopping = onlineShopping;
	}

	public String getAgeGroupName() {
		return ageGroupName;
	}

	public void setAgeGroupName(String ageGroupName) {
		this.ageGroupName = ageGroupName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public com.platform.advertising.UserInfo copyUserInfo(){
		com.platform.advertising.UserInfo userInfo  = new com.platform.advertising.UserInfo();
		userInfo.setSex(gender);
		userInfo.setAgeId(ageGroupId);
		userInfo.setAgeName(ageGroupName);
		userInfo.setJobId(jobId);
		userInfo.setJobName(jobName);
		userInfo.setProvinceId(provinceId);
		userInfo.setProvinceName(provinceName);
		userInfo.setCityId(cityId);
		userInfo.setCityName(cityName);
		userInfo.setAreaId(districtId);
		userInfo.setAreaName(districtName);
		userInfo.setIsNetShoping(onlineShopping);
		userInfo.setIsMarry(isMarry);
		return userInfo;
	}
	

}
