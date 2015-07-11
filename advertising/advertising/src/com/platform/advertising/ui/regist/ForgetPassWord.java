package com.platform.advertising.ui.regist;

import org.json.JSONObject;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.platform.advertising.R;
import com.platform.advertising.util.HttpUtil;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseActivity.BaseThreadCallBack;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
public class ForgetPassWord extends BaseActivity implements OnClickListener {

	private ImageButton back;
	private Button btnCode;
	private EditText edtPassword, edtRePassword, edtCode;
	private String mobile;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.forget_password_layout);
		back = (ImageButton) findViewById(R.id.forget_password_back);
		back.setOnClickListener(this);
		findViewById(R.id.regist_sure).setOnClickListener(this);
		mobile = getIntent().getExtras().getString("mobile");
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.forget_password_back:
			finishBase();
			break;
		case R.id.regist_sure: // 确认
			submit();
			break;
		case R.id.btnCode:
			getCode();
			break;
		}
	}

	private void submit() {
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		final String code = edtCode.getText().toString().trim();
		if (TextUtils.isEmpty(password)) {
			showShortToast("请输入新密码!");
			return;
		}
		if (TextUtils.isEmpty(rePassword)) {
			showShortToast("请输入确认新密码!");
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
				params.put("newPassword", password);
				params.put("verificationCode", code);
				return HttpUtil.post("forgetPassModify", params);
			}

			public void handleSuccess(String result) throws Exception {
				closeProgressDialog();
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("code")) {
					showShortToast(jsonObject.getString("message"));
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
				showProgressDialog("正在提交数据...");
			}
		});
	}

	private void getCode() {
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		if (TextUtils.isEmpty(password)) {
			showShortToast("请输入新密码!");
			return;
		}
		if (TextUtils.isEmpty(rePassword)) {
			showShortToast("请输入确认新密码!");
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
				if (jsonObject.getBoolean("code")) {
					showShortToast(jsonObject.getString("message"));
					edtPassword.setEnabled(false);
					edtRePassword.setEnabled(false);
				} else {
					showShortToast(jsonObject.getString("message"));
					btnCode.setEnabled(true);
				}
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
