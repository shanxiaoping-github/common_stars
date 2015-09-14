package com.platform.advertising.http;

import java.util.ArrayList;

import com.platform.advertising.ui.data.AdvertisingData;

import sxp.android.framework.util.StringUtil;

/**
 * 
 * @author shanxiaoping
 *  获取广告列表
 */
public class HttpgetAdvertisementListClient extends HttpAdvertisingClient{
	private ArrayList<AdvertisingData> list;
	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "getAdvertisementList";
	}
	
	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				
				"pageNumber",
				"pageSize"  

		};
	}
	
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if(!StringUtil.isJsonEmpty(data)){
			list = AdvertisingData.getList(data);
		}
	}

	public ArrayList<AdvertisingData> getList() {
		return list;
	}

	public void setList(ArrayList<AdvertisingData> list) {
		this.list = list;
	}
	

}
