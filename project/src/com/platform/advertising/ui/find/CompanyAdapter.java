package com.platform.advertising.ui.find;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.ui.data.CompanyData;
import com.platform.advertising.ui.find.data.Company;

public class CompanyAdapter extends BaseAdapter<CompanyData> {
	

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder;
		if (arg1 == null) {
			holder = new Holder();
			arg1 = ShowUtil.LoadXmlView(getContext(), R.layout.company_item);

			holder.companyTitle = (TextView) arg1
					.findViewById(R.id.company_item_title);
			arg1.setTag(holder);
		} else {

			holder = (Holder) arg1.getTag();
		}

		final CompanyData companyData = getList().get(arg0);
		holder.companyTitle.setText(companyData.getCompanyName());

		arg1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[] {

						companyData });
			}
		});
		return arg1;
	}

	class Holder {
		TextView companyTitle;
	}

}
