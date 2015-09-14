package com.platform.advertising.view.city;

import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.util.ShowUtil;
import sxp.android.framework.util.StringUtil;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.ui.data.AdverTitleData;

/**
 * 广告题目view
 * 
 * @author shanxiaoping
 */
public class AdvertTitleView extends LinearLayout {
	/**
	 * 点击选中响应接口
	 * @author shanxiaoping
	 *
	 */
	
	public static interface SelectListener{
		public void action(Object...objects);
	}
	private SelectListener actionListen = null;
	
	
	public SelectListener getActionListen() {
		return actionListen;
	}

	public void setActionListern(SelectListener actionListen) {
		this.actionListen = actionListen;
	}
	
	private int index;
	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public AdvertTitleView(Context context, AdverTitleData adverTitleData) {
		super(context);
		setOrientation(LinearLayout.VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		initView(adverTitleData);
		// TODO Auto-generated constructor stub
	}

	// 广告数据
	private AdverTitleData adverTitleData;
	

	public void initView(AdverTitleData adverTitleData) {
		this.adverTitleData = adverTitleData;
		this.addTitleView();

	}

	private void addTitleView() {
		if (!StringUtil.isEmpty(adverTitleData.getaKey())
				&& !StringUtil.isEmpty(adverTitleData.getaValue())
				&& !adverTitleData.getaValue().equals("null")) {
			final View aView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView aImge = (ImageView) aView
					.findViewById(R.id.imageView1);
			TextView aTxt = (TextView) aView.findViewById(R.id.textView1);
			aTxt.setText(adverTitleData.getaValue());
			if (StringUtil.isEmpty(adverTitleData.getIsKey())) {
				aView.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (adverTitleData.getIsView() != null) {
							adverTitleData.getIsView().setBackground(
									SXPApplication
											.getAppContext()
											.getResources()
											.getDrawable(
													R.drawable.item_selector));
						}
						if(actionListen!=null){
							actionListen.action(new Object[]{index});
						}
						
						aView.setBackgroundResource(R.color.answer_checked);
						adverTitleData.setIsView(aView);

						if (adverTitleData.getIsImageView() != null) {
							adverTitleData.getIsImageView().setImageResource(
									R.drawable.topic_choice);
						}
						aImge.setImageResource(R.drawable.topic_choice_1);
						adverTitleData.setIsImageView(aImge);
						adverTitleData.setIsKey("A");
					}
				});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("A")){
					aView.setBackgroundResource(R.color.answer_checked);
					aImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("A")){
					aImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}
			
