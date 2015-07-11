package com.platform.advertising.ui.regist;

import org.json.JSONObject;

import sxp.android.framework.ui.BaseActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.platform.advertising.R;
import com.platform.advertising.ui.MaterialActivity;
import com.platform.advertising.util.HttpUtil;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
public class RegistActivity extends BaseActivity implements OnClickListener {
	private View registSure;
	private ImageButton back;
	private Button btnCode;
	private EditText edtMobile, edtPassword, edtRePassword, edtCode;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.regist_layout);
		registSure = findViewById(R.id.regist_sure);
		back = (ImageButton) findViewById(R.id.regist_back);
		back.setOnClickListener(this);
		registSure.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regist_sure:
			registSure();
			break;
		case R.id.regist_back:
			finishBase();
			break;
		case R.id.btnCode:
			getCode();
			break;
		}
	}

	/**
	 * 注册确认
	 */
	private void registSure() {
		final String mobile = edtMobile.getText().toString().trim();
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		final String code = edtCode.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showShortToast("请输入手机号码!");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showShortToast("请输入密码!");
			return;
		}
		if (TextUtils.isEmpty(rePassword)) {
			showShortToast("请输入确认密码!");
			return;
		}
		if (!TextUtils.equals(password, rePassword)) {
			showShortToast("两次输入密码不一致!");
			return;
		}
		if (TextUtils.isEmpty(code)) {
			showShortToast("请输入验证码!");
			return;
		}
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("mobile", mobile);
				params.put("password", password);
				params.put("verificationCode", code);
				return HttpUtil.post("register", params);
			}

			public void handleSuccess(String result) throws Exception {
				closeProgressDialog();
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("code")) {
					showShortToast(jsonObject.getString("message"));
					openActivity(MaterialActivity.class);
					finishBase();
				} else {
					showShortToast(jsonObject.getString("message"));
				}
			}

			public void handleError(String errorMessage) {
				closeProgressDialog();
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
				showProgressDialog("正在提交信息...");
			}
		});
	}

	private void getCode() {
		final String mobile = edtMobile.getText().toString().trim();
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showShortToast("请输入手机号码!");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showShortToast("请输入密码!");
			return;
		}
		if (TextUtils.isEmpty(rePassword)) {
			showShortToast("请输入确认密码!");
			return;
		}
		if (!TextUtils.equals(password, rePassword)) {
			showShortToast("两次输入密码不一致!");
			return;
		}
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("mobile", mobile);
				return HttpUtil.post("getSmsVerificationCode", params);
			}
			public void handleSuccess(String result) throws Exception {
				JSONObject jsonObject = new JSONObject(result);
				showShortToast(jsonObject.getString("message"));
				btnCode.setEnabled(true);
			}
			public void handleError(String errorMessage) {
				btnCode.setEnabled(true);
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
				btnCode.setEnabled(false);
			}
		});
	}
}
