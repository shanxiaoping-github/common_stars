package com.platform.advertising.view.city.data;

import sxp.android.framework.data.BaseData;

/**
 * 地址接口
 * @author xiaoping.shan
 *
 */
public interface Adress extends BaseData {
	/**
	 * 省
	 */
	public static final int PROVINCE = 1;
	/**
	 * 市
	 */
	public static final int CITY = 2;
	/**
	 * 区
	 */
	public static final int AREA = 3;
	/**
	 * 获得地址类型
	 * @return
	 */
	abstract int getAdressType();

}
