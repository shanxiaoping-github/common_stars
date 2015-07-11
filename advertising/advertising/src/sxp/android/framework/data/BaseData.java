package sxp.android.framework.data;

import java.io.Serializable;

import org.json.JSONObject;
/**
 * 基础数据类
 * @author Administrator
 *
 */
public interface BaseData extends Serializable{
	public void parser(JSONObject jo);//解析数据
	public JSONObject page();//打包数据
	
	
}
