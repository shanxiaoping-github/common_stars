package com.platform.advertising.ui;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.http.HttpMessageReplyClient;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.StringUtil;

/**
 * 信件回复
 * 
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.message_reply_layout)
public class MessageReplyActivity extends BaseActivity {
	
	@ID(value = R.id.back, isBindListener = true)
	private ImageButton back;

	@ID(value = R.id.message_reply_send, isBindListener = true)
	private TextView send;

	// @ID(value = R.id.message_reply_title)
	// private EditText title;

	@ID(value = R.id.message_reply_content)
	private EditText content;

	@RESOURE("id")
	private String id;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.message_reply_send:
			sendReply();
			break;
		case R.id.back:
			finishBase();
			break;

		}
	}

	/**
	 * 发送回复
	 */
	private void sendReply() {
		// String titleStr = title.getText().toString();
		String contentStr = content.getText().toString();
		// if(StringUtil.isEmpty(titleStr)){
		// showShortToast("标题不能为空");
		// }else

		if (StringUtil.isEmpty(contentStr)) {
			showShortToast("内容不能为空");
		} else {

			final HttpMessageReplyClient client = new HttpMessageReplyClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("回复失败");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast(client.getMessage());
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("回复失败");
					return false;
				}
			});
			client.setPramas(new Object[] {
					SharedPreferencesUtil.getString("mobile"), contentStr, id });
			ShowUtil.openHttpDialog("回复中...");
			client.submitRequest();
		}

	}

}
