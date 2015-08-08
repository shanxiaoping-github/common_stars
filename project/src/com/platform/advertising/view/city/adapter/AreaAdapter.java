package com.platform.advertising.view.city.adapter;

import sxp.android.framework.adapter.BaseAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.util.ShowUtil;
import com.platform.advertising.view.city.data.Area;

public class AreaAdapter extends BaseAdapter<Area>{

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if (arg1 == null) {
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.adress_item);
			holder = new Holder();
			holder.area = (TextView) arg1
					.findViewById(R.id.adress_item_title);
			arg1.setTag(holder);
		}
		holder = (Holder) arg1.getTag();
		TextView areaTxt = holder.area;

		final Area area = getData(arg0);
		if(area.isSelect()){
			areaTxt.setBackgroundColor(getContext().getResources().getColor(R.color.blue));
			areaTxt.setTextColor(getContext().getResources().getColor(R.color.white));
		}else{
			areaTxt.setBackgroundColor(getContext().getResources().getColor(R.color.background));
			areaTxt.setTextColor(getContext().getResources().getColor(R.color.shallowGray));
		}
		areaTxt.setText(area.getTitle());
		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				cleanSelect();
				area.setSelect(true);
				notifyDataSetChanged();
				onItem(new Object[]{
					area	
				});
			}
		});
		return arg1;
	}

	class Holder {
		TextView area;
	}
	
	public void cleanSelect(){
		for(int i=0;i<getList().size();i++){
			Area area=  getList().get(i);
			area.setSelect(false);
		}
	}
}
