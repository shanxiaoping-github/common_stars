package com.platform.advertising.ui.data;

import sxp.android.framework.ui.BaseActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.platform.advertising.R;
import com.platform.advertising.ui.PhotoDialog;
import com.platform.advertising.ui.PhotoDialog.CallBack;
import com.platform.advertising.util.ShowUtil;

/**
 * 上传资料
 * 
 * @author xiaoping.shan
 *
 */
public class UploadDataActivity extends BaseActivity implements OnClickListener {

	private Button btnCamera,btnDelete;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.upload_data_layout);
		setBackButton();

		findViewById(R.id.upload_data_all_submit).setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.upload_data_all_submit:
			ShowUtil.showDialog(this, "提交成功", 2000);
			break;
		case R.id.btnDelete:
			showShortToast("删除");
			break;
		case R.id.btnCamera:
			PhotoDialog dialog = new PhotoDialog(mContext, new CallBack() {

				public void clickPhoto() {
					showShortToast("相册选择");
				}

				public void clickCamera() {
					showShortToast("拍照");
				}
			});
			dialog.show();
			break;
		}
	}

}
