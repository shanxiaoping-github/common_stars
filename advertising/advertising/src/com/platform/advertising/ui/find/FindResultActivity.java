package com.platform.advertising.ui.find;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.platform.advertising.R;
import com.platform.advertising.ui.find.data.Company;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.LogUtil;
import com.platform.advertising.view.city.data.Province;

import sxp.android.framework.adapter.MyBaseAdapter.AdapterItemListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseActivity.BaseThreadCallBack;

/**
 * 查找结果
 * 
 * @author xiaoping.shan
 *
 */
public class FindResultActivity extends BaseActivity implements OnClickListener {

	private ImageButton back;
	private ListView resultListView;
	private CompanyAdapter companyAdapter;
	
	private int pageSize = 10;
	private int pageNum = 1;
	private String area;
	private String keyword;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.find_result_layout);
		back = (ImageButton) findViewById(R.id.find_result_back);
		resultListView = (ListView) findViewById(R.id.find_result_listview);
		companyAdapter = new CompanyAdapter();
		companyAdapter.setContext(this);
		companyAdapter.setListener(new AdapterItemListener() {

		
			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				Company company = (Company) objects[0];
				Bundle bundle = new Bundle();
				bundle.putSerializable("company", company);
				openActivity(CompanyDetailActivity.class, bundle);
				return false;
			}
		});
		
		area = getIntent().getStringExtra("area");
		keyword = getIntent().getStringExtra("keyword");
//		ArrayList<Company> companyList = new ArrayList<Company>();
//		for (int i = 0; i < 10; i++) {
//			Company c1 = new Company();
//			c1.setAdress("长沙荷花池");
//			c1.setIntroduce("这是个互联网性质的公司");
//			c1.setTitle("烈焰鸟网络科技有限公司");
//			c1.setTelphone("13667355464");
//			companyList.add(c1);
//		}
//
//		companyAdapter.setList(companyList);
//
//		resultListView.setAdapter(companyAdapter);
		back.setOnClickListener(this);
		
		loadData();
	}

	private void loadData(){
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("area", area);
				params.put("keyword", keyword);
				params.put("pageSize", pageSize);
				params.put("pageNum", pageNum);
				return HttpUtil.post("findProduct", params);
			}

			public void handleSuccess(String result) throws Exception {
				JSONObject jsonObject = new JSONObject(result);
				LogUtil.error(result);
				if (jsonObject.getBoolean("code")) {
					JSONArray jsonArray = jsonObject.getJSONArray("data");
				} else {
					showLongToast(jsonObject.getString("message"));
				}
			}

			public void handleError(String errorMessage) {
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
			}
		});
	}
	
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_result_back:
			finishBase();
			break;

		}
	}

}
