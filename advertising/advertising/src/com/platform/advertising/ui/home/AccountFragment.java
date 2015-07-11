package com.platform.advertising.ui.home;

import org.json.JSONObject;

import com.platform.advertising.R;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;

import sxp.android.framework.ui.BaseFragment;
import sxp.android.framework.util.ShowUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 账户
 * 
 * @author xiaoping.shan
 *
 */
public class AccountFragment extends BaseFragment implements OnClickListener {
	private TextView withdrawal;

	@Override
	public View layout(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		View contentView = inflater.inflate(R.layout.account_fragment_layout,
				null);
		withdrawal = (TextView) contentView
				.findViewById(R.id.account_fragment_withdrawal);
		withdrawal.setOnClickListener(this);
		
		return contentView;
	}

	@Override
	public void onResume() {
		super.onResume();
		send(new BaseThreadCallBack() {
			
			public String sendData() throws Exception {
				params.put("mobile", SharedPreferencesUtil.getString("mobile"));
				return HttpUtil.post("getAccountBalance", params);
			}
			
			public void handleSuccess(String result) throws Exception {
				try {
					JSONObject jsonObject = new JSONObject(result);
					if(jsonObject.getBoolean("code")){
						showLongToast(jsonObject.getString("message"));
					}else{
						showLongToast(jsonObject.getString("message"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					showLongToast("解析数据失败!");
				}
			}
			
			public void handleError(String errorMessage) {
				
			}
			
			public void handleEmpty() {
				
			}
		});
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.account_fragment_withdrawal:
			withdrawal();
			break;

		}
	}
	/**
	 * 提现
	 */
	private void withdrawal(){
		com.platform.advertising.util.ShowUtil.showDialog(getContext(), "提现成功", 2000);
	}

}
