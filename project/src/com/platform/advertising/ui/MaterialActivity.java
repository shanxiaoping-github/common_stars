package com.platform.advertising.ui;

import java.util.ArrayList;
import java.util.List;

import sxp.android.framework.adapter.BaseAdapter.AdapterItemListener;
import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.StringUtil;
import sxp.android.framework.view.CustomDialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.platform.advertising.Age;
import com.platform.advertising.Occupation;
import com.platform.advertising.R;
import com.platform.advertising.UserInfo;
import com.platform.advertising.UserInfoDao.Properties;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpPersonInformationClient;
import com.platform.advertising.sqlite.GreenDaoService;
import com.platform.advertising.ui.adapter.AgeAdapter;
import com.platform.advertising.ui.adapter.JobAdapter;
import com.platform.advertising.ui.home.HomePageActivity;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

@LAYOUT(R.layout.activity_material)
public class MaterialActivity extends MyBaseActivity {
	@ID(value = R.id.area_province)
	private EditText province;
	@ID(value = R.id.area_city)
	private EditText city;
	@ID(value = R.id.area_area)
	private EditText areas;
	@ID(value = R.id.set_fragment_sex, isBindListener = true)
	private Button sexBtn;
	@ID(value = R.id.set_fragment_isnet, isBindListener = true)
	private Button isNetBtn;
	@ID(value = R.id.set_fragment_ismarry, isBindListener = true)
	private Button ismarryBtn;
	@ID(value = R.id.btnAge, isBindListener = true)
	private Button ageBtn;
	@ID(value = R.id.btnIndustry, isBindListener = true)
	private Button jobBtn;

	private String sexId;

	private String ageId;
	private String ageName;

	private String jobId;
	private String jobName;

	private String isNetId;
	private String isMarryId;

	private String provinceId;
	private String provinceName;

	private String cityId;
	private String cityName;

	private String areaId;
	private String areaName;

	@ID(value = R.id.skip, isBindListener = true)
	private Button skipBtn;

	public static final int PER = 0;
	public static final int SET = 1;
	private int state;

