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

	private final String[] paramsKey = new String[] { "interCode",
	"jsonContent" };
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub

		return paramsKey;
	}
	
	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return "uploadFile";
	}
	
	
	@Override
	public File getFileArray() {
		// TODO Auto-generated method stub
		return fileArray;
	}

	private File fileArray;
	
	public void setFileArray(File fileArray) {
		this.fileArray = fileArray;
	}

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
						jo.put(contentKeys[i], String.valueOf(params[i]));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				tempParams = new Object[2];
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
