package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
/**
 * 图片对象
 * @author shanxiaoping
 *
 */
public class ImageData implements BaseData{
	private static final long serialVersionUID = 1L;
	//id
	private String id;
	//创建时间
	private String createTime;
	//地址
	private String imagePath;
	//名称
	private String imageName;
	//类型
	private String imageType;
	//更新时间
	private String updateTime;
	
	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo,"id");
		createTime = JsonUtil.getJsonString(jo,"createTime");
		imagePath = JsonUtil.getJsonString(jo,"imagePath");
		imageName  = JsonUtil.getJsonString(jo,"imageName");
		imageType = JsonUtil.getJsonString(jo,"imageType");
		updateTime = JsonUtil.getJsonString(jo,"updateTime");
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 获取list
	 * @param jsonStr
	 * @return
	 */
	public static ArrayList<ImageData> getList(String jsonStr){
		ArrayList<ImageData> list = new ArrayList<ImageData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo =ja.getJSONObject(i);
				ImageData imageData = new ImageData();
				imageData.parser(jo);
				list.add(imageData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
