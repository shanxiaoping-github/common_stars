package sxp.android.framework.application;

import java.util.HashMap;
import java.util.Map;

/**
 * 运行时上下文
 * @author xiaoping.shan
 *
 */
public class SXPRuntimeContext implements SXPContext{
	private Map<String,Object> map;

	public void savaData(String key, Object value) {
		// TODO Auto-generated method stub
		if(map==null){
			map = new HashMap<String, Object>();
		}
		
		map.put(key, value);
		
	}

	public Object getData(String key) {
		// TODO Auto-generated method stub
		if(map == null){
			return null;
		}
		if(map.containsKey(key)){
			return map.get(key);
		}
		return null;
	}

}
