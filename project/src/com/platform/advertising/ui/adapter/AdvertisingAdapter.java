package com.platform.advertising.ui.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.AdvertisingData;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
/**
 * 广告适配器
 * @author shanxiaoping
 *
 */
public class AdvertisingAdapter extends BaseAdapter<AdvertisingData>{

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if(arg1 == null){
			holder = new Holder();
			arg1 = ShowUtil.LoadXmlView(getContext(),R.layout.advertising_market_layout_item);
			holder.companyName = (TextView)arg1.findViewById(R.id.textView2);
			holder.adverTitle = (TextView)arg1.findViewById(R.id.textView1);
		    arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		final AdvertisingData adverData = getData(arg0);
		holder.adverTitle.setText(adverData.getTitle());
		holder.companyName.setText(adverData.getCompany().getCompanyName());
		arg1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[]{
					adverData	
				});
				
			}
		});
		//holder.number.setText(text);
		return arg1;
	}
	class Holder{
		TextView adverTitle;
		TextView companyName;
	}

}
