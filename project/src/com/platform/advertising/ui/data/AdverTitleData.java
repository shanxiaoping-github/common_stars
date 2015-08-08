package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.ImageView;
import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/*
 * 广告题目
 */
public class AdverTitleData implements BaseData {
	

	private static final long serialVersionUID = 1L;
	//正确的key
	private String correctKey;
	//选中KEY
	private String isKey;
	//选中imageView
	private ImageView isImageView;
	//选中view
	private View isView;
	// id
	private String id;
	// 题目标题
	private String topicName;
	// 创建时间
	private String createTime;
	// 更新时间
	private String updateTime;
	// 得分
	private String score;
	// 价格
	private String amount;

	// A
	private String aKey;
	private String aValue;

	// B
	private String bKey;
	private String bValue;

	// C
	private String cKey;
	private String cValue;

	// D
	private String dKey;
	private String dValue;

	// E
	private String eKey;
	private String eValue;

	// F
	private String fKey;
	private String fValue;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

		// id
		id = JsonUtil.getJsonString(jo, "id");
		// 题目标题
		topicName = JsonUtil.getJsonString(jo, "topicName");
		// 创建时间
		createTime = JsonUtil.getJsonString(jo, "createTime");
		// 更新时间
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		// 得分
		score = JsonUtil.getJsonString(jo, "score");
		// 价格
		amount = JsonUtil.getJsonString(jo, "amount");

		// A
		aKey = JsonUtil.getJsonString(jo, "aKey");
		aValue = JsonUtil.getJsonString(jo, "aValue");

		// B
		bKey = JsonUtil.getJsonString(jo, "bKey");
		bValue = JsonUtil.getJsonString(jo, "bValue");

		// C
		cKey = JsonUtil.getJsonString(jo, "cKey");
		cValue = JsonUtil.getJsonString(jo, "cValue");

		// D
		dKey = JsonUtil.getJsonString(jo, "dKey");
		dValue = JsonUtil.getJsonString(jo, "dValue");

		// E
		eKey = JsonUtil.getJsonString(jo, "eKey");
		eValue = JsonUtil.getJsonString(jo, "eValue");

		// F
		fKey = JsonUtil.getJsonString(jo, "fKey");
		fValue = JsonUtil.getJsonString(jo, "fValue");

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

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getaKey() {
		return aKey;
	}

	public void setaKey(String aKey) {
		this.aKey = aKey;
	}

	public String getaValue() {
		return aValue;
	}

	public void setaValue(String aValue) {
		this.aValue = aValue;
	}

	public String getbKey() {
		return bKey;
	}

	public void setbKey(String bKey) {
		this.bKey = bKey;
	}

	public String getbValue() {
		return bValue;
	}

	public void setbValue(String bValue) {
		this.bValue = bValue;
	}

	public String getcKey() {
		return cKey;
	}

	public void setcKey(String cKey) {
		this.cKey = cKey;
	}

	public String getcValue() {
		return cValue;
	}

	public void setcValue(String cValue) {
		this.cValue = cValue;
	}

	public String getdKey() {
		return dKey;
	}

	public void setdKey(String dKey) {
		this.dKey = dKey;
	}

	public String getdValue() {
		return dValue;
	}

	public void setdValue(String dValue) {
		this.dValue = dValue;
	}

	public String geteKey() {
		return eKey;
	}

	public void seteKey(String eKey) {
		this.eKey = eKey;
	}

	public String geteValue() {
		return eValue;
	}

	public void seteValue(String eValue) {
		this.eValue = eValue;
	}

	public String getfKey() {
		return fKey;
	}

	public void setfKey(String fKey) {
		this.fKey = fKey;
	}

	public String getfValue() {
		return fValue;
	}

	public void setfValue(String fValue) {
		this.fValue = fValue;
	}
	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}
	
	
	public ImageView getIsImageView() {
		return isImageView;
	}

	public void setIsImageView(ImageView isImageView) {
		this.isImageView = isImageView;
	}

	
	public View getIsView() {
		return isView;
	}

	public void setIsView(View isView) {
		this.isView = isView;
	}
	
	

	public String getCorrectKey() {
		return correctKey;
	}

	public void setCorrectKey(String correctKey) {
		this.correctKey = correctKey;
	}

	public static ArrayList<AdverTitleData> getList(String jsonStr){
		ArrayList<AdverTitleData> list = new ArrayList<AdverTitleData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				AdverTitleData adverData = new AdverTitleData();
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
