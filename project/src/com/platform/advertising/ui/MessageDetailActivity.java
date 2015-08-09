package com.platform.advertising.ui;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.TimeUtil;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpMessageReplyClient;
import com.platform.advertising.ui.data.MessageData;
import com.platform.advertising.util.ShowUtil;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.activity_messagedetail)
public class MessageDetailActivity extends MyBaseActivity implements
		OnClickListener {

	@ID(value = R.id.message_name)
	private TextView name;

	@ID(value = R.id.message_title)
	private TextView title;

	@ID(value = R.id.message_time)
	private TextView time;

	@ID(value = R.id.message_content)
	private TextView tvContent;

	@ID(value = R.id.message_reply, isBindListener = true)
	private TextView relpy;

	@RESOURE("message")
	private MessageData messageData;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.activity_messagedetail);
		setBackButton();
		name.setText(messageData.getSender().getName());
		title.setText(messageData.getTitle());
		time.setText(TimeUtil.getInstance().formatLong(
				messageData.getCreateTime(), TimeUtil.DATE_PATTERN_6));
		tvContent.setText(messageData.getContent());
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.message_reply:
			replyMessage();
			break;
		}
	}

	/**
	 * 回复信息
	 */
	private void replyMessage() {

		Bundle bundle = new Bundle();
		bundle.putString("id", messageData.getId());
		openActivity(MessageReplyActivity.class, bundle);

	}

}
