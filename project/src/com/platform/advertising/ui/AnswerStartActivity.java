package com.platform.advertising.ui;

import java.util.ArrayList;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.StringUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpAdvertisementTopicSubmitClient;
import com.platform.advertising.ui.data.AdverTitleData;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;
import com.platform.advertising.view.city.AdvertTitleView;
import com.platform.advertising.view.city.AdvertTitleView.SelectListener;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.answer_start_layout)
public class AnswerStartActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value = R.id.tvTitle)
	private TextView tvTitle;

	@ID(value = R.id.tvNextAnswer, isBindListener = true)
	private TextView tvNextAnswer;
	
	@ID(value = R.id.title_number)
    private TextView titleNumber;

	@ID(value = R.id.viewpager)
	private ViewPager viewPager;
	
	@ID(value = R.id.submit,isBindListener = true)
	private Button submit;
	
	@ID(value = R.id.back,isBindListener = true)
	private View back;

	// @ID(value = R.id.listView)
	// private ListView listView;
	//
	// private MapAdapter adapter;
	// private List<Map<String,String>> data = new
	// ArrayList<Map<String,String>>();

	@RESOURE("AdvertisingData")
	private AdvertisingData adverData;

	@RESOURE("adverTitleList")
	private ArrayList<AdverTitleData> list;

	
	@Override
	protected void layout() {

		initView();
		initViewPage();

		// TODO Auto-generated method stub
		// setContentView(R.layout.answer_start_layout);
	   //setBackButton();
		//
		// tvTitle.setText(getIntent().getExtras().getString("name"));
		// //tvNextAnswer.setOnClickListener(this);
		//
		// Map<String,String> map1 = new HashMap<String, String>();
		// map1.put("answer", "A:没有或很少时间");
		// map1.put("checked", "0");
		// data.add(map1);
		// Map<String,String> map2 = new HashMap<String, String>();
		// map2.put("answer", "B:小部分时间");
		// map2.put("checked", "0");
		// data.add(map2);
		// Map<String,String> map3 = new HashMap<String, String>();
		// map3.put("answer", "C:相当多时间");
		// map3.put("checked", "0");
		// data.add(map3);
		// Map<String,String> map4 = new HashMap<String, String>();
		// map4.put("answer", "B:绝大部分或全部时间");
		// map4.put("checked", "0");
		// data.add(map4);
		//
		// adapter = new MapAdapter(getApplicationContext(), data, new
		// ItemHandleCallBack() {
		//
		// public void handle(ViewHolder holder, Map<String, String> item, int
		// position) {
		// holder.tvs.get(0).setText(item.get("answer"));
		// if("1".equals(item.get("checked"))){
		// holder.ivs.get(0).setImageResource(R.drawable.topic_choice_1);
		// holder.views.get(0).setBackgroundColor(getResources().getColor(R.color.answer_checked));
		// }else {
		// holder.ivs.get(0).setImageResource(R.drawable.topic_choice);
		// holder.views.get(0).setBackgroundColor(getResources().getColor(R.color.transparent));
		// }
		// }
		// }, R.layout.answer_start_layout_item);
		// listView.setAdapter(adapter);
		// listView.setOnItemClickListener(this);
		//
		// HttpgetAdvertisementTopicListClient client = new
		// HttpgetAdvertisementTopicListClient();
		// client.setPramas(new Object[]{
		// adverData.getId()
		// });
		// client.submitRequest();
	}

	private ArrayList<View> viewList = new ArrayList<View>();

	private void initView() {
		for (int i = 0; i < list.size(); i++) {
			AdverTitleData adverTitleData = list.get(i);
			AdvertTitleView titleView = new AdvertTitleView(this,adverTitleData);
			titleView.setIndex(i);
			titleView.setActionListern(new SelectListener() {
				
				@Override
				public void action(Object... objects) {
					// TODO Auto-generated method stub
					int index = (Integer)objects[0];
					if(index!=list.size()-1){
						nextTitle();
					}
				}
			});
			viewList.add(titleView);
		}
		tvTitle.setText(list.get(0).getTopicName());
		titleNumber.setText("Q1");
	}
	private void initViewPage(){
		
		viewPager.setAdapter(new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return viewList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				//super.destroyItem(container, position, object);
				container.removeView(viewList.get(position));
			}
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				//return super.instantiateItem(container, position);
				container.addView(viewList.get(position));
				return viewList.get(position);
			}
		});
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				tvTitle.setText(list.get(arg0).getTopicName());
				int number = arg0+1;
				titleNumber.setText("Q"+number);
				if(arg0==list.size()-1){
					tvNextAnswer.setVisibility(View.GONE);
				}else{
					tvNextAnswer.setVisibility(View.VISIBLE);
				}
				currentIndex = arg0;

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
		case R.id.tvNextAnswer:
			nextTitle();
			break;
		case R.id.submit:
			submitAnswer();
			break;
		case R.id.back:
			finishBase();
			break;
			//Bundle bundle = new Bundle();
			//openActivity(AnswerGradeActivity.class, bundle);
		}
	}
	/**
	 * 下一题
	 */
	private int currentIndex = 0;
	private void nextTitle(){
		if(currentIndex+1<list.size()){
			currentIndex++;
		}
		viewPager.setCurrentItem(currentIndex);
		
	}
	/**
	 * 提交答案
	 */
	private void submitAnswer(){
		boolean isAll = true;
		for(int i=0;i<list.size();i++){
			AdverTitleData adverTitleData = list.get(i);
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
				isAll = false;
				break;
			}
		}
		if(isAll){
			final HttpAdvertisementTopicSubmitClient client = new HttpAdvertisementTopicSubmitClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
				
				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("答案提交失败");
					return false;
				}
				
				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					if(client.getList()!=null){
						AnswerGradeActivity.setAdverTitleList(list);
						Bundle bundle  = new Bundle();
						//bundle.putSerializable("adverTitleList", list);
						bundle.putSerializable("AdvertisingData",adverData);
						bundle.putSerializable("AnswerResponseDataList",client.getList());
						openActivity(AnswerGradeActivity.class,bundle);
						finishBase();
					}
					return false;
				}
				
				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("答案提交失败");
					return false;
				}
			});
			client.setPramas(new Object[]{
					adverData.getId(),
					getParam(),
					SharedPreferencesUtil.getString("mobile"),
			});
			ShowUtil.openHttpDialog("答案提交中...");
			client.submitRequest();
			
		}else{
			showShortToast("请作答完所有题目");
			
		}
		
	}
	/**
	 * 获得答题参数内容
	 * @return
	 */
	private String getParam(){
		StringBuffer paramBuff = new StringBuffer();
		for(int i=0;i<list.size();i++){
			AdverTitleData adverTitleData = list.get(i);
			paramBuff.append(i+1);
			paramBuff.append("|");
			paramBuff.append(adverTitleData.getIsKey());
			if(i!=list.size()-1){
				paramBuff.append(",");
			}
		}
		return paramBuff.toString();
	}
}
