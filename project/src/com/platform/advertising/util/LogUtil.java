package com.platform.advertising.util;

import android.util.Log;

/**
 * 日志工具类
 * 
 * @author chenliang
 * @version v1.0
 * @date 2014-2-20
 * 
 */
public class LogUtil
{
	public static boolean DEBUG = true;
	public static final String TAG = "Base";

	public static void error(String message)
	{
		if (DEBUG)
			Log.e(TAG, message);
	}

	public static void debug(String message)
	{
		if (DEBUG)
			Log.d(TAG, message);
	}

	public static void info(String message)
	{
		if (DEBUG)
			Log.d(TAG, message);
	}
}
