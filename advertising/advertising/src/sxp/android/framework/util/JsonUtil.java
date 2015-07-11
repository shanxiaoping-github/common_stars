package sxp.android.framework.util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * json工厂
 * 
 * @author xiaoping.shan
 *
 */
public class JsonUtil {
	/**
	 * 获取json数据
	 * @param jo
	 * @param key 键值
	 * @return
	 */
	public static String getJsonString(JSONObject jo, String key) {
		String value = "";
		try {
			value = jo.getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
