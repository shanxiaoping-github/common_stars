package com.platform.advertising.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sxp.android.framework.adapter.MapAdapter;
import sxp.android.framework.adapter.MapAdapter.ItemHandleCallBack;
import sxp.android.framework.adapter.MapAdapter.ViewHolder;
import sxp.android.framework.ui.BaseActivity;
import android.R.color;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.platform.advertising.R;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
public class AnswerGradeActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener {
	private TextView tvTitle, tvContract,tvError,tvYes;
	private ListView listView;
	private MapAdapter adapter;
	private View errorLayout, yesLayout,layoutErrorBottom,layoutYesBottom;
	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.answer_grade_layout);
		setBackButton();

		tvContract.setOnClickListener(this);
		errorLayout.setOnClickListener(this);
		yesLayout.setOnClickListener(this);

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("answer", "A:没有或很少时间");
		map1.put("checked", "0");
		data.add(map1);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("answer", "B:小部分时间");
		map2.put("checked", "-1");
		data.add(map2);
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("answer", "C:相当多时间");
		map3.put("checked", "0");
		data.add(map3);
		Map<String, String> map4 = new HashMap<String, String>();
		map4.put("answer", "B:绝大部分或全部时间");
		map4.put("checked", "1");
		data.add(map4);

		adapter = new MapAdapter(getApplicationContext(), data,
				new ItemHandleCallBack() {

					public void handle(ViewHolder holder,
							Map<String, String> item, int position) {
						holder.tvs.get(0).setText(item.get("answer"));
						if ("1".equals(item.get("checked"))) {
							holder.ivs.get(0).setImageResource(
									R.drawable.topic_choice_1);
							holder.views.get(0).setBackgroundColor(
									getResources().getColor(
											R.color.answer_checked));
							holder.tvs.get(0).setTextColor(Color.BLACK);
						} else if ("0".equals(item.get("checked"))) {
							holder.ivs.get(0).setImageResource(
									R.drawable.topic_choice);
							holder.views.get(0).setBackgroundColor(
									getResources()
											.getColor(R.color.transparent));
							holder.tvs.get(0).setTextColor(Color.BLACK);
						} else {
							holder.ivs.get(0).setImageResource(
									R.drawable.topic_choice_2);
							holder.views.get(0).setBackgroundColor(
									getResources()
											.getColor(R.color.transparent));
							holder.tvs.get(0).setTextColor(Color.RED);
						}
					}
				}, R.layout.answer_start_layout_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvContract:
			openActivity(ContractActivity.class);
			break;
		case R.id.errorLayout: {
			tvError.setTextColor(Color.RED);
			layoutErrorBottom.setVisibility(View.VISIBLE);
			tvYes.setTextColor(Color.BLACK);
			layoutYesBottom.setVisibility(View.GONE);
		}
			break;
		case R.id.yesLayout: {
			tvError.setTextColor(Color.BLACK);
			layoutErrorBottom.setVisibility(View.GONE);
			tvYes.setTextColor(Color.RED);
			layoutYesBottom.setVisibility(View.VISIBLE);
		}
			break;
		}

	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	}
}
