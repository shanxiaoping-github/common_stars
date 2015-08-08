package com.platform.advertising;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER_INFO.
 */
public class UserInfo {

    private Long id;
    private String userId;
    private String sex;
    private String ageId;
    private String ageName;
    private String jobId;
    private String jobName;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String areaId;
    private String areaName;
    private String isNetShoping;
    private String isMarry;

    public UserInfo() {
    }

    public UserInfo(Long id) {
        this.id = id;
    }

    public UserInfo(Long id, String userId, String sex, String ageId, String ageName, String jobId, String jobName, String provinceId, String provinceName, String cityId, String cityName, String areaId, String areaName, String isNetShoping, String isMarry) {
        this.id = id;
        this.userId = userId;
        this.sex = sex;
        this.ageId = ageId;
        this.ageName = ageName;
        this.jobId = jobId;
        this.jobName = jobName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.areaId = areaId;
        this.areaName = areaName;
        this.isNetShoping = isNetShoping;
        this.isMarry = isMarry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAgeId() {
        return ageId;
    }

    public void setAgeId(String ageId) {
        this.ageId = ageId;
    }

    public String getAgeName() {
        return ageName;
    }

    public void setAgeName(String ageName) {
        this.ageName = ageName;
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

    public String getIsNetShoping() {
        return isNetShoping;
    }

    public void setIsNetShoping(String isNetShoping) {
        this.isNetShoping = isNetShoping;
    }

    public String getIsMarry() {
        return isMarry;
    }

    public void setIsMarry(String isMarry) {
        this.isMarry = isMarry;
    }

}
