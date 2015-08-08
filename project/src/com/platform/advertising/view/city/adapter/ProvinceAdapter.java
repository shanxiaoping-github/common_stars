package com.platform.advertising.view.city.adapter;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.view.city.data.Province;

public class ProvinceAdapter extends BaseAdapter<Province> {

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.adress_item);
			holder = new Holder();
			holder.province = (TextView) arg1
					.findViewById(R.id.adress_item_title);
			arg1.setTag(holder);
		}
		holder = (Holder) arg1.getTag();
		TextView provinceTxt = holder.province;

		final Province province = getData(arg0);
		if (province.isSelect()) {
			provinceTxt.setBackgroundColor(getContext().getResources().getColor(
					R.color.blue));
			provinceTxt.setTextColor(getContext().getResources().getColor(R.color.white));
		} else {
			provinceTxt.setBackgroundColor(getContext().getResources().getColor(
					R.color.background));
			provinceTxt.setTextColor(getContext().getResources().getColor(R.color.shallowGray));

		}
		
		
		provinceTxt.setText(province.getTitle());

		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				cleanSelect();
				province.setSelect(true);
				notifyDataSetChanged();
				onItem(new Object[]{
						province	
				});
			}
		});
		
		return arg1;
	}

	class Holder {
		TextView province;
	}
	public void cleanSelect(){
		for(int i=0;i<getList().size();i++){
			Province province =  getList().get(i);
			province.setSelect(false);
		}
	}
	

}
