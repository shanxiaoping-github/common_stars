package com.platform.advertising.ui.home;

import sxp.android.framework.annotation.ID;
import sxp.android.framework.annotation.LAYOUT;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.MathUtil;
import sxp.android.framework.util.StringUtil;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.platform.advertising.R;
import com.platform.advertising.http.HttpAddAccountClient;
import com.platform.advertising.http.HttpGetAccountBalanceClient;
import com.platform.advertising.http.HttpWithdrawalClient;
import com.platform.advertising.ui.WithdrawalRecordActivity;
import com.platform.advertising.ui.home.data.Acount;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.util.ShowUtil;

/**
 * 账户
 * 
 * @author xiaoping.shan
 *
 */
@LAYOUT(R.layout.account_layout)
public class AccountActivity extends BaseActivity implements OnClickListener {

	@ID(value = R.id.back, isBindListener = true)
	private ImageButton back;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		super.layout();
		final HttpGetAccountBalanceClient client = new HttpGetAccountBalanceClient();
		client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

			public boolean onTimeout() {
				// TODO Auto-generated method stub
				return false;
			}

			public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
				// TODO Auto-generated method stub
				if (client.getAccount() != null) {
					accountDatailLayout(client.getAccount());
				} else {
					accountAddLayout();
				}
				return false;
			}

			public boolean onEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
		});
		client.setPramas(new Object[] { SharedPreferencesUtil
				.getString("mobile") });
		client.submitRequest();
	}

	/**
	 * 账户详情布局
	 * 
	 * @param acount
	 */

	private TextView changeAccount;
	
	private TextView withdrawal;

	private EditText blance;

	private TextView desc;

	private EditText zfbAccount;

	private EditText realName;

	private TextView record;
	private Acount account;
	
	private Button submitBtn;
	
	public void accountDatailLayout(Acount account) {
		this.account = account;
		setContentView(R.layout.account_fragment_layout);
		back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(this);

		changeAccount = (TextView)findViewById(R.id.account_fragment_change);
		changeAccount.setOnClickListener(this);
		
		withdrawal = (TextView) findViewById(R.id.account_fragment_withdrawal);
		withdrawal.setOnClickListener(this);

		blance = (EditText) findViewById(R.id.account_fragment_blance);

		desc = (TextView) findViewById(R.id.account_fragment_des);

		zfbAccount = (EditText) findViewById(R.id.account_fragment_zfb_account);

		realName = (EditText) findViewById(R.id.account_fragment_realname);
		record = (TextView) findViewById(R.id.record);
		record.setOnClickListener(this);
		
		submitBtn = (Button)findViewById(R.id.account_fragment_submit);
		submitBtn.setOnClickListener(this);

		if (StringUtil.isEmpty(account.getBalance())
				|| account.getBalance().equals("null")) {
			blance.setText("未知");
		} else {
			blance.setText(account.getBalance());
		}

		if (StringUtil.isEmpty(account.getAlipayAccount())
				|| account.getAlipayAccount().equals("null")) {
			zfbAccount.setText("未知");
		} else {
			zfbAccount.setText(account.getAlipayAccount());
		}
		if (StringUtil.isEmpty(account.getName())
				|| account.getName().equals("null")) {
			realName.setText("未知");
		} else {
			realName.setText(account.getName());
		}

	}

	/**
	 * 账户添加布局
	 */
	private EditText name;
	private EditText zhifubao;
	private TextView add;

	public void accountAddLayout() {
		setContentView(R.layout.add_account_layout);
		back = (ImageButton) findViewById(R.id.back);
		back.setOnClickListener(this);
		name = (EditText) findViewById(R.id.add_account_realname);
		zhifubao = (EditText) findViewById(R.id.add_account_zhifubao_account);
		add = (TextView) findViewById(R.id.add_account_add);
		add.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.account_fragment_withdrawal:
			withdrawal();
			break;
		case R.id.add_account_add:
			add();
			break;
		case R.id.back:
			finishBase();
			break;
		case R.id.record:
			record();
			break;
		case R.id.account_fragment_change:
			changeAccount();
			break;
		case R.id.account_fragment_submit:
			uploadAccount();
			break;
		}
	}
	/**
	 * 更新账号信息
	 */
	private void uploadAccount(){
		
		final String nameStr = realName.getText().toString();
		final String zhifubaoStr = zfbAccount.getText().toString();
		if (StringUtil.isEmpty(nameStr)){
			showShortToast("真实姓名不能为空");
		}else if (StringUtil.isEmpty(zhifubaoStr)){
			showShortToast("支付宝账号不能为空");
		}else {

			final HttpAddAccountClient client = new HttpAddAccountClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("修改失败");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast(client.getMessage());
					if (client.isSuccess()) {
						changeAccount.setVisibility(View.VISIBLE);
						submitBtn.setVisibility(View.GONE);
						zfbAccount.setEnabled(false);
					    realName.setEnabled(false);
					}
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("修改失败");
					return false;
				}
			});
			client.setPramas(new Object[] {
					SharedPreferencesUtil.getString("mobile"), nameStr,
					zhifubaoStr });
			ShowUtil.openHttpDialog("修改中...");
			client.submitRequest();
		}
		
	}
	/**
	 * 修改
	 */
	private void changeAccount(){
		if(changeAccount.getText().toString().equalsIgnoreCase("修改")){
			changeAccount.setText("取消");
			submitBtn.setVisibility(View.VISIBLE);
			zfbAccount.setEnabled(true);
		    realName.setEnabled(true);
		}else{
			changeAccount.setText("修改");
			submitBtn.setVisibility(View.GONE);
			zfbAccount.setEnabled(false);
		    realName.setEnabled(false);
			
		}
	}

	/**
	 * 体现记录
	 */
	private void record() {
		openActivity(WithdrawalRecordActivity.class);
	}

	/**
	 * 添加账户
	 */
	private void add() {

		final String nameStr = name.getText().toString();
		final String zhifubaoStr = zhifubao.getText().toString();
		if (StringUtil.isEmpty(nameStr) || StringUtil.isEmpty(zhifubaoStr)) {
			showShortToast("请完善账户信息");
		} else {

			final HttpAddAccountClient client = new HttpAddAccountClient();
			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

				public boolean onTimeout() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("添加失败");
					return false;
				}

				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast(client.getMessage());
					if (client.isSuccess()) {
						Acount acount = new Acount();
						acount.setName(nameStr);
						acount.setAlipayAccount(zhifubaoStr);
						acount.setBalance("0");
						acount.setAmount("0");
						acount.setUsername(SharedPreferencesUtil
								.getString("mobile"));
						accountDatailLayout(acount);
					}
					return false;
				}

				public boolean onEmpty() {
					// TODO Auto-generated method stub
					ShowUtil.closeHttpDialog();
					showShortToast("添加失败");
					return false;
				}
			});
			client.setPramas(new Object[] {
					SharedPreferencesUtil.getString("mobile"), nameStr,
					zhifubaoStr });
			ShowUtil.openHttpDialog("添加中...");
			client.submitRequest();
		}
	}

	/**
	 * 提现
	 */
	private void withdrawal() {

		final EditText amountEdit = new EditText(this);
		amountEdit.setMaxLines(10);
		new AlertDialog.Builder(this)
				.setTitle("请输入提现金额")
				.setView(amountEdit)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String amountStr = amountEdit.getText().toString();
						if (!MathUtil.isDoubleNumber(amountStr)) {
							showLongToast("请输入正确的金额");
						} else if (account == null) {
							showLongToast("账户异常");
						} else if (!MathUtil.isDoubleNumber(account
								.getBalance())) {
							showShortToast("账户余额异常");
						} else {
							double amountValue = Double.valueOf(amountStr);
							double blanceValue = Double.valueOf(account
									.getBalance());
							if (amountValue - blanceValue > 0) {
								showShortToast("余额不足");
							} else {
								final HttpWithdrawalClient client = new HttpWithdrawalClient();
								client.addAsynHcResponseListenrt(new AsynHcResponseListener() {

									public boolean onTimeout() {
										// TODO Auto-generated method stub
										ShowUtil.closeHttpDialog();
										return false;
									}

									public boolean onSuccess(
											BaseAsynHttpClient asynHttpClient) {
										// TODO Auto-generated method stub
										ShowUtil.closeHttpDialog();
										showShortToast(client.getMessage());
										return false;
									}

									public boolean onEmpty() {
										// TODO Auto-generated method stub
										ShowUtil.closeHttpDialog();
										return false;
									}
								});
								client.setPramas(new Object[] {
										SharedPreferencesUtil
												.getString("mobile"), amountStr });
								ShowUtil.openHttpDialog("提现中...");
								client.submitRequest();

							}
						}

					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).create().show();

	}

}
