package com.platform.advertising.http;

import java.util.ArrayList;

import sxp.android.framework.util.StringUtil;

import com.platform.advertising.ui.data.AdverTitleData;

/**
 * 获得广告题目列表
 * @author shanxiaoping
 *
 */
public class HttpgetAdvertisementTopicListClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAdvertisementTopicList";
	}
	
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
			"seqId"
		};
	}
	
	
	private ArrayList<AdverTitleData> list;
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if(!StringUtil.isJsonEmpty(data)){
			list = AdverTitleData.getList(data);
		}
	}

	
	public ArrayList<AdverTitleData> getList() {
		return list;
	}

	public void setList(ArrayList<AdverTitleData> list) {
		this.list = list;
	}
}
