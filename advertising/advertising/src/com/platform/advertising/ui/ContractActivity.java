package com.platform.advertising.ui;

import org.json.JSONObject;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseActivity.BaseThreadCallBack;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.platform.advertising.R;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

public class ContractActivity extends BaseActivity {

	private String originalId = null;
	private EditText edtContent;

	@Override
	protected void layout() {
		setContentView(R.layout.contract_layout);
		setBackButton();

		originalId = getIntent().getExtras().getString("originalId");
		findViewById(R.id.tvSend).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						final String content = edtContent.getText().toString();
						if (TextUtils.isEmpty(content)) {
							showShortToast("回复内容不能为空!");
							return;
						}
						send(new BaseThreadCallBack() {

							public String sendData() throws Exception {
								params.put("mobile", SharedPreferencesUtil
										.getString("mobile"));
								params.put("originalId", originalId);
								params.put("content", content);
								return HttpUtil.post("messageReply", params);
							}

							public void handleSuccess(String result)
									throws Exception {
								closeProgressDialog();
								JSONObject jsonObject = new JSONObject(result);
								if (jsonObject.getBoolean("code")) {
									showShortToast("删除成功!");
									ShowUtil.showDialog(ContractActivity.this,
											"回复成功！", 2000);
									finish();
								} else {
									showShortToast(jsonObject
											.getString("message"));
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
				});
	}

}
