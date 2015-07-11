package com.platform.advertising.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapUtil {

	/**
	 * 将一个对象转换成Map
	 * 
	 * @params object 待转换的对象
	 * */
	public static Map<String, String> ObjectToMap(Object object) {

		Map<String, String> map = new HashMap<String, String>();
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
					map.put(fieldName, value.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	/** 将一个Map转换为对象 */
	public static Object MapToObject(Map<String, String> map, Class clazz) {
		Object result = null;
		try {
			result = clazz.newInstance();
			Method[] methods = result.getClass().getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("set")) {
					// 得到字段名
					String field = methodName.substring(3);
					String first = field.substring(0, 1);
					field = first.toLowerCase() + field.substring(1);
					String fieldType = clazz.getDeclaredField(field).getType()
							.getSimpleName();
					if ("String".equals(fieldType)) {
						String fieldValue = (String) map.get(field);
						method.invoke(result, fieldValue);
					} else if ("Long".equals(fieldType)) {
						Long fieldValue = Long.parseLong(map.get(field));
						method.invoke(result, fieldValue);
					} else if ("Double".equals(fieldType)) {
						Double fieldValue = Double.parseDouble(map.get(field));
						method.invoke(result, fieldValue);
					} else if ("Float".equals(fieldType)) {
						Float fieldValue = Float.parseFloat(map.get(field));
						method.invoke(result, fieldValue);
					} else if ("Integer".equals(fieldType)) {
						Integer fieldValue = Integer.parseInt(map.get(field));
						method.invoke(result, fieldValue);
					} else if ("Short".equals(fieldType)) {
						Short fieldValue = Short.parseShort(map.get(field));
						method.invoke(result, fieldValue);
					}

				}
			}
		} catch (Exception e) {
			LogUtil.error("MapToObject出现错误!");
			e.printStackTrace();
		}
		return result;
	}
}
