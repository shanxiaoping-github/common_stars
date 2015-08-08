package com.platform.advertising;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.platform.advertising.http.HttpgetOccupationListClient;
import com.platform.advertising.sqlite.GreenDaoService;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table OCCUPATION.
 */
public class Occupation implements BaseData {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String idStr;
	private String createTime;
	private String updateTime;
	private String sort;
	private String name;
	private String value;
	private String jp;
	private String qp;
	private String remark;

	public Occupation() {
	}

	public Occupation(Long id) {
		this.id = id;
	}

	public Occupation(Long id, String idStr, String createTime,
			String updateTime, String sort, String name, String value,
			String jp, String qp, String remark) {
		this.id = id;
		this.idStr = idStr;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.sort = sort;
		this.name = name;
		this.value = value;
		this.jp = jp;
		this.qp = qp;
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getQp() {
		return qp;
	}

	public void setQp(String qp) {
		this.qp = qp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		idStr = JsonUtil.getJsonString(jo, "id");
		createTime = JsonUtil.getJsonString(jo, "createTime");
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		sort = JsonUtil.getJsonString(jo, "sort");
		name = JsonUtil.getJsonString(jo, "name");
		value = JsonUtil.getJsonString(jo, "value");
		jp = JsonUtil.getJsonString(jo, "jp");
		qp = JsonUtil.getJsonString(jo, "qp");
		remark = JsonUtil.getJsonString(jo, "remark");

	}
	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static List<Occupation> list;
	public static void parserList(String jsonStr){
		List<Occupation> occList = new ArrayList<Occupation>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				Occupation occ = new Occupation();
				occ.parser(jo);
				GreenDaoService.getInstance().getDaoSession().getOccupationDao().insert(occ);
				occList.add(occ);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(occList.size() > 0){
			list = occList;
			
		}
		
	}
	public static void loadList(){
		if(list == null){
			list = GreenDaoService.getInstance().getDaoSession().getOccupationDao().queryBuilder().list();
		}
		if(list == null||list.size()<=0){
			HttpgetOccupationListClient client  = new HttpgetOccupationListClient();
			client.submitRequest();
		}
	}
	
	public static List<Occupation> getList(){
		return list;
	}
	public static Occupation queryOccupation(String id){
		if(list==null){
			return null;
		}
		for(int i=0;i<list.size();i++){
			Occupation occItem = list.get(i);
			if(occItem.getIdStr().trim().equalsIgnoreCase(id.trim())){
				return occItem;
			}
		}
		return null;
	}
	

}
