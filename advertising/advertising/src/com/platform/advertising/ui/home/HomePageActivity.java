package com.platform.advertising.ui.home;

import sxp.android.framework.ui.BaseFragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.platform.advertising.R;

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
	private View p4;

	@Override
	public void layout() {
		// TODO Auto-generated method stub
		super.layout();
		setContentView(R.layout.home_page_layout);
		/* 初始view */
		p1 = findViewById(R.id.home_page_p1_layout);
		p2 = findViewById(R.id.home_page_p2_layout);
		p3 = findViewById(R.id.home_page_p3_layout);
		p4 = findViewById(R.id.home_page_p4_layout);
		p1.setOnClickListener(this);
		p2.setOnClickListener(this);
		p3.setOnClickListener(this);
		p4.setOnClickListener(this);
		
		/*fragment*/
		homePagefragment = new HomePageFragment();
		findFragment = new FindFragment();
		accountFragment = new AccountFragment();
		setFragment = new SetFragment();

		selectPage(0);

	}

	/**
	 * 选中
	 * 
	 * @param pageIndex
	 */
	private HomePageFragment homePagefragment;
	private FindFragment findFragment;
	private AccountFragment accountFragment;
	private SetFragment setFragment;
	private void selectPage(int pageIndex) {
		clearPage();
		switch (pageIndex) {
		case 0:
			p1.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(homePagefragment,R.id.home_page_content);
			break;

		case 1:
			p2.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(findFragment,R.id.home_page_content);
			break;
		case 2:
			p3.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(accountFragment,R.id.home_page_content);
			break;
		case 3:
			p4.setBackgroundColor(getResources().getColor(R.color.blue));
			setCurrentFragment(setFragment,R.id.home_page_content);
			break;
		}

	}

	private void clearPage() {
		p1.setBackgroundColor(getResources().getColor(R.color.black));
		p2.setBackgroundColor(getResources().getColor(R.color.black));
		p3.setBackgroundColor(getResources().getColor(R.color.black));
		p4.setBackgroundColor(getResources().getColor(R.color.black));
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
		case R.id.home_page_p4_layout:
			selectPage(3);
			break;
		}
	}
}
