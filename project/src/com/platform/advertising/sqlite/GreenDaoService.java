package com.platform.advertising.sqlite;

import sxp.android.framework.application.SXPApplication;
import android.database.sqlite.SQLiteDatabase;

import com.platform.advertising.DaoMaster;
import com.platform.advertising.DaoMaster.DevOpenHelper;
import com.platform.advertising.DaoSession;

/**
 * 数据库服务器
 * 
 * @author xiaoping.shan
 *
 */
public class GreenDaoService {

	private static String dbStr = "sqlite-db";
	private static GreenDaoService daoService = null;

	public static GreenDaoService getInstance() {
		if (daoService == null) {
			daoService = new GreenDaoService();
		}
		return daoService;

	}

	private DaoSession daoSession = null;

	public DaoSession getDaoSession() {
		if (daoSession == null) {
			DevOpenHelper helper = new DaoMaster.DevOpenHelper(
					SXPApplication.getAppContext(), dbStr, null);
			SQLiteDatabase db = helper.getWritableDatabase();
			DaoMaster daoMaster = new DaoMaster(db);
		
			daoSession = daoMaster.newSession();
		}
		return daoSession;
	}

}
