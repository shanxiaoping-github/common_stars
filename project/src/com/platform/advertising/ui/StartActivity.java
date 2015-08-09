package com.platform.advertising.ui;

import java.util.Timer;
import java.util.TimerTask;

import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.StringUtil;

import com.platform.advertising.R;
import com.platform.advertising.http.HttpLoginClient;
import com.platform.advertising.ui.home.HomePageActivity;
import com.platform.advertising.ui.login.LoginActivity;
import com.platform.advertising.util.SharedPreferencesUtil;

/**
 * 开始界面
 * 
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.start_layout)
public class StartActivity extends BaseActivity {

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		super.layout();
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				login();
			}
		}, 4000);

	}

	/**
	 * 登录
	 */
	private void login() {
		String mobileStr = SharedPreferencesUtil.getString("mobile");
		String passwordStr = SharedPreferencesUtil.getString("password");
		if (StringUtil.isEmpty(mobileStr) || StringUtil.isEmpty(passwordStr)) {
			openActivity(LoginActivity.class);
			finishBase();
		} else {
			final HttpLoginClient loginClient = new HttpLoginClient();
			loginClient.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					openActivity(LoginActivity.class);
					finishBase();
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					if (loginClient.isSuccess()) {
						openActivity(HomePageActivity.class);
					} else {
						openActivity(LoginActivity.class);
					}
					finishBase();
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					openActivity(LoginActivity.class);
					finishBase();
					return false;
				}
			});
			loginClient.setPramas(new Object[] { mobileStr, passwordStr });
			loginClient.submitRequest();

		}

	}

}
