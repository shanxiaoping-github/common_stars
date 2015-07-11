package com.platform.advertising.ui.find;

import sxp.android.framework.adapter.MyBaseAdapter;
import sxp.android.framework.util.ShowUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.find.data.Company;

public class CompanyAdapter extends MyBaseAdapter<Company> {

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

		final Company company = getList().get(arg0);
		holder.companyTitle.setText(company.getTitle());

		arg1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				onItem(new Object[] {

				company });
			}
		});
		return arg1;
	}

	class Holder {
		TextView companyTitle;
	}

}
