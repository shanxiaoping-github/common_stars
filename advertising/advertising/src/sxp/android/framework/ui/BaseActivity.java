package sxp.android.framework.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import sxp.android.framework.manager.ActivityManager;
import sxp.android.framework.view.BaseProgressDialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.platform.advertising.R;

/**
 * ui基类
 * 
 * @author Administrator
 * 
 */
public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	private static final String TAG = "BaseActivity";

	private BaseProgressDialog progressDialog = null;
	protected Handler baseHandler;

	protected Context mContext;

	protected Map<String, Object> params = new HashMap<String, Object>();

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		ActivityManager.getInstance().push(this);
		mContext = this;
		baseHandler = new Handler();
		progressDialog = new BaseProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
		layout();// 对ui进行模板布局,以及一些ui的界面初始化化，不包含数据
		if (savedInstanceState == null) {// 没有保存数据和重建的情况下
			dataInit();
		} else {// 数据恢复
			dataRestore(savedInstanceState);
		}
		eventDispose();
	}

	public void setBackButton() {
		View view = findViewById(R.id.back);
		view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
		view.setVisibility(View.VISIBLE);
	}

	/** 重载父类的setContentView方法，目的是自动初始化组件 */
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initComponent();
	}

	/**
	 * 通过反射初始化控件，要求控件的名字和布局文件名字一致,不一致通过ID注解进行配置
	 * */
	protected void initComponent(){
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields){
			field.setAccessible(true);
			Class type = field.getType();
			if (View.class.isAssignableFrom(type)){
				String idName = field.getName();
				// 如果字段上有注解则采用注解的id
			    
				ID idAnnotation = field.getAnnotation(ID.class);
				if (idAnnotation != null) {
					idName = idAnnotation.value();
				}

				View view = findViewById(getResources().getIdentifier(idName,
						"id", getPackageName()));

				if (view != null) {
					if ("Button".equals(view.getClass().getSimpleName())) {
						view.setOnClickListener(this);
					}
					try {
						field.set(this,view);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 这里必须添加@Retention(RetentionPolicy.RUNTIME)，不然程序运行过程中获取不到注解
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	// 只能将注解加到方法上
	/**控件上的ID注解*/
	public @interface ID {
		String value();
	}
	/**
	 * 设置进度对话框消息
	 * 
	 * @param message
	 */
	public void setProgressDialogMessage(String message) {
		progressDialog.setMessageText(message);
	}

	/**
	 * 显示进度对话框
	 */
	public void showProgressDialog() {
		progressDialog.show();
		progressDialog.startAnim();
	}

	/**
	 * 显示进度对话框,带有消息
	 */
	public void showProgressDialog(String message) {
		setProgressDialogMessage(message);
		progressDialog.show();
		progressDialog.startAnim();
	}

	/**
	 * 关闭进度对话框
	 */
	public void closeProgressDialog() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	/**
	 * 向服务器发送数据时调用的方法
	 * 
	 * @param callBack
	 *            传入数据处理的回调接口
	 */
	public void send(BaseThreadCallBack callBack) {
		new BaseThread(callBack).start();
	}

	/**
	 * 处理线程回调接口
	 */
	public interface BaseThreadCallBack {
		/** 线程开始执行时回调方法 */
		public void handleEmpty();

		/** 发送数据到服务器调用方法,返回服务器回传数据 */
		public String sendData() throws Exception;

		/** 提交数据成功时回调方法,传入服务器返回的数据 */
		public void handleSuccess(String result) throws Exception;

		/** 提交数据失败时回调方法，传入错误的消息 */
		public void handleError(String errorMessage);
	}

	/**
	 * 公共的线程调用
	 */
	class BaseThread extends Thread {
		private BaseThreadCallBack callBack = null;
		private String result = null;

		public BaseThread(BaseThreadCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void run() {
			// 处理提交前的处理
			baseHandler.post(new Runnable() {
				public void run(){
					callBack.handleEmpty();
				}
			});
			try {
				// 在这里提交数据
				result = callBack.sendData();
			} catch (Exception e) {
				// 处理错误
				final String errorMessage = e.getMessage();
				// 处理错误
				baseHandler.post(new Runnable() {
					public void run() {
						callBack.handleError(errorMessage);
					}
				});
			}
			// 处理正确的结果
			if (result != null) {
				baseHandler.post(new Runnable() {
					public void run() {
						try {
							callBack.handleSuccess(result.toString());
						} catch (Exception e) {
							e.printStackTrace();
							showLongToast("解析数据失败!数据：" + result.toString());
						}
					}
				});
			} else {
				baseHandler.post(new Runnable() {
					public void run() {
						try {
							callBack.handleError("服务器罢工了!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}

		}
	}

	public void onClick(View v) {

	}

	/**
	 * 数据恢复
	 */
	protected void dataRestore(Bundle savedInstanceState) {
	}

	/**
	 * 数据初始化
	 */
	protected void dataInit() {
	};

	/**
	 * 对ui进行模板布局,以及一些ui的界面初始化化，不包含数据
	 */
	protected abstract void layout();

	/**
	 * 事件执行
	 */
	protected void eventDispose() {
	};

	/**
	 * 界面刷新
	 * 
	 * @param param
	 */
	protected void refesh(Object... param) {
	}

	/******************************** 【跳转到其他界面】 *******************************************/
	public void openActivity(Class<?> pClass) {
		openActivity(pClass, null);
	}

	public void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	public void openActivity(String pAction) {
		openActivity(pAction, null);
	}

	public void openActivity(String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	public void openActivityResult(Class<?> pClass, int requestCode) {
		openActivityResult(pClass, null, requestCode);
	}

	/******************************** 【跳转到子界面】 *******************************************/
	public void openActivityResult(Class<?> pClass) {
		openActivityResult(pClass, null);
	}

	public void openActivityResult(Class<?> pClass, Bundle pBundle) {
		openActivityResult(pClass, pBundle, 0);
	}

	public void openActivityResult(Class<?> pClass, Bundle pBundle,
			int requestCode) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivityForResult(intent, requestCode);
	}

	/******************************** 【界面提示】 *******************************************/

	public void showShortToast(int pResId) {
		showShortToast(getString(pResId));
	}

	public void showLongToast(String pMsg) {
		Toast.makeText(this, pMsg, Toast.LENGTH_LONG).show();
	}

	public void showShortToast(String pMsg) {
		Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();

	}

	/* 自定义清除 */
	public void finishBase() {

		ActivityManager.getInstance().peek(getClass().getName(), true);
		finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == event.KEYCODE_BACK) {
			finishBase();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	
	public void showAlertDialog(String message) {
		AlertDialog.Builder builder = new Builder(BaseActivity.this);
		builder.setMessage(message);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}
}
