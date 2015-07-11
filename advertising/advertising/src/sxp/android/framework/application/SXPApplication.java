package sxp.android.framework.application;

import com.platform.advertising.util.SharedPreferencesUtil;

import sxp.android.framework.configuration.Configuration;
import sxp.android.framework.manager.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

/**
 * 程序类
 * 
 * @author xiaoping.shan
 * 
 */
public class SXPApplication extends Application {

	private static SXPApplication instance;// 实例
	private static Context context;// 上下文
	private static WindowManager wm = null;// 窗口管理类
	
//	public static final String SERVERURL = "http://121xc.com/service/call/";
	public static final String SERVERURL = "http://121.43.234.132/service/call/";

	private SXPRuntimeContext sxpRuntimeContext;// 运行时上下文
	private SXPConfigurationContext sxpConfigurationContext;// 持久化上下文

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();
		instance = this;// 获得实例
		context = getApplicationContext();// 获得上下文
		sxpRuntimeContext = new SXPRuntimeContext();
		sxpConfigurationContext = new SXPConfigurationContext(context, this);
		Configuration.getInstance();// 加载配置文件
		SharedPreferencesUtil.init(context, "setting");
	}

	/**
	 * 获取运行时上下文
	 * 
	 * @return
	 */
	public SXPRuntimeContext getSXPRuntimeContext() {
		if (sxpRuntimeContext == null) {
			sxpRuntimeContext = new SXPRuntimeContext();
		}
		return sxpRuntimeContext;

	}

	/**
	 * 获取持久化上下文
	 * 
	 * @return
	 */
	public SXPConfigurationContext getSXPConfigurationContext() {
		if (sxpConfigurationContext == null) {
			sxpConfigurationContext = new SXPConfigurationContext(context, this);
		}
		return sxpConfigurationContext;

	}

	/**
	 * 返回程序单列
	 * 
	 * @return
	 */
	public static SXPApplication getInstance() {
		return instance;
	}

	/**
	 * 获得程序上下文
	 * 
	 * @return
	 */
	public static Context getAppContext() {
		return SXPApplication.context;
	}

	/**
	 * 获得窗口管理
	 * 
	 * @param context
	 * @return
	 */
	public static WindowManager getWindowManager(Context context) {
		if (wm == null) {
			wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
		}
		return wm;
	}

	/**
	 * 获得屏幕宽
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWindowWidth(Context context) {
		WindowManager wm = getWindowManager(context);
		return wm.getDefaultDisplay().getWidth();
	}

	/**
	 * 获得屏幕高
	 * 
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWindowHeight(Context context) {
		WindowManager wm = getWindowManager(context);
		return wm.getDefaultDisplay().getHeight();
	}

	/**
	 * 退出程序
	 */
	public static void outApp(boolean isSafe) {
		ActivityManager.getInstance().finishActivity();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

}
