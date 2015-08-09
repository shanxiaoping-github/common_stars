package com.platform.advertising.ui.home;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.platform.advertising.R;
import com.platform.advertising.ui.ChangePwdActivity;
import com.platform.advertising.ui.LocationActivity;
import com.platform.advertising.ui.MaterialActivity;
import com.platform.advertising.ui.MessageActivity;
import com.platform.advertising.ui.login.LoginActivity;
import com.platform.advertising.util.SharedPreferencesUtil;

/**
 * 设置
 * 
 * @author xiaoping.shan
 *
 */
public class SetFragment extends BaseFragment implements OnClickListener {

	private View lMaterial, lLocation, lMessage, lPassword,myAccount;
	private Button outApp;

	@Override
	public View layout(LayoutInflater inflater) {
		View contentView = inflater.inflate(R.layout.set_fragment_layout, null);

		lMaterial = contentView.findViewById(R.id.lMaterial);
		lLocation = contentView.findViewById(R.id.lLocation);
		lMessage = contentView.findViewById(R.id.lMessage);
		lPassword = contentView.findViewById(R.id.lPassword);
		myAccount = contentView.findViewById(R.id.my_account);
		outApp = (Button)contentView.findViewById(R.id.set_fragment_outapp);
		lMaterial.setOnClickListener(this);
		lLocation.setOnClickListener(this);
		lMessage.setOnClickListener(this);
		lPassword.setOnClickListener(this);
		myAccount.setOnClickListener(this);
		outApp.setOnClickListener(this);
		return contentView;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
		case R.id.lMaterial:
			Bundle bundle = new Bundle();
			bundle.putInt("state", MaterialActivity.SET);
			((BaseActivity)getActivity()).openActivity(MaterialActivity.class,bundle);
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
		case R.id.my_account://我得账户
			((BaseActivity)getActivity()).openActivity(AccountActivity.class);
			break;
		case R.id.set_fragment_outapp:
			outApp();
			break;
		}
	}
	/**
	 * 安全退出
	 */
	private void outApp(){
		SharedPreferencesUtil.putString("mobile", "");
		SharedPreferencesUtil.putString("password", "");
	    ((BaseActivity)getActivity()).openActivity(LoginActivity.class);
		
	}
}
