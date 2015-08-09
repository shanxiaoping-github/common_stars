package com.platform.advertising.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.MessageData;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import sxp.android.framework.util.TimeUtil;
/**
 * 消息adapter
 * @author shanxiaoping
 *
 */
public class MessageAdapter extends BaseAdapter<MessageData>{

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder=null;
		if(arg1 == null){
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.message_item_layout);
			holder = new Holder();
			holder.title = (TextView)arg1.findViewById(R.id.message_item_title);
			holder.time = (TextView)arg1.findViewById(R.id.message_item_time);
			holder.content = (TextView)arg1.findViewById(R.id.message_item_content);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		final MessageData msg = getData(arg0);
		holder.title.setText(msg.getTitle());
		holder.content.setText(msg.getContent());
		holder.time.setText(TimeUtil.getInstance().formatLong(msg.getCreateTime(), TimeUtil.DATE_PATTERN_6));
		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[]{
					msg	
				});
			}
		});
		return arg1;
	}
	class Holder{
		TextView title;
		TextView time;
		TextView content;
	}

}
