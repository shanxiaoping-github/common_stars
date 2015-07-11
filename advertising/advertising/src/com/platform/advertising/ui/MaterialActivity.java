package com.platform.advertising.ui;

import org.json.JSONObject;

import sxp.android.framework.ui.BaseActivity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.platform.advertising.R;
import com.platform.advertising.ui.AgeDialog.CallBack;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.view.city.data.Area;
import com.platform.advertising.view.city.data.City;
import com.platform.advertising.view.city.data.Province;

public class MaterialActivity extends BaseActivity {

	private EditText province;
	private EditText city;
	private EditText areas;

	private Button btnSex;
	private Button isnet;
	private Button ismarry;
	private Button btnAge, btnIndustry;
	private String sex, age;
	private Area area;
	private String industry;
	private String isNet = "1";
	private String isMarry = "1";
	private String selectProvinceId;
	private String selectCityId;
	private String selectAreaId;
	private String ageGroupName;

	@Override
	protected void layout() {
		setContentView(R.layout.activity_material);
		findViewById(R.id.set_fragment_back).setOnClickListener(
				new OnClickListener() {

					public void onClick(View v) {
						finish();
					}
				});
		findViewById(R.id.set_frament_save).setOnClickListener(this);

		findViewById(R.id.set_fragment_area).setOnClickListener(this);

		btnSex = (Button) findViewById(R.id.set_fragment_sex);
		isnet = (Button) findViewById(R.id.set_fragment_isnet);
		ismarry = (Button) findViewById(R.id.set_fragment_ismarry);
		btnAge = (Button) findViewById(R.id.btnAge);
		btnIndustry = (Button) findViewById(R.id.btnIndustry);

		province = (EditText) findViewById(R.id.area_province);
		city = (EditText) findViewById(R.id.area_city);
		areas = (EditText) findViewById(R.id.area_area);

		btnSex.setOnClickListener(this);
		isnet.setOnClickListener(this);
		ismarry.setOnClickListener(this);
		btnAge.setOnClickListener(this);
		btnIndustry.setOnClickListener(this);

		init();

	}

	// 获取职业列表
	public void getOccupationList() {
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				return HttpUtil.post("getOccupationList", params);
			}

			public void handleSuccess(String result) throws Exception {
				try {
					JSONObject jsonObject = new JSONObject(result);
					if (jsonObject.getBoolean("code")) {
						JSONObject dataObject = jsonObject
								.getJSONObject("data");
					} else {
						showLongToast(jsonObject.getString("message"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					showLongToast("解析数据失败!");
				}
			}

			public void handleError(String errorMessage) {
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
			}
		});
	}

	// 获取年龄列表
	public void getAgeGroupList() {
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				return HttpUtil.post("getAgeGroupList", params);
			}

			public void handleSuccess(String result) throws Exception {
				try {
					JSONObject jsonObject = new JSONObject(result);
					if (jsonObject.getBoolean("code")) {
						JSONObject dataObject = jsonObject
								.getJSONObject("data");
					} else {
						showLongToast(jsonObject.getString("message"));
					}
				} catch (Exception e) {
					e.printStackTrace();
					showLongToast("解析数据失败!");
				}
			}

			public void handleError(String errorMessage) {
				showLongToast(errorMessage);
			}

