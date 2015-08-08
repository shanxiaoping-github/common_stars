package com.platform.advertising.ui.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
import sxp.android.framework.util.StringUtil;

/**
 * 信件消息
 * 
 * @author shanxiaoping
 *
 */
public class MessageData implements BaseData {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String createTime;
	private String updateTime;
	private String title;
	private String content;
	private String senderRead;
	private String receiverRead;
	private String senderDelete;
	private String receiverDelete;
	private String forMessage;
	private String newMessage;
	private SenderData sender;
	private ReceiverData receiver;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo, "id");
		createTime = JsonUtil.getJsonString(jo, "createTime");
		updateTime = JsonUtil.getJsonString(jo, "updateTime");
		title = JsonUtil.getJsonString(jo, "title");
		content = JsonUtil.getJsonString(jo, "content");
		senderRead = JsonUtil.getJsonString(jo, "senderRead");
		receiverRead = JsonUtil.getJsonString(jo, "receiverRead");
		senderDelete = JsonUtil.getJsonString(jo, "senderDelete");
		receiverDelete = JsonUtil.getJsonString(jo, "receiverDelete");
		forMessage = JsonUtil.getJsonString(jo, "forMessage");
		newMessage = JsonUtil.getJsonString(jo, "newMessage");
		
		String senderStr = JsonUtil.getJsonString(jo,"sender");
		if(!StringUtil.isJsonEmpty(senderStr)){
			sender = new SenderData();
			try {
				sender.parser(new JSONObject(senderStr));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String receiverStr = JsonUtil.getJsonString(jo, "receiver");
		if(!StringUtil.isJsonEmpty(receiverStr)){
			receiver = new ReceiverData();
			try {
				receiver.parser(new JSONObject(receiverStr));
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderRead() {
		return senderRead;
	}

	public void setSenderRead(String senderRead) {
		this.senderRead = senderRead;
	}

	public String getReceiverRead() {
		return receiverRead;
	}

	public void setReceiverRead(String receiverRead) {
		this.receiverRead = receiverRead;
	}

	public String getSenderDelete() {
		return senderDelete;
	}

	public void setSenderDelete(String senderDelete) {
		this.senderDelete = senderDelete;
	}

	public String getReceiverDelete() {
		return receiverDelete;
	}

	public void setReceiverDelete(String receiverDelete) {
		this.receiverDelete = receiverDelete;
	}

	public String getForMessage() {
		return forMessage;
	}

	public void setForMessage(String forMessage) {
		this.forMessage = forMessage;
	}

	public String getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}

	public SenderData getSender() {
		return sender;
	}

	public void setSender(SenderData sender) {
		this.sender = sender;
	}

	public ReceiverData getReceiver() {
		return receiver;
	}

	public void setReceiver(ReceiverData receiver) {
		this.receiver = receiver;
	}
	
	public static ArrayList<MessageData> getList(String jsonStr){
		ArrayList<MessageData> list = new ArrayList<MessageData>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i = 0;i < ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				MessageData messageData = new MessageData();
				messageData.parser(jo);
				list.add(messageData);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
