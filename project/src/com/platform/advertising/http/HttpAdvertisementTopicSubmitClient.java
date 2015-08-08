package com.platform.advertising.http;

import java.util.ArrayList;

import com.platform.advertising.ui.data.AnswerResponseData;

import sxp.android.framework.util.StringUtil;

/**
 * 题目提交
 * 
 * @author shanxiaoping
 *
 */
public class HttpAdvertisementTopicSubmitClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "advertisementTopicSubmit";
	}

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "seqId", "param", "mobile" };
	}

	private ArrayList<AnswerResponseData> list;

	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
		if (!StringUtil.isJsonEmpty(data)) {
			list = AnswerResponseData.getList(data);
		}

	}

	public ArrayList<AnswerResponseData> getList() {
		return list;
	}

	public void setList(ArrayList<AnswerResponseData> list) {
		this.list = list;
	}

}
