package com.platform.advertising.ui.login;

import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.MathUtil;
import sxp.android.framework.util.StringUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.Age;
import com.platform.advertising.Occupation;
import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpLoginClient;
import com.platform.advertising.ui.home.HomePageActivity;
import com.platform.advertising.ui.regist.ForgetPassWord;
import com.platform.advertising.ui.regist.RegistActivity;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 登录界面
 */
@LAYOUT(R.layout.login_layout)
public class LoginActivity extends MyBaseActivity implements OnClickListener {
	/**
	 * 用户名
	 */
	private EditText userName;
	/**
	 * 密码
	 */
	private EditText passWord;
	/**
	 * 注册
	 */
	private Button registBtn;
	/**
	 * 登录
	 */
	private Button loginBtn;
	/**
	 * 记住密码
	 */
	private ImageButton rememberBtn;
	/**
	 * 忘记密码
	 */
	private TextView forgetPassWordBtn;

	// private boolean isRemember = false;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.login_layout);
		userName = (EditText) findViewById(R.id.login_input_user_name);
		passWord = (EditText) findViewById(R.id.login_input_user_password);
		registBtn = (Button) findViewById(R.id.login_input_regist);
		loginBtn = (Button) findViewById(R.id.login_input_login);
		// rememberBtn = (ImageButton)
		// findViewById(R.id.login_remember_account);
		forgetPassWordBtn = (TextView) findViewById(R.id.login_forget_password);
		registBtn.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		// rememberBtn.setOnClickListener(this);
		forgetPassWordBtn.setOnClickListener(this);
		// 加载职业
		Occupation.loadList();
		// 加载年龄
		Age.loadList();

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_input_regist:// 注册
			regist();
			break;
		case R.id.login_input_login:// 登录
			login();
			break;
		// case R.id.login_remember_account:// 记住账号
		// rememberAccount();
		// break;
		case R.id.login_forget_password:// 忘记密码
			forgetPassWord();
			break;
		}

	}

	// private void setRemember() {
	// if (isRemember) {
	// rememberBtn.setImageResource(R.drawable.login_input_remember_1);
	// } else {
	// rememberBtn.setImageResource(R.drawable.login_input_remember);
	// }
	// }

	private void regist() {
		openActivity(RegistActivity.class);

	}

	private void login() {
		final String mobile = userName.getText().toString().trim();
		final String password = passWord.getText().toString().trim();
		if (StringUtil.isEmpty(mobile)) {
			showShortToast("用户名不能为空!");
		} else if (!MathUtil.isMobileNumber(mobile)) {
			showShortToast("手机号不合法!");
		} else if (StringUtil.isEmpty(password)) {
			showShortToast("密码不能为空!");
		} else {
			final HttpLoginClient loginClient = new HttpLoginClient();
			loginClient.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("登陆超时!");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showLongToast(loginClient.getMessage());
					if (loginClient.isSuccess()) {
						SharedPreferencesUtil.putString("mobile", mobile);
						SharedPreferencesUtil.putString("password", password);
						openActivity(HomePageActivity.class);
						finishBase();
					}
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("登陆失败!");
					return false;
				}
			});
			loginClient.setPramas(new Object[] { mobile, password });
			ShowUtil.openHttpDialog("登陆中...");
			loginClient.submitRequest();

		}
	}

	private void forgetPassWord() {
		final String mobile = userName.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showShortToast("手机号不能为空!");
			return;
		}
		Bundle bundle = new Bundle();
		bundle.putString("mobile", mobile);
		openActivity(ForgetPassWord.class, bundle);
	}

}
