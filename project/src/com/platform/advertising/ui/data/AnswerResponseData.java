package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 答题结果回应
 * 
 * @author shanxiaoping
 *
 */
public class AnswerResponseData implements BaseData {
	
	private static final long serialVersionUID = 1L;
	// id
	private String id;
	//  是否正确
	private boolean correct;
	// 正确获取的金额数
	private String amount;
	// 积分
	private String score;
	// 正确答案
	private String correctAnswerKey;
	// 答题答案
	private String key;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo, "id");
		correct = JsonUtil.getJsonBoolean(jo, "correct");
		amount = JsonUtil.getJsonString(jo, "amount");
		score = JsonUtil.getJsonString(jo, "score");
		correctAnswerKey = JsonUtil.getJsonString(jo, "correctAnswerKey");
		key = JsonUtil.getJsonString(jo, "key");

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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCorrectAnswerKey() {
		return correctAnswerKey;
	}

	public void setCorrectAnswerKey(String correctAnswerKey) {
		this.correctAnswerKey = correctAnswerKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public static ArrayList<AnswerResponseData> getList(String jsonStr){
		ArrayList<AnswerResponseData> list = new ArrayList<AnswerResponseData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				AnswerResponseData answerData = new AnswerResponseData();
				answerData.parser(jo);
				list.add(answerData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
