package com.platform.advertising.http;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.exception.SXPException;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.JsonUtil;

/**
 * @author shanxiaoping 网络服务client
 */
public abstract class HttpAdvertisingClient extends BaseAsynHttpClient {
	/**
	 * 返回内容键值
	 * 
	 * @return
	 */
	protected String[] getContentPramasKeys() {
		return null;
	}

	/**
	 * 获取链接地址
	 * 
	 * @return
	 */
	protected abstract String getAdress();

	// 0 一般参数 1 file参数
	private int state = 0;

	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		switch (state) {
		case 0:
			return paramsKey;
		case 1:
			return paramsFileKey;
		default:
			return paramsKey;
		}

	}

	private final String[] paramsKey = new String[] { "interCode",
			"jsonContent" };
	private final String[] paramsFileKey = new String[] { "interCode",
			"jsonContent", "uploadFile" };
	private File file;
	
	@Override
	public void setPramas(Object... params) {
		// TODO Auto-generated method stub
		Object[] tempParams = null;
		if (null != params && params.length > 0) {
			String[] contentKeys = getContentPramasKeys();
			if (null != contentKeys && contentKeys.length != params.length) {
				throw new SXPException("参数异常", "参数个数不匹配");
			} else if (null != contentKeys) {
				JSONObject jo = new JSONObject();
				for (int i = 0; i < params.length; i++) {
					try {

						boolean isFile = params[i] instanceof File ? true
								: false;
						if (isFile) {
                           state = 1;
                           file = (File)params[i];
						} else {
							jo.put(contentKeys[i], String.valueOf(params[i]));
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if(file == null){
					tempParams = new Object[2];
				}else{
					tempParams = new Object[3];
					tempParams[2] = file;
				}
				tempParams[0] = getAdress();
				tempParams[1] = jo.toString();
			}
		} else {
			tempParams = new Object[2];
			tempParams[0] = getAdress();
			tempParams[1] = "{}";

		}
		super.setPramas(tempParams);
	}
	
//	public File[] getFile(){
//		File[] fileArray = new File[fileList.size()];
//		for (int i = 0; i < fileList.size(); i++) {
//			File file = fileList.get(i);
//			fileArray[i] = file;
//		}
//		return fileArray;
//	}

	/**
	 * 提交请求
	 */
	public void submitRequest() {
		if (null == getContentPramasKeys()) {
			setPramas(null);
		}
		this.subRequestPost("");
	}

	private boolean isSuccess = false;
	private String message = "";
	private String data = "";

	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		try {
			JSONObject jo = new JSONObject(content);
			isSuccess = JsonUtil.getJsonBoolean(jo, "code");
			message = JsonUtil.getJsonString(jo, "message");
			data = JsonUtil.getJsonString(jo, "data");
			parserMessage(data);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 解析具体的数据
	 * 
	 * @param message
	 */
	protected void parserMessage(String data) {

	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
