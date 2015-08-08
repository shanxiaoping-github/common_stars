package com.platform.advertising.ui.home;

import com.platform.advertising.R;
import com.platform.advertising.ui.CitySelectActivity;
import com.platform.advertising.ui.find.FindResultActivity;
import com.platform.advertising.view.city.data.Area;

import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.ui.BaseFragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 查找
 * 
 * @author xiaoping.shan
 *
 */
public class FindFragment extends BaseFragment implements OnClickListener {
	
	private String provinceId;
	private EditText province;
	
	private String cityId;
	private EditText city;
	
	private String areasId;
	private EditText areas;
	
	
	private EditText edtKeyword;

	@Override
	public View layout(LayoutInflater inflater) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.find_fragment_layout, null);
		view.findViewById(R.id.find_fragment_find_btn).setOnClickListener(this);
		view.findViewById(R.id.find_fragment_area).setOnClickListener(this);
		edtKeyword = (EditText) view.findViewById(R.id.edtKeyword);
		province = (EditText) view.findViewById(R.id.area_province);
		city = (EditText) view.findViewById(R.id.area_city);
		areas = (EditText) view.findViewById(R.id.area_area);
		province.setOnClickListener(this);
		city.setOnClickListener(this);
		areas.setOnClickListener(this);
		
		return view;

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_fragment_find_btn:
			Intent intent = new Intent(getContext(), FindResultActivity.class);
			intent.putExtra("provinceId", provinceId);
			intent.putExtra("cityId", cityId);
			intent.putExtra("districtId", areasId);
			intent.putExtra("keyword", edtKeyword.getText().toString().trim());
			startActivity(intent);

			break;
		case R.id.find_fragment_area:
			((BaseActivity) getActivity())
					.openActivityResult(CitySelectActivity.class);
			break;
		case R.id.area_province:
			startActivityForResult(new Intent(getActivity(),CitySelectActivity.class), 100);
			break;
		case R.id.area_city:
			startActivityForResult(new Intent(getActivity(),CitySelectActivity.class), 100);
			break;
		case R.id.area_area:
			startActivityForResult(new Intent(getActivity(),CitySelectActivity.class), 100);
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			province.setText(data.getStringExtra("selectProvince"));
			provinceId = data.getStringExtra("selectProvinceId");
			
			city.setText(data.getStringExtra("selectCity"));
			cityId = data.getStringExtra("selectCityId");
			
			areas.setText(data.getStringExtra("selectArea"));
			areasId = data.getStringExtra("selectAreaId");
			
		}
	}

}
