package com.platform.advertising.framework;

import java.util.HashMap;
import java.util.Map;

import sxp.android.framework.ui.BaseActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.platform.advertising.R;

public class MyBaseActivity extends BaseActivity {
	private BaseProgressDialog progressDialog = null;
	protected Handler baseHandler;
	protected Context mContext;
	protected Map<String, Object> params = new HashMap<String, Object>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		baseHandler = new Handler();
		progressDialog = new BaseProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
	}

	public void setBackButton() {
		View view = findViewById(R.id.back);
		view.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				finishBase();
			}
		});
		view.setVisibility(View.VISIBLE);
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
				public void run() {
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

}
