package com.platform.advertising.http;

import java.util.ArrayList;

import com.platform.advertising.ui.data.MessageData;

import sxp.android.framework.util.StringUtil;

/**
 * 获取信息列表
 * 
 * @author shanxiaoping
 *
 */
public class HttpgetMessageListClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "messageList";
	}

	// mobile登陆用户号
	// pageNumber 页码
	// pageSize 每页记录数（如每页显示20条记录）

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] {
				"mobile",
				"pageNumber","pageSize"
		};
	}
	
	private ArrayList<MessageData> list;
	@Override
	protected void parserMessage(String data){
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if(!StringUtil.isJsonEmpty(data)){
			list = MessageData.getList(data);
		}
	}

	public ArrayList<MessageData> getList() {
		return list;
	}

	public void setList(ArrayList<MessageData> list) {
		this.list = list;
	}
	
	
	

}
