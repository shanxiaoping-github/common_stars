package com.platform.advertising.ui.adapter;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.Occupation;
import com.platform.advertising.R;

/*
 * 职业适配器
 */
public class JobAdapter extends BaseAdapter<Occupation> {

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		Holder holder;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.show_txt_layout);
			holder = new Holder();
			holder.showTxt = (TextView) arg1
					.findViewById(R.id.show_txt_content);
			arg1.setTag(holder);
		} else {
			holder = (Holder) arg1.getTag();
		}
		final Occupation occ = getData(arg0);
		arg1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[] { occ });
			}
		});

		holder.showTxt.setText(occ.getName());
		return arg1;
	}

	class Holder {
		TextView showTxt;
	}

}