			public void handleEmpty() {
			}
		});
	}

	public void init() {
		sex = SharedPreferencesUtil.getString("sex");
		if (TextUtils.isEmpty(sex)) {
			sex = "0";
		}
		if (TextUtils.equals(sex, "0")) {
			btnSex.setBackgroundResource(R.drawable.personal_switch_womwen_1);
		} else {
			btnSex.setBackgroundResource(R.drawable.personal_switch_womwen);
		}

		age = SharedPreferencesUtil.getString("age");
		setAge(age);

		industry = SharedPreferencesUtil.getString("industry");
		if (TextUtils.isEmpty(industry)) {
			industry = "农业";
		}
		btnIndustry.setText(industry);
		// 地区
		area = new Area();
		area.setCity(new City());
		area.getCity().setProvince(new Province());

		area.getCity().getProvince()
				.setTitle(SharedPreferencesUtil.getString("province"));
		area.getCity().setTitle(SharedPreferencesUtil.getString("city"));
		area.setTitle(SharedPreferencesUtil.getString("area"));

		selectAreaId = SharedPreferencesUtil.getString("districtId");
		selectProvinceId = SharedPreferencesUtil.getString("provinceId");
		selectCityId = SharedPreferencesUtil.getString("cityId");

		province.setText(area.getCity().getProvince().getTitle());
		city.setText(area.getCity().getTitle());
		areas.setText(area.getTitle());

		isNet = SharedPreferencesUtil.getString("isNet");
		if (TextUtils.isEmpty(isNet)) {
			isNet = "0";
		}
		if (TextUtils.equals(isNet, "0")) {
			isnet.setBackgroundResource(R.drawable.personal_switch_noyes);
		} else {
			isnet.setBackgroundResource(R.drawable.personal_switch_no);
		}

		isMarry = SharedPreferencesUtil.getString("isMarry");
		if (TextUtils.isEmpty(isMarry)) {
			isMarry = "0";
		}
		if (TextUtils.equals(isMarry, "0")) {
			ismarry.setBackgroundResource(R.drawable.personal_switch_noyes);
		} else {
			ismarry.setBackgroundResource(R.drawable.personal_switch_no);
		}

		// sex();
		// isnet();
		// ismarry();
	}

	private void save() {
		if (TextUtils.isEmpty(selectAreaId) || TextUtils.isEmpty(selectCityId)
				|| TextUtils.isEmpty(selectProvinceId)) {
			showShortToast("地区不能为空");
		} else {

			send(new BaseThreadCallBack() {

				public String sendData() throws Exception {
					params.put("mobile",
							SharedPreferencesUtil.getString("mobile"));
					params.put("districtId", selectAreaId);
					params.put("provinceId", selectProvinceId);
					params.put("cityId", selectCityId);
					params.put("genderId", sex);
					params.put("ageGroupId", age);
					params.put("ageGroupName", ageGroupName);
					params.put("jobId", 1);
					params.put("jobName", industry);
					params.put("onlineShopping", isNet);
					params.put("isMarried", isMarry);
					return HttpUtil.post("userInfoUpdate", params);
				}

				public void handleSuccess(String result) throws Exception {
					closeProgressDialog();
					try {
						JSONObject jsonObject = new JSONObject(result);
						if (jsonObject.getBoolean("code")) {
							SharedPreferencesUtil.putString("sex", sex);
							SharedPreferencesUtil.putString("age", age);
							SharedPreferencesUtil.putString("industry",
									industry);

							SharedPreferencesUtil.putString("province", area
									.getCity().getProvince().getTitle());
							SharedPreferencesUtil.putString("city", area
									.getCity().getTitle());
							SharedPreferencesUtil.putString("area",
									area.getTitle());

							SharedPreferencesUtil.putString("districtId",
									selectAreaId);
							SharedPreferencesUtil.putString("provinceId",
									selectProvinceId);
							SharedPreferencesUtil.putString("cityId",
									selectCityId);

							SharedPreferencesUtil.putString("isNet", isNet);
							SharedPreferencesUtil.putString("isMarry", isMarry);
							com.platform.advertising.util.ShowUtil.showDialog(
									getApplicationContext(), "保存成功", 2000);
						} else {
							showShortToast(jsonObject.getString("message"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				public void handleError(String errorMessage) {
					closeProgressDialog();
					showLongToast(errorMessage);
				}

				public void handleEmpty() {
					showProgressDialog("正在提交数据...");
				}
			});
		}
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set_frament_save:
			save();
			break;
		case R.id.set_fragment_area:
			openActivityResult(CitySelectActivity.class);
			break;
		case R.id.set_fragment_sex:
			sex();
			break;
		case R.id.set_fragment_isnet:
			isnet();
			break;
		case R.id.set_fragment_ismarry:
			ismarry();
			break;
		case R.id.btnAge:
			AgeDialog ageDialog = new AgeDialog(this, new CallBack() {

				public void selectAge(String value) {
					//age = value;
					setAge(age);
				}
			});
			ageDialog.show();
			break;
		case R.id.btnIndustry:
			IndustryDialog dialog = new IndustryDialog(this,
					new com.platform.advertising.ui.IndustryDialog.CallBack() {

						public void selectIndustry(String text) {
							industry = text;
							btnIndustry.setText(industry);
						}
					});
			dialog.show();
			break;
		}
	}

	public void setAge(String age) {
		if (TextUtils.isEmpty(age)) {
			age = "0";
		}
		if (TextUtils.equals(age, "0")) {
			btnAge.setText("20岁以下");
			ageGroupName = "20岁以下";
		} else if (TextUtils.equals(age, "1")) {
			btnAge.setText("20-35岁");
			ageGroupName = "20-35岁";
		} else if (TextUtils.equals(age, "2")) {
			btnAge.setText("35-50岁");
			ageGroupName = "35-50岁";
		} else if (TextUtils.equals(age, "3")) {
			btnAge.setText("50岁以上");
			ageGroupName = "50岁以上";
		}
		this.age = age;
	}

	private void sex() {
		if (TextUtils.equals(sex, "0")) {
			sex = "1";
			btnSex.setBackgroundResource(R.drawable.personal_switch_womwen);
		} else {
			sex = "0";
			btnSex.setBackgroundResource(R.drawable.personal_switch_womwen_1);
		}
	}

	private void isnet() {
		if (TextUtils.equals(isNet, "0")) {
			isnet.setBackgroundResource(R.drawable.personal_switch_noyes);
			isNet = "1";
		} else {
			isnet.setBackgroundResource(R.drawable.personal_switch_no);
			isNet = "0";
		}
		// isNet = !isNet;
	}

	private void ismarry() {
		if (TextUtils.equals(isMarry, "0")) {
			ismarry.setBackgroundResource(R.drawable.personal_switch_noyes);
			isMarry = "1";
		} else {
			ismarry.setBackgroundResource(R.drawable.personal_switch_no);
			isMarry = "0";
		}
		// isMarry = !isMarry;
	}

	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if (arg2 != null) {
			// area = (Area) arg2.getExtras().get("selectArear");
			// province.setText(area.getCity().getProvince().getTitle());
			// city.setText(area.getCity().getTitle());
			// areas.setText(area.getTitle());
			province.setText(arg2.getStringExtra("selectProvince"));
			city.setText(arg2.getStringExtra("selectCity"));
			areas.setText(arg2.getStringExtra("selectArea"));
			selectProvinceId = arg2.getStringExtra("selectProvinceId");
			selectCityId = arg2.getStringExtra("selectCityId");
			selectAreaId = arg2.getStringExtra("selectAreaId");
		}
	}
}
