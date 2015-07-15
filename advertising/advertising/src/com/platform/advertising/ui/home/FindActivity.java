package com.platform.advertising.ui.home;

import com.platform.advertising.R;

import sxp.android.framework.ui.BaseFragmentActivity;

/**
 * 查找activity
 * 
 * @author shanxiaoping
 *
 */
public class FindActivity extends BaseFragmentActivity {
	private FindFragment findFragment;
	@Override
	public void layout() {
		// TODO Auto-generated method stub
		super.layout();
		setContentView(R.layout.find_layout);
		findFragment = new FindFragment();
		setCurrentFragment(findFragment, R.id.find_layout_container);
		
	}

}
