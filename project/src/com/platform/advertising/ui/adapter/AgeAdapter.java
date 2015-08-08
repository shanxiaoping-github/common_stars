package com.platform.advertising.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.Age;
import com.platform.advertising.R;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.ShowUtil;

/**
 * 年龄适配器
 * 
 * @author shanxiaoping
 *
 */
public class AgeAdapter extends BaseAdapter<Age> {

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.show_txt_layout);
			holder = new Holder();
			holder.showTxt = (TextView) arg1
					.findViewById(R.id.show_txt_content);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		final Age age = getData(arg0);
		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[]{
						age
				});
			}
		});
		
		holder.showTxt.setText(age.getName());
		return arg1;
	}

	class Holder {
		TextView showTxt;
	}

}
