package com.platform.advertising.util;

import sxp.android.framework.configuration.Configuration;

/**
 * 图片工具类
 * @author shanxiaoping
 *
 */
public class ImageUtil {
	/**
	 * 获得资源路径
	 * @param imagePath
	 * @return
	 */
	public static String getImageUrl(String imagePath){
		return Configuration.getInstance().getProperty("res")+imagePath;
	}

}
