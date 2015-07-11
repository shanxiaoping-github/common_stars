package com.platform.advertising.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json操作工具类
 * 
 * @author chenliang
 * @version v1.0
 * @date 2014-3-3
 */
public class JSONUtil {
	@SuppressWarnings("unchecked")
	public static Object jsonToObject(JSONObject jsonObject, Class clazz) {
		Object object = null;
		try {
			object = clazz.getConstructor(null).newInstance(null);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				String methodName = "set" + getFirstUpString(fieldName);

				Method method = null;
				String result = field.getType().getSimpleName();
				Object args = null;
				try {
					if (result.equals("Integer") || result.equals("int")) {
						method = clazz.getMethod(methodName, Integer.class);
						args = jsonObject.getInt(fieldName);
					} else if (result.equals("Long") || result.equals("long")) {
						method = clazz.getMethod(methodName, Long.class);
						args = jsonObject.getLong(fieldName);
					} else if (result.equals("Double")
							|| result.equals("double")) {
						method = clazz.getMethod(methodName, Float.class);
						args = jsonObject.getDouble(fieldName);
					} else if (result.equals("Float") || result.equals("float")) {
						method = clazz.getMethod(methodName, Float.class);
						args = jsonObject.getDouble(fieldName);
					} else if (result.equals("Boolean")
							|| result.equals("boolean")) {
						method = clazz.getMethod(methodName, Boolean.class);
						args = jsonObject.getBoolean(fieldName);
					} else if (result.equals("String")) {
						method = clazz.getMethod(methodName, String.class);
						args = jsonObject.getString(fieldName);
					}
					if (method != null) {
						method.invoke(object, args);
					}
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static JSONObject objectToJson(Object object) {
		JSONObject jsonObject = new JSONObject();

		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null) {
			for (Field field : fields) {
				String fieldName = field.getName();
				String methodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				try {
					Method getMethod = clazz
							.getDeclaredMethod(methodName, null);
					Object value = getMethod.invoke(object, null);
					if(value != null){
						jsonObject.put(fieldName, value.toString());
					}else{
						jsonObject.put(fieldName, "");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return jsonObject;
	}

	/** 将JSON字符串转换成Map */
	@SuppressWarnings("unchecked")
	public static Map<String, String> jsonToMap(JSONObject jsonObject) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				map.put(key, jsonObject.getString(key));
			}
			return map;
		} catch (JSONException e) {
			e.printStackTrace();
			LogUtil.error("JsonToMap出现错误!!!");
		}
		return null;
	}

	/** 把第一个字符大写 */
	private static String getFirstUpString(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String getName(String className, String fieldName) {
		return className + "." + fieldName;
	}
}
