package com.platform.advertising.ui.regist;

import android.view.View;

import com.platform.advertising.R;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.ui.BaseActivity;

/**
 * 注册协议
 * 
 * @author shanxiaoping
 *
 */
@LAYOUT(R.layout.regist_greenment_layout)
public class RegistGreenMentActivity extends BaseActivity {
	@ID(value = R.id.regist_greenment_back, isBindListener = true)
	private View back;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.regist_greenment_back:
			finishBase();
			break;

		}
	}
}
