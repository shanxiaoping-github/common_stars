package com.platform.advertising.ui.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 接收者
 * 
 * @author shanxiaoping
 *
 */
public class ReceiverData implements BaseData {

	private static final long serialVersionUID = 1L;
	private String id;
	private String createTime;
	private String updateTime;
	private String username;
	private String amount;
	private String balance;
	private String name;
	private String gender;
	private String ageGroupId;
	private String ageGroupName;
	private String jobId;
	private String jobName;
	private String onlineShopping;
	private String isMarry;
	private String provinceId;
	private String cityId;
	private String districtId;
	private String alipayAccount;
	private String longitude;
	private String latitude;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo, "id");
		createTime = JsonUtil.getJsonString(jo, "createTime");
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		username = JsonUtil.getJsonString(jo, "username");
		amount = JsonUtil.getJsonString(jo, "amount");
		balance = JsonUtil.getJsonString(jo, "balance");
		name = JsonUtil.getJsonString(jo, "name");
		gender = JsonUtil.getJsonString(jo, "gender");
		ageGroupId = JsonUtil.getJsonString(jo, "ageGroupId");
		ageGroupName = JsonUtil.getJsonString(jo, "ageGroupName");
		jobId = JsonUtil.getJsonString(jo, "jobId");
		jobName = JsonUtil.getJsonString(jo, "jobName");
		onlineShopping = JsonUtil.getJsonString(jo, "onlineShopping");
		isMarry = JsonUtil.getJsonString(jo, "isMarry");
		provinceId = JsonUtil.getJsonString(jo, "provinceId");
		cityId = JsonUtil.getJsonString(jo, "cityId");
		districtId = JsonUtil.getJsonString(jo, "districtId");
		alipayAccount = JsonUtil.getJsonString(jo, "alipayAccount");
		longitude = JsonUtil.getJsonString(jo, "longitude");
		latitude = JsonUtil.getJsonString(jo, "latitude");

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeGroupId() {
		return ageGroupId;
	}

	public void setAgeGroupId(String ageGroupId) {
		this.ageGroupId = ageGroupId;
	}

	public String getAgeGroupName() {
		return ageGroupName;
	}

	public void setAgeGroupName(String ageGroupName) {
		this.ageGroupName = ageGroupName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getOnlineShopping() {
		return onlineShopping;
	}

	public void setOnlineShopping(String onlineShopping) {
		this.onlineShopping = onlineShopping;
	}

	public String getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
