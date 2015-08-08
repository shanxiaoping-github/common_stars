package com.platform.advertising.http;

/**
 * 信件发送
 * 
 * @author shanxiaoping
 *
 */
public class HttpMessageSendClient extends HttpAdvertisingClient {

	@Override
	protected String getAdress() {
		// TODO Auto-generated method stub
		return "messageSend";
	}

	// mobile登陆用户号
	// title 标题
	// content 内容
	// receiverId 接收人用户号

	@Override
	protected String[] getContentPramasKeys() {
		// TODO Auto-generated method stub
		return new String[] { "mobile", "title", "content", "receiverId" };
	}
	@Override
	protected void parserMessage(String data) {
		// TODO Auto-generated method stub
		super.parserMessage(data);
	}

}
