package com.platform.advertising.util;

import sxp.android.framework.adapter.BaseAdapter;
import sxp.android.framework.data.BaseData;
import sxp.android.framework.manager.ActivityManager;
import sxp.android.framework.util.StringUtil;
import sxp.android.framework.view.CustomDialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.platform.advertising.R;

/**
 * 工具类
 * 
 * @author xiaoping.shan
 *
 */
public class ShowUtil {

	/* 拍照响应 */
	public interface PhotoResponse {
		/* 选择相册 */
		public void selectAlbum();

		/* 选择拍照 */
		public void selectTakingPictures();

	}

	/* 加载布局文件 */
	public static View LoadXmlView(Context context, int xmlId) {
		LayoutInflater flat = LayoutInflater.from(context);
		View view = flat.inflate(xmlId, null);
		return view;
	}

	/* 开启httpDialog */
	private static CustomDialog httpDialog = null;

	public static void openHttpDialog(String showText) {

		httpDialog = getCustomDialog(ActivityManager.getInstance().peek(),
				R.layout.httpdialog, 0, R.style.Dialog);
		((TextView) httpDialog.getcView().findViewById(
				R.id.httpdialog_show_text)).setText(showText);
		httpDialog.setCanceledOnTouchOutside(false);
		httpDialog.show();

	}
	

	/* 关闭dialog */
	public static void closeHttpDialog() {
		if (httpDialog != null) {
			httpDialog.dismiss();
			httpDialog = null;
		}
	}

	/**
	 * 获得自定义的dialog
	 */
	public static CustomDialog getCustomDialog(Context context, int layoutId,
			int type, int dialogType) {
		View contentView = LoadXmlView(context, layoutId);
		CustomDialog customDialog = new CustomDialog(context, contentView,
				dialogType);
		customDialog.setType(type);
		return customDialog;
	}

	/**
	 * 拍照
	 * 
	 * @param context
	 *            上下文
	 * @param titleStr
	 *            标题
	 * @param response
	 *            响应
	 * @return
	 */
	public static CustomDialog getPhotoView(Context context, String titleStr,
			int dailogType, final PhotoResponse response) {

		final CustomDialog dialog = getCustomDialog(context,
				R.layout.select_image, 1, R.style.Dialog_full);
		View view = dialog.getcView();
		TextView title = (TextView) view.findViewById(R.id.select_image_title);
		TextView album = (TextView) view.findViewById(R.id.select_image_album);
		TextView takingPictures = (TextView) view
				.findViewById(R.id.select_image_taking_pictures);
		TextView cancle = (TextView) view
				.findViewById(R.id.select_image_cancle);

		title.setText(titleStr);
		album.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				response.selectAlbum();
			}
		});

		takingPictures.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				response.selectTakingPictures();
			}
		});

		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		return dialog;
	}

	/**
	 * 获得listview Dialog
	 * 
	 * @param context
	 * @param title
	 * @param adapter
	 * @return
	 */
	public static CustomDialog getListView(Context context, String title,
			BaseAdapter<? extends BaseData> adapter) {
		return getListView(context, title, adapter, null, "");
	}

	public static CustomDialog getListView(Context context, String title,
			BaseAdapter<? extends BaseData> adapter, OnClickListener click,
			String rightTxt) {
		final CustomDialog dialog = getCustomDialog(context,
				R.layout.select_listview_dialog, 1, R.style.Dialog_full);
		View contentView = dialog.getcView();
		TextView titleTxt = (TextView) contentView
				.findViewById(R.id.select_listview_title);
		titleTxt.setText(title);
		Button canleBtn = (Button) contentView
				.findViewById(R.id.select_listview_canle);
		canleBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		Button allBtn = (Button) contentView
				.findViewById(R.id.select_listview_all);

		if (!StringUtil.isEmpty(rightTxt)) {
			allBtn.setText(rightTxt);
		}
		if (click != null) {
			allBtn.setOnClickListener(click);
		} else {
			allBtn.setVisibility(View.GONE);
		}

		ListView listView = (ListView) contentView
				.findViewById(R.id.select_listview_listview);
		listView.setAdapter(adapter);

		return dialog;
	}

	/**
	 * 显示
	 * 
	 * @param context
	 *            上下文
	 * @param promtStr
	 *            提示
	 * @param delay
	 *            延时
	 */
	public static void showDialog(Context context, String promtStr, int delay) {
		final CustomDialog registDialog = ShowUtil.getCustomDialog(context,
				R.layout.prompt_dialog, 1, R.style.Dialog);
		TextView registTxt = (TextView) registDialog.getcView().findViewById(
				R.id.prompt_dialog_title);
		registTxt.setText(promtStr);
		registDialog.show();
		new Handler().postDelayed(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				registDialog.dismiss();
			}
		}, delay);
	}

}
