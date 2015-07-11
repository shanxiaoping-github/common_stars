package com.platform.advertising.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.platform.advertising.R;

public class PhotoDialog extends Dialog {

	private Context context;
	private CallBack clickListener;

	public PhotoDialog(Context context, CallBack clickListener) {
		super(context, R.style.Dialog);
		this.context = context;
		this.clickListener = clickListener;
	}

	public PhotoDialog(Context context, int style) {
		super(context, R.style.Dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_photo);
		findViewById(R.id.lCamera).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.clickCamera();
						}
					}
				});
		findViewById(R.id.lPhoto).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.clickPhoto();
						}
					}
				});
	}

	public interface CallBack {
		void clickCamera();

		void clickPhoto();
	}
}
