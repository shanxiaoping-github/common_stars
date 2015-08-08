package com.platform.advertising.ui;

import org.json.JSONObject;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;

/**
 * 
 * @author xiaoping.shan 忘记密码
 */
@LAYOUT(R.layout.activity_change_password)
public class ChangePwdActivity extends MyBaseActivity implements OnClickListener {
	@ID(value = R.id.btnCode,isBindListener = true)
	private Button btnCode;
	@ID(R.id.edtOldPassword)
	private EditText edtOldPassword;
    @ID(R.id.edtPassword)
	private EditText edtPassword;
    @ID(R.id.edtRePassword)
	private EditText edtRePassword;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		//setContentView(R.layout.activity_change_password);
		setBackButton();
		findViewById(R.id.regist_sure).setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regist_sure: // 确认
			submit();
			break;
		}
	}

	private void submit() {
		final String oldPassword = edtOldPassword.getText().toString().trim();
		final String password = edtPassword.getText().toString().trim();
		final String rePassword = edtRePassword.getText().toString().trim();
		if (TextUtils.isEmpty(oldPassword)) {
			showShortToast("请输入原密码!");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showShortToast("请输入新密码!");
			return;
		}
		if (TextUtils.isEmpty(rePassword)) {
			showShortToast("请输入确认新密码!");
			return;
		}
		if (!TextUtils.equals(oldPassword,
				SharedPreferencesUtil.getString("password"))) {
			showShortToast("原密码错误!");
			return;
		}
		if (TextUtils.equals(oldPassword, password)) {
			showShortToast("原密码和新密码不能一样!");
			return;
		}
		if (!TextUtils.equals(password, rePassword)) {
			showShortToast("两次输入密码不一致!");
			return;
		}
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("mobile", SharedPreferencesUtil.getString("mobile"));
				params.put("oldPassword", oldPassword);
				params.put("newPassword", password);
				return HttpUtil.post("passModify", params);
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

}
