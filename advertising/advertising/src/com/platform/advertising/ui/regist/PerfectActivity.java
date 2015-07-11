package com.platform.advertising.ui.regist;

import sxp.android.framework.ui.BaseActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.util.ShowUtil;

/**
 * 完善资料
 * 
 * @author xiaoping.shan
 *
 */
public class PerfectActivity extends BaseActivity implements OnClickListener {

	private ImageButton back;
	private TextView title;
	private TextView skip;
	private View save;
	
	private Button sex;
	private Button isMarried;
	private Button isNetShop;
	private Button age;
	private Button work;
	private EditText province;
	private EditText city;
	private EditText area;
	
	

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_material);
		back = (ImageButton) findViewById(R.id.set_fragment_back);
		title = (TextView) findViewById(R.id.set_frament_title);
		skip = (TextView) findViewById(R.id.set_fragment_skip);
		save = findViewById(R.id.set_frament_save);

		title.setText("完善资料");
		skip.setText("跳过>");
		back.setOnClickListener(this);
		save.setOnClickListener(this);
		skip.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set_fragment_back:
			finishBase();
			break;
		case R.id.set_fragment_skip:
			finishBase();
			break;
		case R.id.set_frament_save:
			ShowUtil.showDialog(this,"恭喜你，注册成功",2000);
			break;

		}

	}

	
}
