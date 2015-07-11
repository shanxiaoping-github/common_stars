package com.platform.advertising.ui.home;

import sxp.android.framework.ui.BaseFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.platform.advertising.R;
import com.platform.advertising.ui.ChangePwdActivity;
import com.platform.advertising.ui.LocationActivity;
import com.platform.advertising.ui.MaterialActivity;
import com.platform.advertising.ui.MessageActivity;

/**
 * 设置
 * 
 * @author xiaoping.shan
 *
 */
public class SetFragment extends BaseFragment implements OnClickListener {

	private View lMaterial, lLocation, lMessage, lPassword;

	@Override
	public View layout(LayoutInflater inflater) {
		View contentView = inflater.inflate(R.layout.set_fragment_layout, null);

		lMaterial = contentView.findViewById(R.id.lMaterial);
		lLocation = contentView.findViewById(R.id.lLocation);
		lMessage = contentView.findViewById(R.id.lMessage);
		lPassword = contentView.findViewById(R.id.lPassword);
		lMaterial.setOnClickListener(this);
		lLocation.setOnClickListener(this);
		lMessage.setOnClickListener(this);
		lPassword.setOnClickListener(this);

		return contentView;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.lMaterial:
			intent = new Intent();
			intent.setClass(getContext(), MaterialActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.lLocation:
			intent = new Intent();
			intent.setClass(getContext(), LocationActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.lMessage:
			intent = new Intent();
			intent.setClass(getContext(), MessageActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.lPassword:
			intent = new Intent();
			intent.setClass(getContext(), ChangePwdActivity.class);
			getActivity().startActivity(intent);
			break;
		}
	}
}
