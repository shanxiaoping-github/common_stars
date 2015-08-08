package com.platform.advertising.ui.regist;

import org.json.JSONObject;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.util.MathUtil;
import sxp.android.framework.util.StringUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.ui.MaterialActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.regist_layout)
public class RegistActivity extends MyBaseActivity implements OnClickListener {
	@ID(value = R.id.regist_sure,isBindListener = true)
	private View registSure;
	
	@ID(value = R.id.regist_back,isBindListener = true)
	private ImageButton back;
	
	@ID(value=R.id.btnCode,isBindListener = true)
	private Button btnCode;
	
	@ID(value=R.id.edtMobile,isBindListener = true)
	private EditText edtMobile;
	
	@ID(value=R.id.edtPassword,isBindListener = true)
	private EditText edtPassword;
	
	@ID(value=R.id.edtRePassword,isBindListener = true)
	private EditText edtRePassword;
	
	@ID(value=R.id.edtCode,isBindListener = true)
	private EditText edtCode;
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
	private void registSure(){
		final String mobile = edtMobile.getText().toString().trim();
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		final String code = edtCode.getText().toString().trim();
		if (TextUtils.isEmpty(mobile)) {
			showShortToast("请输入手机号码!");
			return;
		}
		if(!MathUtil.isMobileNumber(mobile)){
			showShortToast("手机号不合法!");
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
				//closeProgressDialog();
				ShowUtil.closeHttpDialog();
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("code")) {
					showShortToast(jsonObject.getString("message"));
					SharedPreferencesUtil.putString("mobile", mobile);
					SharedPreferencesUtil.putString("password", password);
					Bundle bundle = new Bundle();
					bundle.putInt("state", MaterialActivity.PER);
					openActivity(MaterialActivity.class,bundle);
					finishBase();
				} else {
					showShortToast(jsonObject.getString("message"));
				}
			}

			public void handleError(String errorMessage) {
				//closeProgressDialog();
				ShowUtil.closeHttpDialog();
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
				ShowUtil.openHttpDialog("注册中...");
				//showProgressDialog("正在提交信息...");
			}
		});
	}

	private void getCode() {
		final String mobile = edtMobile.getText().toString().trim();
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		if (StringUtil.isEmpty(mobile)) {
			showShortToast("请输入手机号码!");
			return;
		}
		if(!MathUtil.isMobileNumber(mobile)){
			showShortToast("手机号不合法!");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			showShortToast("请输入密码!");
			return;
		}
		if (StringUtil.isEmpty(rePassword)) {
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

			public void handleEmpty(){
				showLongToast("验证码获取中...");
				btnCode.setEnabled(false);
			}
		});
	}
}
