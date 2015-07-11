package com.platform.advertising.ui.login;

import org.json.JSONObject;

import sxp.android.framework.ui.BaseActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.home.HomePageActivity;
import com.platform.advertising.ui.regist.ForgetPassWord;
import com.platform.advertising.ui.regist.RegistActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;

/**
 * 
 * @author xiaoping.shan 登录界面
 */
public class LoginActivity extends BaseActivity implements OnClickListener {
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

	private boolean isRemember = false;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_layout);
		userName = (EditText) findViewById(R.id.login_input_user_name);
		passWord = (EditText) findViewById(R.id.login_input_user_password);
		registBtn = (Button) findViewById(R.id.login_input_regist);
		loginBtn = (Button) findViewById(R.id.login_input_login);
		rememberBtn = (ImageButton) findViewById(R.id.login_remember_account);
		forgetPassWordBtn = (TextView) findViewById(R.id.login_forget_password);
		registBtn.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		rememberBtn.setOnClickListener(this);
		forgetPassWordBtn.setOnClickListener(this);

		isRemember = SharedPreferencesUtil.getBoolean("isRemember");
		setRemember();

		if (isRemember) {
			openActivity(HomePageActivity.class);
			finish();
		}
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
		case R.id.login_remember_account:// 记住账号
			rememberAccount();
			break;
		case R.id.login_forget_password:// 忘记密码
			forgetPassWord();
			break;
		}

	}

	private void setRemember() {
		if (isRemember) {
			rememberBtn.setImageResource(R.drawable.login_input_remember_1);
		} else {
			rememberBtn.setImageResource(R.drawable.login_input_remember);
		}
	}

	private void regist() {
		openActivity(RegistActivity.class);

	}

	private void login() {
		final String mobile = userName.getText().toString().trim();
		final String password = passWord.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showShortToast("用户名不能为空!");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showShortToast("密码不能为空!");
			return;
		}
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("mobile", mobile);
				params.put("password", password);
				return HttpUtil.post("login", params);
			}

			public void handleSuccess(String result) throws Exception {
				closeProgressDialog();
				try {
					JSONObject jsonObject = new JSONObject(result);
					if (jsonObject.getBoolean("code")){
						showLongToast(jsonObject.getString("message"));
						SharedPreferencesUtil.putString("mobile", mobile);
						SharedPreferencesUtil.putString("password", password);
						SharedPreferencesUtil.putBoolean("isRemember",
								isRemember);
						openActivity(HomePageActivity.class);
						finish();
					} else {
						showLongToast(jsonObject.getString("message"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					showLongToast("解析数据失败!");
				}
			}

			public void handleError(String errorMessage) {
				closeProgressDialog();
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
				showProgressDialog("正在登陆...");
			}
		});
	}

	private void rememberAccount() {
		isRemember = !isRemember;
		setRemember();
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
