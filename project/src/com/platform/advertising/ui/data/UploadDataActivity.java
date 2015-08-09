package com.platform.advertising.ui.data;

import java.io.File;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.util.FileUtil;
import sxp.android.framework.util.MathUtil;
import sxp.android.framework.util.StringUtil;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.platform.advertising.R;
import com.platform.advertising.baidu_sdk.LBSActivity;
import com.platform.advertising.framework.MyBaseActivity;
import com.platform.advertising.http.HttpEnterpriseInformationSubmitClient;
import com.platform.advertising.ui.CitySelectActivity;
import com.platform.advertising.ui.PhotoDialog;
import com.platform.advertising.ui.PhotoDialog.CallBack;
import com.squareup.picasso.Picasso;

/**
 * 上传资料
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(value = R.layout.upload_data_layout)
public class UploadDataActivity extends MyBaseActivity implements
		OnClickListener {
	@ID(value = R.id.btnCamera, isBindListener = true)
	private Button btnCamera;
	@ID(value = R.id.btnDelete, isBindListener = true)
	private Button btnDelete;

	@ID(R.id.show_img)
	private ImageView showImg;

	@ID(value = R.id.upload_data_all_submit, isBindListener = true)
	private View submit;

	@ID(value = R.id.btnUpload)
	private Button sureBtn;

	@ID(value = R.id.company_name)
	private EditText companyName;

	@ID(value = R.id.area_province)
	private TextView area_province;

	@ID(value = R.id.area_city)
	private TextView area_city;

	@ID(value = R.id.area_area)
	private TextView area_area;

	@ID(value = R.id.specific_address)
	private EditText specific_address;

	@ID(value = R.id.company_introduce)
	private EditText company_introduce;

	@ID(value = R.id.contact_name)
	private EditText contactPerson;

	@ID(value = R.id.phone)
	private EditText phone;

	@ID(value = R.id.upload_data_adress, isBindListener = true)
	private View selectAdress;

	@ID(value = R.id.lCenter, isBindListener = true)
	private View lbs;

	@ID(value = R.id.location)
	private TextView location;

	private String longitude = "0";
	private String latitude = "0";

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setBackButton();
		tempFile = FileUtil.getSDPath() + StringUtil.getFileNameByUIID()
				+ ".png";

	}

	private String tempFile;

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.upload_data_all_submit:
			submit();
			break;
		case R.id.btnDelete:
			// showShortToast("删除");
			setSureBtn(false);
			break;
		case R.id.btnCamera:
			PhotoDialog dialog = new PhotoDialog(UploadDataActivity.this,
					new CallBack() {

						public void clickPhoto() {
							// showShortToast("相册选择");
							sxp.android.framework.util.ShowUtil.selectAlbum(
									UploadDataActivity.this, 0);
						}

						public void clickCamera() {
							// showShortToast("拍照");
							sxp.android.framework.util.ShowUtil
									.selectTakingPictures(
											UploadDataActivity.this, new File(
													tempFile), 1);
						}
					});
			dialog.show();
			break;
		case R.id.upload_data_adress:
			openActivityResult(CitySelectActivity.class, 2);
			break;
		case R.id.lCenter:
			lbs();
			break;
		}
	}

	/**
	 * 定位
	 */
	private void lbs() {
		// BaiduLBSManager.getInstance().startLBS();
		openActivityResult(LBSActivity.class, 3);
	}

	private String currentUploadPath;
	private String provinceId;
	private String cityId;
	private String areaId;
	private File file;

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		case 0:
			if (arg2 != null) {
				String path = sxp.android.framework.util.ShowUtil.getAlbumPath(
						this, arg2);
				currentUploadPath = path;
				file = new File(path);
				Picasso.with(this)
						.load(file)
						.resize(SXPApplication.getWindowWidth(this) - 30,
								MathUtil.diptopx(this, 160)).into(showImg);
				setSureBtn(true);
			}
			break;

		case 1:
			if (arg1 == Activity.RESULT_OK) {
				currentUploadPath = tempFile;
				file = new File(tempFile);
				Picasso.with(this)
						.load(file)
						.resize(SXPApplication.getWindowWidth(this) - 30,
								MathUtil.diptopx(this, 160)).into(showImg);
				setSureBtn(true);
			}
			break;
		case 2:
			if (arg2 != null) {
				area_province.setText(arg2.getStringExtra("selectProvince"));
				area_city.setText(arg2.getStringExtra("selectCity"));
				area_area.setText(arg2.getStringExtra("selectArea"));
				provinceId = arg2.getStringExtra("selectProvinceId");
				cityId = arg2.getStringExtra("selectCityId");
				areaId = arg2.getStringExtra("selectAreaId");
			}

			break;
		case 3:
			if (arg2 != null) {

				BDLocation bdLocation = arg2.getExtras().getParcelable(
						BDLocation.class.getName());
				longitude = String.valueOf(bdLocation.getLongitude());
				latitude = String.valueOf(bdLocation.getLatitude());

				location.setText("经度:" + bdLocation.getLongitude() + ",纬度:"
						+ bdLocation.getLatitude() + "\n"
						+ bdLocation.getAddrStr());

			}
			break;
		}
	}

	private void setSureBtn(boolean isEnable) {
		if (isEnable) {
			btnCamera.setVisibility(View.GONE);
			sureBtn.setEnabled(true);
			sureBtn.setText("确认上传");
			sureBtn.setBackgroundResource(R.drawable.blue_btn_back_ground);
		} else {
			file = null;
			currentUploadPath = "";
			showImg.setImageDrawable(null);
			btnCamera.setVisibility(View.VISIBLE);
			sureBtn.setEnabled(false);
			sureBtn.setText("未上传");
			sureBtn.setBackgroundResource(R.drawable.up_bou_off);
		}

	}

	/**
	 * 提交
	 */
	private void submit() {
		String companyNameStr = companyName.getText().toString();
		String specificAddressStr = specific_address.getText().toString();
		String companyIntroduceStr = company_introduce.getText().toString();
		String contractPersonStr = contactPerson.getText().toString();
		String phoneStr = phone.getText().toString();

		if (!sureBtn.isEnabled()) {
			showLongToast("请选取或拍摄图片");
		} else if (StringUtil.isEmpty(companyNameStr)) {
			showLongToast("请填写公司名称");
		} else if (StringUtil.isEmpty(contractPersonStr)) {
			showLongToast("请填写联系人");
		} else if (StringUtil.isEmpty(phoneStr)) {
			showLongToast("请填写手机号");
		} else if (!isArea()) {
			showLongToast("请选择地区");
		} else if (StringUtil.isEmpty(specificAddressStr)) {
			showLongToast("请填写详细地址");
		} else if (StringUtil.isEmpty(companyIntroduceStr)) {
			showLongToast("请填写公司介绍");
		} else {
			final HttpEnterpriseInformationSubmitClient client = new HttpEnterpriseInformationSubmitClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					com.platform.advertising.util.ShowUtil.closeHttpDialog();
					showShortToast("提交失败");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					com.platform.advertising.util.ShowUtil.closeHttpDialog();
					showShortToast(client.getMessage());
					if (client.isSuccess()) {
						finishBase();
					}
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					com.platform.advertising.util.ShowUtil.closeHttpDialog();
					showShortToast("提交失败");
					return false;
				}
			});
			// "companyName",
			// "districtId",
			// "provinceId",
			// "cityId",
			// "address",
			// "phone",
			// "contactPerson",
			// "introduce",
			// "longitude",
			// "latitude",
			// "uploadFile"

			client.setPramas(new Object[] { companyNameStr, areaId, provinceId,
					cityId, specificAddressStr, phoneStr, contractPersonStr,
					companyIntroduceStr, longitude,latitude,file });
			com.platform.advertising.util.ShowUtil.openHttpDialog("提交中...");
			client.submitRequest();

		}

	}

	private boolean isArea() {
		String province = area_province.getText().toString();
		String city = area_city.getText().toString();
		String area = area_area.getText().toString();
		if (StringUtil.isEmpty(province) && StringUtil.isEmpty(city)
				&& StringUtil.isEmpty(area)) {
			return false;
		}
		return true;
	}

}