	@Override
	protected void layout() {
		// setContentView(R.layout.activity_material);

		findViewById(R.id.set_fragment_back).setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						finishBase();
					}
				});
		findViewById(R.id.set_frament_save).setOnClickListener(this);
		findViewById(R.id.set_fragment_area).setOnClickListener(this);

		state = getIntent().getExtras().getInt("state");
		switch (state) {
		case PER:
			skipBtn.setVisibility(View.VISIBLE);
			break;
		case SET:
			skipBtn.setVisibility(View.GONE);
			break;
		}
		initUserInfo();

	}

	private void save() {

		if (StringUtil.isEmpty(ageId)) {
			showShortToast("年龄不能为空");
		} else if (StringUtil.isEmpty(jobId)) {
			showShortToast("职业不能为空");
		} else if (StringUtil.isEmpty(provinceId) && StringUtil.isEmpty(cityId)
				&& StringUtil.isEmpty(areaId)) {
			showShortToast("地区不能为空");
		} else {

			final HttpPersonInformationClient savaInfoClient = new HttpPersonInformationClient();
			savaInfoClient
					.addAsynHcResponseListenrt(new AsynHcResponseListener() {

						public boolean onTimeout() {
							// TODO Auto-generated method stub
							ShowUtil.closeHttpDialog();
							showShortToast("个人信息保存超时");
							return false;
						}

						public boolean onSuccess(
								BaseAsynHttpClient asynHttpClient) {
							// TODO Auto-generated method stub
							ShowUtil.closeHttpDialog();
							if (savaInfoClient.isSuccess()) {

								UserInfo userInfo = new UserInfo();
								userInfo.setUserId(SharedPreferencesUtil
										.getString("mobile"));
								userInfo.setSex(sexId);
								userInfo.setAgeId(ageId);
								userInfo.setAgeName(ageName);
								userInfo.setJobId(jobId);
								userInfo.setJobName(jobName);
								userInfo.setProvinceId(provinceId);
								userInfo.setProvinceName(provinceName);
								userInfo.setCityId(cityId);
								userInfo.setCityName(cityName);
								userInfo.setAreaId(areaId);
								userInfo.setAreaName(areaName);
								userInfo.setIsNetShoping(isNetId);
								userInfo.setIsMarry(isMarryId);

								QueryBuilder<UserInfo> qb = GreenDaoService
										.getInstance().getDaoSession()
										.getUserInfoDao().queryBuilder();

								DeleteQuery<UserInfo> db = qb.where(
										Properties.UserId.eq(userInfo
												.getUserId())).buildDelete();

								db.executeDeleteWithoutDetachingEntities();

								GreenDaoService.getInstance().getDaoSession()
										.getUserInfoDao().insert(userInfo);

								showShortToast("个人信息保存成功");

								switch (state) {
								case PER:
									openActivity(HomePageActivity.class);
									break;

								case SET:
									finishBase();
									break;
								}

							} else {
								showShortToast(savaInfoClient.getMessage());
							}

							return false;
						}

						public boolean onEmpty() {
							// TODO Auto-generated method stub
							showShortToast("个人信息保存失败");
							ShowUtil.closeHttpDialog();
							return false;
						}
					});
			savaInfoClient.setPramas(new Object[] {
					SharedPreferencesUtil.getString("mobile"), areaId,
					provinceId, cityId, sexId, ageId, ageName, jobId, jobName,
					isNetId, isMarryId

			});
			ShowUtil.openHttpDialog("信息保存中...");
			savaInfoClient.submitRequest();
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
			setSex();
			break;
		case R.id.set_fragment_isnet:
			setNet();
			break;
		case R.id.set_fragment_ismarry:
			setMarry();
			;
			break;
		case R.id.btnAge:
			showAgeDialog();

			break;
		case R.id.btnIndustry:
			showJobDialog();
			break;
		case R.id.skip:
			openActivity(HomePageActivity.class);
			break;
		}
	}

	/**
	 * 设置年龄
	 * 
	 * @param ageId
	 * @param ageName
	 */
	public void setAge(String ageId, String ageName) {
		this.ageId = ageId;
		this.ageName = ageName;
		ageBtn.setText(ageName);

	}

	/**
	 * 设置性别
	 */
	private void setSex() {
		if (sexId.equals("male")) {
			sexId = "female";
		} else {
			sexId = "male";
		}
		isSex();
	}

	private void isSex() {
		if (sexId.equals("female")) {
			sexBtn.setBackgroundResource(R.drawable.personal_switch_womwen);
		} else {
			sexBtn.setBackgroundResource(R.drawable.personal_switch_womwen_1);
		}

	}

	/**
	 * 设置是否网购
	 */
	private void setNet() {
		if (isNetId.equals("0")) {
			isNetId = "1";
		} else {
			isNetId = "0";
		}
		isnet();
	}

	private void isnet() {
		if (isNetId.equals("0")) {
			isNetBtn.setBackgroundResource(R.drawable.personal_switch_noyes);

		} else {
			isNetBtn.setBackgroundResource(R.drawable.personal_switch_no);

		}

	}

	/**
	 * 是否结婚
	 */
	private void setMarry() {
		if (isMarryId.equals("0")) {
			isMarryId = "1";
		} else {
			isMarryId = "0";
		}
		ismarry();
	}

	private void ismarry() {
		if (isMarryId.equals("0")) {
			ismarryBtn.setBackgroundResource(R.drawable.personal_switch_noyes);
		} else {
			ismarryBtn.setBackgroundResource(R.drawable.personal_switch_no);
		}
	}

	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		if (arg2 != null) {

			provinceName = arg2.getStringExtra("selectProvince");
			provinceId = arg2.getStringExtra("selectProvinceId");

			cityName = arg2.getStringExtra("selectCity");
			cityId = arg2.getStringExtra("selectCityId");

			areaName = arg2.getStringExtra("selectArea");
			areaId = arg2.getStringExtra("selectAreaId");

			province.setText(provinceName);
			city.setText(cityName);
			areas.setText(areaName);

		}
	}

	private AgeAdapter ageAdapter;
	private CustomDialog ageDialog;

	private void showAgeDialog() {
		if (ageDialog == null) {
			ageAdapter = new AgeAdapter();
			ageAdapter.setContext(this);
			ArrayList<Age> list = new ArrayList<Age>();
			list.addAll(Age.getList());
			ageAdapter.setList(list);
			ageAdapter.setListener(new AdapterItemListener() {

				public boolean onAdapterItemListener(Object... objects) {
					// TODO Auto-generated method stub
					ageDialog.dismiss();
					Age age = (Age) objects[0];
					ageId = age.getIdStr();
					ageName = age.getName();
					ageBtn.setText(ageName);
					return false;
				}
			});
			ageDialog = ShowUtil.getListView(this, "请选择年龄", ageAdapter);

		}
		ageDialog.show();

	}

	private CustomDialog jobDialog;
	private JobAdapter jobAdapter;

	private void showJobDialog() {
		if (jobDialog == null) {
			jobAdapter = new JobAdapter();
			jobAdapter.setContext(this);
			ArrayList<Occupation> list = new ArrayList<Occupation>();
			list.addAll(Occupation.getList());
			jobAdapter.setList(list);
			jobAdapter.setListener(new AdapterItemListener() {

				public boolean onAdapterItemListener(Object... objects) {
					// TODO Auto-generated method stub
					jobDialog.dismiss();
					Occupation occ = (Occupation) objects[0];
					jobId = occ.getIdStr();
					jobName = occ.getName();
					jobBtn.setText(jobName);
					return false;
				}
			});
			jobDialog = ShowUtil.getListView(this, "请选择职业", jobAdapter);
		}
		jobDialog.show();
	}

	private void initUserInfo() {
		List<UserInfo> list = GreenDaoService
				.getInstance()
				.getDaoSession()
				.getUserInfoDao()
				.queryBuilder()
				.where(Properties.UserId.eq(SharedPreferencesUtil
						.getString("mobile"))).list();
		if (list != null && list.size() > 0) {
			UserInfo userInfo = list.get(0);
			sexId = userInfo.getSex();
			isSex();

			ageId = userInfo.getAgeId();
			ageName = userInfo.getAgeName();
			ageBtn.setText(ageName);

			jobId = userInfo.getJobId();
			jobName = userInfo.getJobName();
			jobBtn.setText(jobName);

			provinceId = userInfo.getProvinceId();
			provinceName = userInfo.getProvinceName();
			province.setText(provinceName);

			cityId = userInfo.getCityId();
			cityName = userInfo.getCityName();
			city.setText(cityName);

			areaId = userInfo.getAreaId();
			areaName = userInfo.getAreaName();
			areas.setText(areaName);

			isNetId = userInfo.getIsNetShoping();
			isnet();

			isMarryId = userInfo.getIsMarry();
			ismarry();
		} else {
			sexId = "male";
			isNetId = "1";
			isMarryId = "1";
		}

	}

}
