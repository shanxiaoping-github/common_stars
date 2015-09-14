package com.platform.advertising.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.annotation.RESOURE;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.MathUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpgetAdvertisementTopicListClient;
import com.platform.advertising.ui.data.AdverTitleData;
import com.platform.advertising.ui.data.AdvertisingData;
import com.platform.advertising.ui.data.ImageData;
import com.platform.advertising.util.ImageUtil;
import com.squareup.picasso.Picasso;

/**
 * 
 * @author xiaoping.shan 注册界面
 */
@LAYOUT(R.layout.advertising_market_detail_layout)
public class AdvertisingMarketDetailActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value = R.id.tvTitle)
	private TextView tvTitle;

	@ID(value = R.id.content)
	private TextView content;

	@ID(value = R.id.startAnswer, isBindListener = true)
	private View startAnswer;
	
	@ID(value = R.id.viewpager)
	private ViewPager imageViewpage;
	
	@RESOURE("AdvertisingData")
	private AdvertisingData adverData;

	private ArrayList<AdverTitleData> adverTitleList;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		// setContentView(R.layout.advertising_market_detail_layout);
		setBackButton();
		tvTitle.setText(adverData.getTitle());
		content.setText(adverData.getContent());
		initViewPage();
		loadData();

	}
	/**
	 * 初始化viewpage
	 */
	private ArrayList<ImageView> imageList;
	private void initViewPage(){
		
		if(adverData.getImageList()==null||adverData.getImageList().size()<=0){
			imageViewpage.setVisibility(View.GONE);
			return;
		}
		imageList = new ArrayList<ImageView>();
		for (ImageData imageData : adverData.getImageList()) {
			ImageView imageView = new ImageView(this);
			imageView.setTag(imageData);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, MathUtil.diptopx(this,160)));
			imageView.setBackground(getResources().getDrawable(R.drawable.image_stroke));
			imageList.add(imageView);
		}
		
		imageViewpage.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return adverData.getImageList().size();
			}
			@Override  
            public void destroyItem(ViewGroup container, int position,  
                    Object object) {  
                container.removeView(imageList.get(position));  
  
            }  
  
            @Override  
            public int getItemPosition(Object object) {  
  
                return super.getItemPosition(object);  
            }  
            @Override  
            public Object instantiateItem(ViewGroup container, int position) {  
                container.addView(imageList.get(position));  
                String path =((ImageData)imageList.get(position).getTag()).getImagePath();
                Picasso.with(AdvertisingMarketDetailActivity.this).load(ImageUtil.getImageUrl(path)).resize(SXPApplication.getWindowWidth(AdvertisingMarketDetailActivity.this),MathUtil.diptopx(AdvertisingMarketDetailActivity.this,160)).centerCrop().placeholder(R.drawable.show_img).error(R.drawable.show_img).into(imageList.get(position));
                return imageList.get(position);  
            }  
  
        });
		imageViewpage.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				index =  arg0;
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		countTime();
	}
	private int index;
	private void countTime(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				index++;
				if(index>imageList.size()-1){
					index=0;
				}
				runOnUiThread(new  Runnable() {
					public void run() {
						
						imageViewpage.setCurrentItem(index);
					}
				});
				
			}
		}, 3000,3000);
	
		
		
	}
	

	/**
	 * 加载数据
	 */
	private int count = 0;
	private void loadData() {
		count++;
		if(count>3){
			return;
		}
		
		final HttpgetAdvertisementTopicListClient client = new HttpgetAdvertisementTopicListClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				if (client.getList() != null) {
					adverTitleList = client.getList();
				}
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		client.setPramas(new Object[] { adverData.getId() });
		client.submitRequest();

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startAnswer:
			startAnswer();

			break;
		}
	}

	/**
	 * 开始答题
	 */
	private void startAnswer() {
		if (adverTitleList != null) {
			Bundle bundle = new Bundle();
			bundle.putSerializable("AdvertisingData", adverData);
			bundle.putSerializable("adverTitleList", adverTitleList);
			openActivity(AnswerStartActivity.class, bundle);
			finishBase();
		}else{
			showShortToast("没有题目可答");
			loadData();
		}

	}

}
