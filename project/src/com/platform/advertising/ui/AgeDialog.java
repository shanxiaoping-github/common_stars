package com.platform.advertising.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.platform.advertising.R;

public class AgeDialog extends Dialog {

	private Context context;
	private CallBack clickListener;
	

	public AgeDialog(Context context, CallBack clickListener) {
		super(context, R.style.Dialog);
		this.context = context;
		this.clickListener = clickListener;
	}

	public AgeDialog(Context context, int style) {
		super(context, R.style.Dialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_age);
		findViewById(R.id.lAge20).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.selectAge("0");
						}
					}
				});
		findViewById(R.id.lAge35).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.selectAge("1");
						}
					}
				});
		findViewById(R.id.lAge50).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.selectAge("2");
						}
					}
				});
		findViewById(R.id.lAge100).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						dismiss();
						if (clickListener != null) {
							clickListener.selectAge("3");
						}
					}
				});
	}

	public interface CallBack {
		void selectAge(String age);
	}
}
