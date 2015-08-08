package com.platform.advertising.ui.home;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.UploadDataActivity;

/**
 * 首页
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.home_page_fragment_layout)
public class HomePageFragment extends BaseFragment implements OnClickListener {
	@ID(value=R.id.home_page_fragment_updata,isBindListener = true)
	private ImageView uploadBtn;

	@ID(value = R.id.linear1,isBindListener = true)
	private View linear1;
	
	@ID(value = R.id.linear2,isBindListener = true)
	private View linear2;
	
	@ID(value = R.id.linear3,isBindListener = true)
	private View linear3;

	@Override
	public View layout(LayoutInflater inflater) {
		// TODO Auto-generated method stub

//		View contentView = inflater.inflate(R.layout.home_page_fragment_layout,
//				null);
//		uploadBtn = (ImageView) contentView
//				.findViewById(R.id.home_page_fragment_updata);
//		uploadBtn.setOnClickListener(this);
//		linear1 = contentView.findViewById(R.id.linear1);
//		linear1.setOnClickListener(this);
//		linear2 = contentView.findViewById(R.id.linear2);
//		linear2.setOnClickListener(this);
//		linear3 = contentView.findViewById(R.id.linear3);
//		linear3.setOnClickListener(this);
//		return contentView;
		return null;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.home_page_fragment_updata:
			((BaseActivity) getActivity())
					.openActivity(UploadDataActivity.class);
			break;
		case R.id.linear1:
//			((BaseActivity) getActivity())
//					.openActivity(AdvertisingMarketActivity.class);
			break;
		case R.id.linear2:
//			((BaseActivity) getActivity())
//					.openActivity(AdvertisingMarketActivity.class);
			break;
		case R.id.linear3:
			((BaseActivity) getActivity())
					.openActivity(FindActivity.class);
			break;
		}

	}

}
