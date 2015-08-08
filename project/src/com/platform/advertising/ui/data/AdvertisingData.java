package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
import sxp.android.framework.util.StringUtil;

/**
 * 广告数据
 * 
 * @author shanxiaoping
 *
 */
public class AdvertisingData implements BaseData {

	private static final long serialVersionUID = 1L;
	// 内容
	private String content;
	// 标题
	private String summary;
	// id
	private String id;
	// 创建时间
	private String createTime;
	// 标题
	private String title;

	// imageList
	private ArrayList<ImageData> imageList;
	// company
	private CompanyData company;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		content =  JsonUtil.getJsonString(jo, "content");
		summary = JsonUtil.getJsonString(jo, "summary");
		id = JsonUtil.getJsonString(jo, "id");
		createTime = JsonUtil.getJsonString(jo, "createTime");
		title = JsonUtil.getJsonString(jo, "title");
		
		String imageListStr = JsonUtil.getJsonString(jo, "imageList");
		if(!StringUtil.isJsonEmpty(imageListStr)){
			imageList = ImageData.getList(imageListStr);
		}
		
		String companyStr = JsonUtil.getJsonString(jo,"company");
		if(!StringUtil.isJsonEmpty(companyStr)){
			company = new CompanyData();
			try {
				company.parser(new JSONObject(companyStr));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<ImageData> getImageList() {
		return imageList;
	}

	public void setImageList(ArrayList<ImageData> imageList) {
		this.imageList = imageList;
	}

	public CompanyData getCompany() {
		return company;
	}

	public void setCompany(CompanyData company) {
		this.company = company;
	}
	public static ArrayList<AdvertisingData> getList(String jsonStr){
		ArrayList<AdvertisingData> list = new ArrayList<AdvertisingData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i= 0 ;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				AdvertisingData adverData = new AdvertisingData();
				adverData.parser(jo);
				list.add(adverData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
