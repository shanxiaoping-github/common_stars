package com.platform.advertising.ui;

import java.util.ArrayList;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.ui.data.AdverTitleData;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.ui.data.AnswerResponseData;
import com.platform.advertising.view.city.AdvertTitleView;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.answer_grade_layout)
public class AnswerGradeActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value = R.id.tvTitle)
	private TextView tvTitle;

	@ID(value = R.id.tvContract, isBindListener = true)
	private TextView tvContract;

	@ID(value = R.id.reward)
	private TextView rewardDesc;

	@ID(value = R.id.tvError)
	private TextView tvError;

	@ID(value = R.id.tvYes)
	private TextView tvYes;

	// @ID(value = R.id.listView)
	// private ListView listView;
	// private MapAdapter adapter;

	@ID(value = R.id.errorLayout, isBindListener = true)
	private View errorLayout;
	@ID(value = R.id.yesLayout, isBindListener = true)
	private View yesLayout;

	@ID(value = R.id.layoutErrorBottom)
	private View layoutErrorBottom;
	@ID(value = R.id.layoutYesBottom)
	private View layoutYesBottom;

	@ID(value = R.id.viewPage)
	private ViewPager viewpager;

	@ID(value = R.id.title_name)
	private TextView titleName;

	// private List<Map<String, String>> data = new ArrayList<Map<String,
	// String>>();

	// @RESOURE("adverTitleList")
	private static ArrayList<AdverTitleData> adverTitleList;
	private ArrayList<AdverTitleData> rightList = new ArrayList<AdverTitleData>();
	private ArrayList<AdverTitleData> errorList = new ArrayList<AdverTitleData>();

	@RESOURE("AnswerResponseDataList")
	private ArrayList<AnswerResponseData> annswerResponseList;
	
	@RESOURE("AdvertisingData")
	private AdvertisingData adverData;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.answer_grade_layout);
		setBackButton();
		setRewardDesc();
		initView();
		setError();

		// tvContract.setOnClickListener(this);
		// errorLayout.setOnClickListener(this);
		// yesLayout.setOnClickListener(this);

		// Map<String, String> map1 = new HashMap<String, String>();
		// map1.put("answer", "A:没有或很少时间");
		// map1.put("checked", "0");
		// data.add(map1);
		// Map<String, String> map2 = new HashMap<String, String>();
		// map2.put("answer", "B:小部分时间");
		// map2.put("checked", "-1");
		// data.add(map2);
		// Map<String, String> map3 = new HashMap<String, String>();
		// map3.put("answer", "C:相当多时间");
		// map3.put("checked", "0");
		// data.add(map3);
		// Map<String, String> map4 = new HashMap<String, String>();
		// map4.put("answer", "B:绝大部分或全部时间");
		// map4.put("checked", "1");
		// data.add(map4);
		//
		// adapter = new MapAdapter(getApplicationContext(), data,
		// new ItemHandleCallBack() {
		//
		// public void handle(ViewHolder holder,
		// Map<String, String> item, int position) {
		// holder.tvs.get(0).setText(item.get("answer"));
		// if ("1".equals(item.get("checked"))) {
		// holder.ivs.get(0).setImageResource(
		// R.drawable.topic_choice_1);
		// holder.views.get(0).setBackgroundColor(
		// getResources().getColor(
		// R.color.answer_checked));
		// holder.tvs.get(0).setTextColor(Color.BLACK);
		// } else if ("0".equals(item.get("checked"))) {
		// holder.ivs.get(0).setImageResource(
		// R.drawable.topic_choice);
		// holder.views.get(0).setBackgroundColor(
		// getResources()
		// .getColor(R.color.transparent));
		// holder.tvs.get(0).setTextColor(Color.BLACK);
		// } else {
		// holder.ivs.get(0).setImageResource(
		// R.drawable.topic_choice_2);
		// holder.views.get(0).setBackgroundColor(
		// getResources()
		// .getColor(R.color.transparent));
		// holder.tvs.get(0).setTextColor(Color.RED);
		// }
		// }
		// }, R.layout.answer_start_layout_item);
		// listView.setAdapter(adapter);
		// listView.setOnItemClickListener(this);
	}

	/*
	 * 设置奖励描述
	 */
	private void setRewardDesc() {
		double amountotal = 0;
		int totalNumber = 0;
		for (int i = 0; i < annswerResponseList.size(); i++) {
			AnswerResponseData answerResponseData = annswerResponseList.get(i);
			AdverTitleData adverTitleData = adverTitleList.get(i);
			adverTitleData.setIsKey(answerResponseData.getKey());
			adverTitleData.setCorrectKey(answerResponseData
					.getCorrectAnswerKey());
			if (answerResponseData.getCorrectAnswerKey().equalsIgnoreCase(
					answerResponseData.getKey())) {
				amountotal += Double.valueOf(answerResponseData.getAmount());
				totalNumber++;
				if (rightList == null) {
					rightList = new ArrayList<AdverTitleData>();
				}
				rightList.add(adverTitleData);

			} else {
				if (errorList == null) {
					errorList = new ArrayList<AdverTitleData>();
				}
				errorList.add(adverTitleData);
			}
		}
		if (totalNumber > 0) {
			rewardDesc.setText("恭喜你,答对了" + totalNumber + "道题,获得" + amountotal
					+ "元");
		} else {
			rewardDesc.setText("不好意思，你没有答对一道题");
		}

	}

	private ArrayList<View> errorViewlist;
	private PagerAdapter errorPagerAdapter;

	private ArrayList<View> rightViewlist;
	private PagerAdapter rightPagerAdapter;

	private void initView() {
		for (int i = 0; i < errorList.size(); i++) {
			AdverTitleData adverTitleData = errorList.get(i);
			AdvertTitleView advertitleView = new AdvertTitleView(this,
					adverTitleData);
			if (errorViewlist == null) {
				errorViewlist = new ArrayList<View>();
			}
			errorViewlist.add(advertitleView);
		}
		errorPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return errorViewlist.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
				container.removeView(errorViewlist.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(errorViewlist.get(position));
				return errorViewlist.get(position);
			}
		};

		for (int i = 0; i < rightList.size(); i++) {
			AdverTitleData adverTitleData = rightList.get(i);
			AdvertTitleView advertitleView = new AdvertTitleView(this,
					adverTitleData);
			if (rightViewlist == null) {
				rightViewlist = new ArrayList<View>();
			}
			rightViewlist.add(advertitleView);
		}
		rightPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return rightViewlist.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				// super.destroyItem(container, position, object);
				container.removeView(rightViewlist.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(rightViewlist.get(position));
				return rightViewlist.get(position);
			}
		};

		viewpager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				AdverTitleData titleData = null;
				switch (state) {
				case 0:
					titleData = errorList.get(arg0);
					break;

				case 1:
					titleData = rightList.get(arg0);
					break;
				}
				titleName.setText(titleData.getTopicName());
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvContract:
//			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
//					+ adverData.getCompany().getPhone()));
//			startActivity(intent);

			Bundle bundle = new Bundle();
			bundle.putString("receiveId","13667355464");
			openActivity(ContractActivity.class,bundle);
			break;
		case R.id.errorLayout:
			setError();
			break;
		case R.id.yesLayout:
			setRight();
			break;
		}

	}

	private int state;

	private void setError() {
		state = 0;
		tvError.setTextColor(Color.RED);
		layoutErrorBottom.setVisibility(View.VISIBLE);
		tvYes.setTextColor(Color.BLACK);
		layoutYesBottom.setVisibility(View.GONE);
		if (errorList.size() > 0) {
			viewpager.setVisibility(View.VISIBLE);
			titleName.setVisibility(View.VISIBLE);
			titleName.setText(errorList.get(0).getTopicName());
			viewpager.setAdapter(errorPagerAdapter);
			viewpager.setCurrentItem(0);
			
		}else{
			viewpager.setVisibility(View.INVISIBLE);
			titleName.setVisibility(View.INVISIBLE);
		}

	}

	private void setRight() {
		state = 1;
		tvError.setTextColor(Color.BLACK);
		layoutErrorBottom.setVisibility(View.GONE);
		tvYes.setTextColor(Color.RED);
		layoutYesBottom.setVisibility(View.VISIBLE);
		if (rightList.size() > 0) {
			viewpager.setVisibility(View.VISIBLE);
			titleName.setVisibility(View.VISIBLE);
			titleName.setText(rightList.get(0).getTopicName());
			viewpager.setAdapter(rightPagerAdapter);
			viewpager.setCurrentItem(0);
		}else{
			viewpager.setVisibility(View.INVISIBLE);
			titleName.setVisibility(View.INVISIBLE);
		}
	}

	public static ArrayList<AdverTitleData> getAdverTitleList() {
		return AnswerGradeActivity.adverTitleList;
	}

	public static void setAdverTitleList(
			ArrayList<AdverTitleData> adverTitleList) {
		AnswerGradeActivity.adverTitleList = adverTitleList;
	}

}
