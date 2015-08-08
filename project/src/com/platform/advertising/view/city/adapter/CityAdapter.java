package com.platform.advertising.view.city.adapter;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.view.city.data.City;

public class CityAdapter extends BaseAdapter<City> {

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.adress_item);
			holder = new Holder();
			holder.city = (TextView) arg1.findViewById(R.id.adress_item_title);
			arg1.setTag(holder);
		}
		holder = (Holder) arg1.getTag();
		TextView cityTxt = holder.city;

		final City city = getData(arg0);
		if (city.isSelect()) {
			cityTxt.setBackgroundColor(getContext().getResources().getColor(
					R.color.blue));
			cityTxt.setTextColor(getContext().getResources().getColor(R.color.white));
		} else {
			cityTxt.setBackgroundColor(getContext().getResources().getColor(
					R.color.background));
			cityTxt.setTextColor(getContext().getResources().getColor(R.color.shallowGray));

		}
		cityTxt.setText(city.getTitle());
		arg1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				cleanSelect();
				city.setSelect(true);
				notifyDataSetChanged();
				onItem(new Object[] { city });
			}
		});
		return arg1;
	}

	class Holder {
		TextView city;
	}

	public void cleanSelect() {
		for (int i = 0; i < getList().size(); i++) {
			City city = getList().get(i);
			city.setSelect(false);
		}
	}
}
