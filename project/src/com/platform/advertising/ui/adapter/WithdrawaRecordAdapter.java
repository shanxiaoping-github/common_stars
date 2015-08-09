package com.platform.advertising.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.WithdrawaData;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.util.ShowUtil;

public class WithdrawaRecordAdapter extends BaseAdapter<WithdrawaData>{

	@Override
	public View view(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if(arg1 == null){
			arg1 = ShowUtil.LoadXmlView(getContext(),R.layout.withdrawa_record_item);
			holder = new Holder();
			holder.bank = (TextView)arg1.findViewById(R.id.bank);
			holder.money =(TextView) arg1.findViewById(R.id.money);
			holder.time = (TextView)arg1.findViewById(R.id.time);
			holder.name = (TextView)arg1.findViewById(R.id.name);
			arg1.setTag(holder);
		}else{
			holder = (Holder)arg1.getTag();
		}
		WithdrawaData withdrawaData = getData(arg0);
		holder.bank.setText(withdrawaData.getBank()+"-尾号"+withdrawaData.getAccount().substring(withdrawaData.getAccount().length()-4));
		holder.money.setText("￥"+withdrawaData.getAmount());
		holder.time.setText(withdrawaData.getPaymentDate());
		holder.name.setText(withdrawaData.getPayer());
		return arg1;
	}
	class Holder{
		TextView bank;
		TextView money;
		TextView time;
		TextView name;
	}

}
