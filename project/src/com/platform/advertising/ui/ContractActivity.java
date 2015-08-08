package com.platform.advertising.ui;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.StringUtil;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpMessageSendClient;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

@LAYOUT(R.layout.contract_layout)
public class ContractActivity extends MyBaseActivity {

	@ID(value = R.id.tvSend, isBindListener = true)
	private TextView send;

	@ID(value = R.id.edtTitle)
	private EditText edtTitle;

	@ID(value = R.id.edtContent)
	private EditText edtContent;

	@RESOURE("receiveId")
	private String receiveId;

	@Override
	protected void layout() {
		// setContentView(R.layout.contract_layout);
		setBackButton();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tvSend:
			sendMessage();
			break;
		}
	}

	/**
	 * 发送信息
	 */
	private void sendMessage() {
		String titleStr = edtTitle.getText().toString();
		String contentStr = edtContent.getText().toString();
		if (StringUtil.isEmpty(titleStr)) {
			showShortToast("标题不能为空");
		} else if (StringUtil.isEmpty(contentStr)) {
			showShortToast("输入内容不能为空");
		} else {

			// mobile登陆用户号
			// title 标题
			// content 内容
			// receiverId 接收人用户号

			final HttpMessageSendClient client = new HttpMessageSendClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("发送失败");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast(client.getMessage());
					if (client.isSuccess()) {
						finishBase();
					}
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("发送失败");
					return false;
				}
			});
			client.setPramas(new Object[] {
					SharedPreferencesUtil.getString("mobile"), titleStr,
					contentStr, receiveId });

			ShowUtil.openHttpDialog("发送中...");
			client.submitRequest();

		}
	}
}
