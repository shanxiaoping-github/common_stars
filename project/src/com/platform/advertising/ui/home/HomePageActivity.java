package com.platform.advertising.ui.home;

import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.ui.BaseFragmentActivity;
import sxp.android.framework.util.ShowUtil;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.platform.advertising.R;
import com.platform.advertising.baidu_sdk.BaiduLBSManager;
import com.platform.advertising.ui.AdvertisingMarketFragment;

/**
 * 首页
 * 
 * @author xiaoping.shan
 *
 */
public class HomePageActivity extends BaseFragmentActivity implements
		OnClickListener {
	private View p1;
	private View p2;
	private View p3;
	

	@Override
	public void layout() {
		// TODO Auto-generated method stub
		super.layout();
		//初始定位
		BaiduLBSManager.getInstance().initLBS();
		
		setContentView(R.layout.home_page_layout);
		/* 初始view */
		p1 = findViewById(R.id.home_page_p1_layout);
		p2 = findViewById(R.id.home_page_p2_layout);
		p3 = findViewById(R.id.home_page_p3_layout);
		
		p1.setOnClickListener(this);
		p2.setOnClickListener(this);
		p3.setOnClickListener(this);
		
		
		/*fragment*/
		advertisingMarketFragment = new AdvertisingMarketFragment();
		findFragment = new FindFragment();
		//accountFragment = new AccountFragment();
		setFragment = new SetFragment();

		selectPage(0);

	}

	/**
	 * 选中
	 * 
	 * @param pageIndex
	 */
	private AdvertisingMarketFragment advertisingMarketFragment;
	private FindFragment findFragment;
	//private AccountFragment accountFragment;
	private SetFragment setFragment;
	private void selectPage(int pageIndex) {
		clearPage();
		switch (pageIndex) {
		case 0:
			p1.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(advertisingMarketFragment,R.id.home_page_content);
			break;

		case 1:
			p2.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(findFragment,R.id.home_page_content);
			break;
		case 2:
			p3.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(setFragment,R.id.home_page_content);
			break;
//		case 3:
//			p4.setBackgroundColor(getResources().getColor(R.color.blue));
//			setCurrentFragment(setFragment,R.id.home_page_content);
//			break;
		}

	}

	private void clearPage() {
		p1.setBackgroundColor(getResources().getColor(R.color.shallowGray));
		p2.setBackgroundColor(getResources().getColor(R.color.shallowGray));
		p3.setBackgroundColor(getResources().getColor(R.color.shallowGray));
		//p4.setBackgroundColor(getResources().getColor(R.color.black));
	}

	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.home_page_p1_layout:
			selectPage(0);
			break;
		case R.id.home_page_p2_layout:
			selectPage(1);
			break;
		case R.id.home_page_p3_layout:
			selectPage(2);
			break;
//		case R.id.home_page_p4_layout:
//			selectPage(3);
//			break;
		}
	}
	
	private long lastTime;
	private long howTime = 1000;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - lastTime > 1000) {
				lastTime = System.currentTimeMillis();
				ShowUtil.showShortToast(this, "连按两次退出软件");
			}else {
				SXPApplication.outApp(true);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
