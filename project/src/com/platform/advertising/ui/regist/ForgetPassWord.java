package com.platform.advertising.ui.regist;

import org.json.JSONObject;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
@LAYOUT(R.layout.forget_password_layout)
public class ForgetPassWord extends MyBaseActivity implements OnClickListener {

	@ID(value = R.id.forget_password_back,isBindListener=true)
	private ImageButton back;
	
	@ID(value=R.id.btnCode,isBindListener = true)
	private Button btnCode;
	
	@ID(value=R.id.edtPassword,isBindListener = true)
	private EditText edtPassword;
	
	@ID(value = R.id.edtRePassword,isBindListener = true)
	private EditText edtRePassword;
	
	@ID(value = R.id.edtCode,isBindListener = true)
	private EditText edtCode;
	
	@ID(value = R.id.forget_password_sure,isBindListener = true)
	private View sure;
	
	private String mobile;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		mobile = getIntent().getExtras().getString("mobile");
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.forget_password_back:
			finishBase();
			break;
		case R.id.forget_password_sure: // 确认
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
				//closeProgressDialog();
				ShowUtil.closeHttpDialog();
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("code")) {
					showShortToast(jsonObject.getString("message"));
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
				ShowUtil.openHttpDialog("密码重置中...");
				//showProgressDialog("正在提交数据...");
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
