package sxp.android.framework.ui;

import java.util.HashMap;
import java.util.Map;

import sxp.android.framework.ui.BaseActivity.BaseThread;
import sxp.android.framework.ui.BaseActivity.BaseThreadCallBack;
import sxp.android.framework.view.BaseProgressDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 碎片基类
 * 
 * @author Administrator
 * 
 */
public abstract class BaseFragment extends Fragment {

	private OnHeadlineSelectedListener listener;// 监听器
	private Context context;// 上下文
	private BaseProgressDialog progressDialog = null;
	protected Handler baseHandler;
	protected Map<String, Object> params = new HashMap<String, Object>();

	public OnHeadlineSelectedListener getListener() {
		return listener;
	}

	public void setListener(OnHeadlineSelectedListener listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		baseHandler = new Handler();
		progressDialog = new BaseProgressDialog(getActivity());
		progressDialog.setCanceledOnTouchOutside(false);
		return layout(inflater);// 布局;
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

	public void showShortToast(int pResId) {
		showShortToast(getString(pResId));
	}

	public void showLongToast(String pMsg) {
		Toast.makeText(getActivity(), pMsg, Toast.LENGTH_LONG).show();
	}

	public void showShortToast(String pMsg) {
		Toast.makeText(getActivity(), pMsg, Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		context = activity;
		listener = (OnHeadlineSelectedListener) activity;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		if (savedInstanceState == null) {// 数据初始化
			dataInit();
		} else {// 数据恢复
			dataRestore(savedInstanceState);
		}
		eventDispose();// 事件
	}

	/* 布局 */
	public abstract View layout(LayoutInflater inflater);

	/* 选中 */
	public void onSelect() {
	};

	/* 未选中 */
	public void onUnSelect() {
	};

	/* 数据初始化 */
	public void dataInit() {
	}

	/* 数据恢复 */
	public void dataRestore(Bundle savedInstanceState) {
	}

	/* 处理 */
	public void eventDispose() {
	}

	/* activity返回 */
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	}

	/* 获得上下文环境 */
	public Context getContext() {
		return context;
	}

	/**
	 * 管理类事件触发
	 * 
	 * @author Administrator
	 * 
	 */
	public interface OnHeadlineSelectedListener {
		public void onArticleSelected(Object[] param);

		public void back();
	}

	/**
	 * 按键处理
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (listener != null && keyCode == event.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			listener.back();// 返回
		}
		return true;
	}

}
