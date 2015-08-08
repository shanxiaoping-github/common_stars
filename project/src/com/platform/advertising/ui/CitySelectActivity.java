package com.platform.advertising.ui;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.LAYOUT;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.LogUtil;
import com.platform.advertising.view.city.adapter.AreaAdapter;
import com.platform.advertising.view.city.adapter.CityAdapter;
import com.platform.advertising.view.city.adapter.ProvinceAdapter;
import com.platform.advertising.view.city.data.Area;
import com.platform.advertising.view.city.data.City;
import com.platform.advertising.view.city.data.Province;

/**
 * 城市选择
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.city_select_layout)
public class CitySelectActivity extends MyBaseActivity implements OnClickListener {

	private ListView provinceListView;

	private ListView cityListView;

	private ListView areaListView;

	private ProvinceAdapter provinceAdapter;
	private CityAdapter cityAdapter;
	private AreaAdapter areaAdapter;

	private ImageButton back;
	private TextView sure;
	
	private String selectProvince;
	private String selectCity;
	private String selectArea;
	private String selectProvinceId;
	private String selectCityId;
	private String selectAreaId;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		//setContentView(R.layout.city_select_layout);
		provinceListView = (ListView) findViewById(R.id.city_select_province);
		cityListView = (ListView) findViewById(R.id.city_select_city);
		areaListView = (ListView) findViewById(R.id.city_select_area);
		back = (ImageButton) findViewById(R.id.city_select_back);
		sure = (TextView) findViewById(R.id.city_select_sure);

		back.setOnClickListener(this);
		sure.setOnClickListener(this);
		provinceAdapter = new ProvinceAdapter();
		provinceAdapter.setContext(this);
		provinceAdapter.setListener(new AdapterItemListener() {

			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				Province province = (Province) objects[0];
				selectProvince = province.getTitle();
				selectProvinceId = province.getCode();
				getCity(province);
				return false;
			}
		});

		cityAdapter = new CityAdapter();
		cityAdapter.setContext(this);
		cityAdapter.setListener(new AdapterItemListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				City city = (City) objects[0];
				selectCity = city.getTitle();
				selectCityId = city.getCode();
				getArea(city);
				return false;
			}
		});

		areaAdapter = new AreaAdapter();
		areaAdapter.setContext(this);
		areaAdapter.setListener(new AdapterItemListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean onAdapterItemListener(Object... objects) {
				// TODO Auto-generated method stub
				cuArear = (Area) objects[0];
				selectArea = cuArear.getTitle();
				selectAreaId = cuArear.getCode();
				return false;
			}
		});

		provinceListView.setAdapter(provinceAdapter);
		cityListView.setAdapter(cityAdapter);
		areaListView.setAdapter(areaAdapter);


		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("parentId", 0);
				return HttpUtil.post("getRegionList", params);
			}

			public void handleSuccess(String result) throws Exception {
				JSONObject jsonObject = new JSONObject(result);
				LogUtil.error(result);
				if (jsonObject.getBoolean("code")) {
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					ArrayList<Province> provinceList = new ArrayList<Province>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject object = jsonArray.getJSONObject(i);
						Province province = new Province();
						province.setCode(object.getString("id"));
						province.setTitle(object.getString("name"));
						provinceList.add(province);
					}
					provinceAdapter.setList(provinceList);
					provinceAdapter.notifyDataSetChanged();
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

	private void getCity(final Province province) {
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("parentId", province.getCode());
				return HttpUtil.post("getRegionList", params);
			}

			public void handleSuccess(String result) throws Exception {
				JSONObject jsonObject = new JSONObject(result);
				LogUtil.error(result);
				if (jsonObject.getBoolean("code")) {
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					ArrayList<City> cityList = new ArrayList<City>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject object = jsonArray.getJSONObject(i);
						City city = new City();
						city.setCode(object.getString("id"));
						city.setTitle(object.getString("name"));
						cityList.add(city);
					}
					province.setCityList(cityList);
					cityAdapter.setList(province.getCityList());
					cityAdapter.cleanSelect();
					cityAdapter.notifyDataSetChanged();
					areaAdapter.setList(new ArrayList<Area>());
					areaAdapter.cleanSelect();
					areaAdapter.notifyDataSetChanged();
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
	
	private void getArea(final City city) {
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("parentId", city.getCode());
				return HttpUtil.post("getRegionList", params);
			}

			public void handleSuccess(String result) throws Exception {
				JSONObject jsonObject = new JSONObject(result);
				LogUtil.error(result);
				if (jsonObject.getBoolean("code")) {
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					ArrayList<Area> areaList = new ArrayList<Area>();
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject object = jsonArray.getJSONObject(i);
						Area area = new Area();
						area.setCode(object.getString("id"));
						area.setTitle(object.getString("name"));
						areaList.add(area);
					}
					city.setAreaList(areaList);
					areaAdapter.setList(city.getAreaList());
					areaAdapter.cleanSelect();
					areaAdapter.notifyDataSetChanged();
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
		case R.id.city_select_back:
			finishBase();
			break;

		case R.id.city_select_sure:
			sure();
			break;
		}
	}

	private Area cuArear;

	private void sure() {

		Intent intent = new Intent();
		//intent.putExtra("selectArear", cuArear);
		intent.putExtra("selectProvince", selectProvince);
		intent.putExtra("selectCity", selectCity);
		intent.putExtra("selectArea", selectArea);
		intent.putExtra("selectProvinceId", selectProvinceId);
		intent.putExtra("selectCityId", selectCityId);
		intent.putExtra("selectAreaId", selectAreaId);
		setResult(0,intent);
		finishBase();

	}

}