			this.addView(aView);
		}

		if (!StringUtil.isEmpty(adverTitleData.getbKey())
				&& !StringUtil.isEmpty(adverTitleData.getbValue())
				&& !adverTitleData.getbValue().equals("null")) {
			final View bView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView bImge = (ImageView) bView
					.findViewById(R.id.imageView1);
			TextView bTxt = (TextView) bView.findViewById(R.id.textView1);
			bTxt.setText(adverTitleData.getbValue());
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
			bView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (adverTitleData.getIsView() != null) {
						adverTitleData.getIsView().setBackground(
								SXPApplication.getAppContext().getResources()
										.getDrawable(R.drawable.item_selector));
					}
					if(actionListen!=null){
						actionListen.action(new Object[]{index});
					}
					bView.setBackgroundResource(R.color.answer_checked);
					adverTitleData.setIsView(bView);

					if (adverTitleData.getIsImageView() != null) {
						adverTitleData.getIsImageView().setImageResource(
								R.drawable.topic_choice);
					}
					bImge.setImageResource(R.drawable.topic_choice_1);
					adverTitleData.setIsImageView(bImge);
					adverTitleData.setIsKey("B");
				}
			});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("B")){
					bView.setBackgroundResource(R.color.answer_checked);
					bImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("B")){
					bImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}
			
			
			this.addView(bView);
		}

		if (!StringUtil.isEmpty(adverTitleData.getcKey())
				&& !StringUtil.isEmpty(adverTitleData.getcValue())
				&& !adverTitleData.getcValue().equals("null")) {
			final View cView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView cImge = (ImageView) cView
					.findViewById(R.id.imageView1);
			TextView cTxt = (TextView) cView.findViewById(R.id.textView1);
			cTxt.setText(adverTitleData.getcValue());
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
			cView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (adverTitleData.getIsView() != null) {
						adverTitleData.getIsView().setBackground(
								SXPApplication.getAppContext().getResources()
										.getDrawable(R.drawable.item_selector));
					}
					if(actionListen!=null){
						actionListen.action(new Object[]{index});
					}
					cView.setBackgroundResource(R.color.answer_checked);
					adverTitleData.setIsView(cView);

					if (adverTitleData.getIsImageView() != null) {
						adverTitleData.getIsImageView().setImageResource(
								R.drawable.topic_choice);
					}
					cImge.setImageResource(R.drawable.topic_choice_1);
					adverTitleData.setIsImageView(cImge);
					adverTitleData.setIsKey("C");
				}
			});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("C")){
					cView.setBackgroundResource(R.color.answer_checked);
					cImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("C")){
					cImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}
			this.addView(cView);
		}

		if (!StringUtil.isEmpty(adverTitleData.getdKey())
				&& !StringUtil.isEmpty(adverTitleData.getdValue())
				&& !adverTitleData.getdValue().equals("null")) {
			final View dView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView dImge = (ImageView) dView
					.findViewById(R.id.imageView1);
			TextView dTxt = (TextView) dView.findViewById(R.id.textView1);
			dTxt.setText(adverTitleData.getdValue());
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
			dView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (adverTitleData.getIsView() != null) {
						adverTitleData.getIsView().setBackground(
								SXPApplication.getAppContext().getResources()
										.getDrawable(R.drawable.item_selector));
					}
					if(actionListen!=null){
						actionListen.action(new Object[]{index});
					}
					dView.setBackgroundResource(R.color.answer_checked);
					adverTitleData.setIsView(dView);

					if (adverTitleData.getIsImageView() != null) {
						adverTitleData.getIsImageView().setImageResource(
								R.drawable.topic_choice);
					}
					dImge.setImageResource(R.drawable.topic_choice_1);
					adverTitleData.setIsImageView(dImge);
					adverTitleData.setIsKey("D");
				}
			});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("D")){
					dView.setBackgroundResource(R.color.answer_checked);
					dImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("D")){
					dImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}

			this.addView(dView);
		}

		if (!StringUtil.isEmpty(adverTitleData.geteKey())
				&& !StringUtil.isEmpty(adverTitleData.geteValue())
				&& !adverTitleData.geteValue().equals("null")) {
			final View eView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView eImge = (ImageView) eView
					.findViewById(R.id.imageView1);
			TextView eTxt = (TextView) eView.findViewById(R.id.textView1);
			eTxt.setText(adverTitleData.geteValue());
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
			eView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub

					if (adverTitleData.getIsView() != null) {
						adverTitleData.getIsView().setBackground(
								SXPApplication.getAppContext().getResources()
										.getDrawable(R.drawable.item_selector));
					}
					if(actionListen!=null){
						actionListen.action(new Object[]{index});
					}
					eView.setBackgroundResource(R.color.answer_checked);
					adverTitleData.setIsView(eView);

					if (adverTitleData.getIsImageView() != null) {
						adverTitleData.getIsImageView().setImageResource(
								R.drawable.topic_choice);
					}
					eImge.setImageResource(R.drawable.topic_choice_1);
					adverTitleData.setIsImageView(eImge);
					adverTitleData.setIsKey("E");
				}
			});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("E")){
					eView.setBackgroundResource(R.color.answer_checked);
					eImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("E")){
					eImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}
			this.addView(eView);
		}

		if (!StringUtil.isEmpty(adverTitleData.getfKey())
				&& !StringUtil.isEmpty(adverTitleData.getfValue())
				&& !adverTitleData.getfValue().equals("null")) {
			final View fView = ShowUtil.LoadXmlView(
					SXPApplication.getAppContext(),
					R.layout.answer_start_layout_item);
			final ImageView fImge = (ImageView) fView
					.findViewById(R.id.imageView1);
			TextView fTxt = (TextView) fView.findViewById(R.id.textView1);
			fTxt.setText(adverTitleData.getfValue());
			if(StringUtil.isEmpty(adverTitleData.getIsKey())){
			fView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (adverTitleData.getIsView() != null) {
						adverTitleData.getIsView().setBackground(
								SXPApplication.getAppContext().getResources()
										.getDrawable(R.drawable.item_selector));
					}
					if(actionListen!=null){
						actionListen.action(new Object[]{index});
					}
					fView.setBackgroundResource(R.color.answer_checked);
					adverTitleData.setIsView(fView);

					if (adverTitleData.getIsImageView() != null) {
						adverTitleData.getIsImageView().setImageResource(
								R.drawable.topic_choice);
					}
					fImge.setImageResource(R.drawable.topic_choice_1);
					adverTitleData.setIsImageView(fImge);
					adverTitleData.setIsKey("F");
				}
			});
			}else{
				if(adverTitleData.getCorrectKey().equalsIgnoreCase("F")){
					fView.setBackgroundResource(R.color.answer_checked);
					fImge.setImageResource(R.drawable.topic_choice_1);
				}
				if(!adverTitleData.getCorrectKey().equalsIgnoreCase(adverTitleData.getIsKey())&&adverTitleData.getIsKey().equalsIgnoreCase("F")){
					fImge.setImageResource(R.drawable.topic_choice_2);
				}
				
			}
			this.addView(fView);
		}

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
