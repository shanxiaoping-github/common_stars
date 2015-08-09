package com.platform.advertising.http;

/**
 * 信件回复
 * 
 * @author shanxiaoping
 *
 */
public class HttpMessageReplyClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "messageReply";
	}

	// mobile登陆用户号
	// content 内容
	// originalId 原站内信编号

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "mobile", "content", "originalId" };
	}

}
