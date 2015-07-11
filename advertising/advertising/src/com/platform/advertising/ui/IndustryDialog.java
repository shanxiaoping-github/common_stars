package com.platform.advertising.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.platform.advertising.R;

public class IndustryDialog extends Dialog {

	private Context context;
	private CallBack clickListener;
	List<String> data = new ArrayList<String>();

	public IndustryDialog(Context context, CallBack clickListener) {
		super(context, R.style.Dialog);
		this.context = context;
		this.clickListener = clickListener;
		data.add("农业");
		data.add("制造");
		data.add("建筑");
		data.add("医疗");
		data.add("运输");
		data.add("信息");
		data.add("金融");
		data.add("其他");
	}

	public IndustryDialog(Context context, int style) {
		super(context, R.style.Dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_industry);
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(new ArrayAdapter<String>(context,R.layout.dialog_industry_item,R.id.textView,data));
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dismiss();
				if (clickListener != null) {
					clickListener.selectIndustry(data.get(position));
				}
			}
		});
	}

	public interface CallBack {
		void selectIndustry(String text);
	}
}
