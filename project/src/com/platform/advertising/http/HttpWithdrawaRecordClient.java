package com.platform.advertising.http;

import java.util.ArrayList;

import com.platform.advertising.ui.data.WithdrawaData;

import sxp.android.framework.util.StringUtil;

/**
 * 获取提现记录
 * @author shanxiaoping
 *
 */
public class HttpWithdrawaRecordClient extends HttpAdvertisingClient{

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "withdrawalsRecord";
	}
	
//	pageNumber		number	Y	1	页码
//	pageSize  	number	Y	1	每页记录数（如每页显示20条记录）
//	mobile	number	Y	1	手机号码

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"pageNumber",
				"pageSize",
				"mobile"
		};
	}
	private ArrayList<WithdrawaData> list;
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if(!StringUtil.isJsonEmpty(data)){
			list = WithdrawaData.getList(data);
		}
	}

	public ArrayList<WithdrawaData> getList() {
		return list;
	}

	public void setList(ArrayList<WithdrawaData> list) {
		this.list = list;
	}
	

}
