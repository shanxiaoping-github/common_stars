package com.platform.advertising.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import sxp.android.framework.adapter.MapAdapter;
import sxp.android.framework.adapter.MapAdapter.ItemHandleCallBack;
import sxp.android.framework.adapter.MapAdapter.ViewHolder;
import sxp.android.framework.ui.BaseActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.platform.advertising.R;
import com.platform.advertising.util.HttpUtil;
import com.platform.advertising.util.SharedPreferencesUtil;
import com.platform.advertising.view.city.XListView;
import com.platform.advertising.view.city.XListView.IXListViewListener;

public class MessageActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener {
	private XListView listView;
	private MapAdapter adapter;
	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
	private boolean isFirst = true;
	private int pageSize = 10;
	private int pageNum = 1;
	SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_message);
		setBackButton();

		adapter = new MapAdapter(getApplicationContext(), data,
				new ItemHandleCallBack() {

					public void handle(ViewHolder holder,
							Map<String, String> item, int position) {
						holder.tvs.get(0).setText(item.get("title"));
						String date = dateFormat.format(new Date(Long
								.parseLong(item.get("modifyDate"))));
						holder.tvs.get(1).setText(date);
					}
				}, R.layout.advertising_market_layout_item);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(new IXListViewListener() {

			public void onRefresh() {
				pageNum = 1;
				loadData(true);
			}

			public void onLoadMore() {
				loadData(false);
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				position--;
				final String messageId = data.get(position).get("id");
				AlertDialog.Builder builder = new Builder(MessageActivity.this);
				builder.setMessage("确定要删除该消息?");
				builder.setIcon(android.R.drawable.ic_dialog_alert);
				builder.setTitle("提示");
				builder.setPositiveButton("删除",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								send(new BaseThreadCallBack() {

									public String sendData() throws Exception {
										params.put("mobile",
												SharedPreferencesUtil
														.getString("mobile"));
										params.put("messageId",
												messageId);
										return HttpUtil.post("messageDelete",
												params);
									}

									public void handleSuccess(String result)
											throws Exception {
										closeProgressDialog();
										JSONObject jsonObject = new JSONObject(
												result);
										if (jsonObject.getBoolean("code")) {
											showShortToast("删除成功!");
											loadData(true);
										} else {
											showShortToast(jsonObject
													.getString("message"));
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
						});
				builder.setNegativeButton("取消",
						new android.content.DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
				return true;
			}
		});

		loadData(true);
	}

	private void loadData(final boolean isRefresh) {
		send(new BaseThreadCallBack() {

			public String sendData() throws Exception {
				params.put("mobile", SharedPreferencesUtil.getString("mobile"));
				params.put("pageSize", String.valueOf(pageSize));
				if (isRefresh) {
					params.put("pageNumber", "1");
				} else {
					params.put("pageNumber", String.valueOf(pageNum + 1));
				}
				return HttpUtil.post("messageList", params);
			}

			public void handleSuccess(String result) throws Exception {
				closeProgressDialog();
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("code")) {
					JSONArray array = jsonObject.getJSONArray("data");
					if (isRefresh) {
						data.clear();
					} else if (array.length() != 0) {
						pageNum++;
					}
					for (int i = 0; i < array.length(); i++) {
						Map<String, String> map = new HashMap<String, String>();
						JSONObject object = array.getJSONObject(i);
						map.put("id", object.getString("id"));
						map.put("modifyDate", object.getString("modifyDate"));
						map.put("title", object.getString("title"));
						map.put("content", object.getString("content"));
						data.add(map);
					}
//					Map<String, String> map = new HashMap<String, String>();
//					map.put("id", "1");
//					map.put("modifyDate", "1401706477000");
//					map.put("title", "今天什么时候过来");
//					map.put("content", "这边有点事情想和你商量一下");
//					data.add(map);
					adapter.notifyDataSetChanged();
				} else {
					showShortToast(jsonObject.getString("message"));
				}
				listView.stopRefresh();
				listView.stopLoadMore();
			}

			public void handleError(String errorMessage) {
				closeProgressDialog();
				showLongToast(errorMessage);
				listView.stopRefresh();
				listView.stopLoadMore();
			}

			public void handleEmpty() {
				if (isFirst) {
					isFirst = false;
					showProgressDialog("正在获取数据...");
				}
			}
		});
	}

	public void onClick(View v) {
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		position--;
		Bundle bundle = new Bundle();
		bundle.putSerializable("id", data.get(position).get("id"));
		bundle.putSerializable("title", data.get(position).get("title"));
		bundle.putSerializable("content", data.get(position).get("content"));
		openActivity(MessageDetailActivity.class, bundle);
	}
}
