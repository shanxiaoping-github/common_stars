package com.platform.advertising.ui.home;

import com.platform.advertising.R;
import com.platform.advertising.ui.AdvertisingMarketActivity;
import com.platform.advertising.ui.data.UploadDataActivity;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 首页
 * @author xiaoping.shan
 *
 */
public class HomePageFragment extends BaseFragment implements OnClickListener {
	private ImageView uploadBtn;

	private View linear1, linear2, linear3;

	@Override
	public View layout(LayoutInflater inflater) {
		// TODO Auto-generated method stub

		View contentView = inflater.inflate(R.layout.home_page_fragment_layout,
				null);
		uploadBtn = (ImageView) contentView
				.findViewById(R.id.home_page_fragment_updata);
		uploadBtn.setOnClickListener(this);
		linear1 = contentView.findViewById(R.id.linear1);
		linear1.setOnClickListener(this);
		linear2 = contentView.findViewById(R.id.linear2);
		linear2.setOnClickListener(this);
		linear3 = contentView.findViewById(R.id.linear3);
		linear3.setOnClickListener(this);
		return contentView;

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.home_page_fragment_updata:
			((BaseActivity) getActivity())
					.openActivity(UploadDataActivity.class);
			break;
		case R.id.linear1:
			((BaseActivity) getActivity())
					.openActivity(AdvertisingMarketActivity.class);
			break;
		case R.id.linear2:
			((BaseActivity) getActivity())
					.openActivity(AdvertisingMarketActivity.class);
			break;
		case R.id.linear3:
			((BaseActivity) getActivity())
					.openActivity(FindActivity.class);
			break;
		}

	}

}
