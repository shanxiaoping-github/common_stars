package com.platform.advertising.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.params.CookieSpecPNames;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import sxp.android.framework.application.SXPApplication;
import android.text.TextUtils;

/**
 * HTTP协议操作工具类
 * 
 * @author chenliang
 * @version v1.0
 * @date 2014-2-20
 * 
 */
public class HttpUtil {

	/**
	 * @param urlKey
	 *            具体要访问的资源urlKey的键
	 * @param map
	 *            传给服务器的数据，以键值对的方式
	 * @return 服务器返回的数据,发生错误时返回null
	 * @throws Exception
	 */
	public static String post(String interCode, Map<String, Object> map) {
		LogUtil.error("HttpUtil提交的数据:" + "  " + interCode + map.toString());
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(SXPApplication.SERVERURL);
			String cookieValue = SharedPreferencesUtil.getString("value");
			if (!TextUtils.isEmpty(cookieValue)) {
				CookieStore cookieStore = new BasicCookieStore();
				BasicClientCookie cookie = new BasicClientCookie(
						SharedPreferencesUtil.getString("cookieName"),
						cookieValue);
				cookie.setDomain(SharedPreferencesUtil.getString("domain"));
				cookie.setPath(SharedPreferencesUtil.getString("path"));
				cookieStore.addCookie(cookie);
				httpClient.setCookieStore(cookieStore);
			}
			httpClient.getParams().setParameter(
					CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
			HttpParams params = new BasicHttpParams();
			// 设置连接超时时间为10秒
			HttpConnectionParams.setConnectionTimeout(params, 10000);
			// 从服务器获取响应数据等待超时时间
			HttpConnectionParams.setSoTimeout(params, 5000);
			httpPost.setParams(params);
			// 在执行之前必须设置参数
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("interCode",
					interCode == null ? "" : String.valueOf(interCode)));
			JSONObject jsonContent = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Object value = entry.getValue();
				jsonContent.put(entry.getKey(), value == null ? "" : String.valueOf(value));
			}
			
			parameters.add(new BasicNameValuePair("jsonContent",
					jsonContent.toString()));

			HttpEntity httpEntity = new UrlEncodedFormEntity(parameters,
					"UTF-8");
			httpPost.setEntity(httpEntity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				List<Cookie> cookies = ((AbstractHttpClient) httpClient)
						.getCookieStore().getCookies();
				if (!cookies.isEmpty()) {
					Cookie cookie = cookies.get(0);
					String value = cookie.getValue();
					String path = cookie.getPath();
					String domain = cookie.getDomain();
					String cookName = cookie.getName();
					SharedPreferencesUtil.putString("value", value);
					SharedPreferencesUtil.putString("path", path);
					SharedPreferencesUtil.putString("domain", domain);
					SharedPreferencesUtil.putString("cookieName", cookName);
				}

				httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, "UTF-8");
			}
		} catch (Exception e) {
			throw new BaseException(e);
		}
		LogUtil.error("HttpUtil返回的数据:" + result);
		return result;
	}

	public static String postFile(String url, Map<String, Object> map,
			String fileName, String[] files) {
		LogUtil.debug("HttpUtil提交的数据:" + url + map.toString() + " fileName=" + fileName + "files=" + files.toString());
		String result = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			// url = "http://192.168.0.150:8080/app_attendance.action";
			HttpPost httpPost = new HttpPost(url);
			String cookieValue = SharedPreferencesUtil.getString("value");
			if (!TextUtils.isEmpty(cookieValue)) {
				CookieStore cookieStore = new BasicCookieStore();
				BasicClientCookie cookie = new BasicClientCookie(
						SharedPreferencesUtil.getString("cookieName"),
						cookieValue);
				cookie.setDomain(SharedPreferencesUtil.getString("domain"));
				cookie.setPath(SharedPreferencesUtil.getString("path"));
				cookieStore.addCookie(cookie);
				httpClient.setCookieStore(cookieStore);
			}
			httpClient.getParams().setParameter(
					CookieSpecPNames.SINGLE_COOKIE_HEADER, true);
			HttpParams params = new BasicHttpParams();
			// 设置连接超时时间为10秒
			HttpConnectionParams.setConnectionTimeout(params, 10000);
			// 从服务器获取响应数据等待超时时间
			HttpConnectionParams.setSoTimeout(params, 5000);
			httpPost.setParams(params);

			MultipartEntity entity = new MultipartEntity();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Object value = entry.getValue();
				entity.addPart(entry.getKey(), new StringBody(
						value == null ? "" : String.valueOf(value)));
			}
			for (String file : files) {
				entity.addPart(fileName, new FileBody(new File(file)));
			}
			httpPost.setEntity(entity);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() == 200) {
				List<Cookie> cookies = ((AbstractHttpClient) httpClient)
						.getCookieStore().getCookies();
				if (!cookies.isEmpty()) {
					Cookie cookie = cookies.get(0);
					String value = cookie.getValue();
					String path = cookie.getPath();
					String domain = cookie.getDomain();
					String cookName = cookie.getName();
					SharedPreferencesUtil.putString("value", value);
					SharedPreferencesUtil.putString("path", path);
					SharedPreferencesUtil.putString("domain", domain);
					SharedPreferencesUtil.putString("cookieName", cookName);
				}

				HttpEntity entity2 = httpResponse.getEntity();
				result = EntityUtils.toString(entity2, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(e);
		}
		LogUtil.error("HttpUtil返回的数据:" + result);
		return result;
	}
}
