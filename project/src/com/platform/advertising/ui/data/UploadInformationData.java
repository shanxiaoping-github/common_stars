package com.platform.advertising.ui.data;

import java.io.File;

/**
 * 上传企业资料
 * @author shanxiaoping
 *
 */
public class UploadInformationData {
	//公司名称
	private String companyName;
	//具体地址
	private String specific_address;
	//公司介绍
	private String company_introduce;
	//联系人
	private String contractPerson;
	//手机号码
	private String phone;
	//省
	private String provinceId;
	private String provinceName;
	//城市
	private String cityId;
    private String cityName;
	//地区
	private String areaId;
	private String areaName;
	//图片
	private File file;
	//经度，纬度
	private String longitude = "0";
	private String latitude = "0";
	private String adress;
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSpecific_address() {
		return specific_address;
	}
	public void setSpecific_address(String specific_address) {
		this.specific_address = specific_address;
	}
	public String getCompany_introduce() {
		return company_introduce;
	}
	public void setCompany_introduce(String company_introduce) {
		this.company_introduce = company_introduce;
	}
	public String getContractPerson() {
		return contractPerson;
	}
	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	
}
