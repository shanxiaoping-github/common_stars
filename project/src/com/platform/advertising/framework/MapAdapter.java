package com.platform.advertising.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 
 * @author chenliang
 * @version v1.1
 * @date 2014-8-10
 */
public class MapAdapter extends BaseAdapter {
	private ItemHandleCallBack handle = null;
	private List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	private LayoutInflater inflater;
	private Integer itemResource;
	private Context context;

	/**
	 * 初始化一个ItemAdapter，传入数据和自定义回调接口,item布局
	 * 
	 * @param context
	 * @param data
	 *            数据
	 * @param handle
	 *            Item处理回调接口
	 * @param itemResource
	 *            item布局
	 */
	public MapAdapter(Context context, List<Map<String, String>> data,
			ItemHandleCallBack handle, Integer itemResource) {
		this(context,data,handle);
		this.itemResource = itemResource;
	}

	/**
	 * 初始化一个ItemAdapter，传入数据和自定义回调接口,item布局
	 * 
	 * @param context
	 * @param data
	 *            数据
	 * @param itemResource
	 *            item布局
	 */
	public MapAdapter(Context context, List<Map<String, String>> data,
			Integer itemResource) {
		this(context, data);
		this.itemResource = itemResource;
	}

	/**
	 * 初始化一个ItemAdapter，传入数据和自定义回调接口
	 * 
	 * @param context
	 * @param data
	 *            数据
	 * @param handle
	 *            Item处理回调接口
	 */
	public MapAdapter(Context context, List<Map<String, String>> data,
			ItemHandleCallBack handle) {
		this(context, data);
		this.handle = handle;
	}

	/**
	 * 初始化一个ItemAdapter，传入数据
	 * 
	 * @param context
	 * @param data
	 *            数据
	 */
	public MapAdapter(Context context, List<Map<String, String>> data) {
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.data = data;
	}

	/**
	 * 数据发生改变时候调用
	 * 
	 * @param data
	 *            改变后的数据
	 */
	public void refresh() {
		this.notifyDataSetChanged();
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			// 没有设置Item布局时采用默认item布局
//			if (itemResource == null)
//				convertView = inflater.inflate(R.layout.base_list_item, null);
//			// 设置了Item布局则采用设置的Item布局
//			else
				convertView = inflater.inflate(itemResource, null);
			holder = new ViewHolder();
			// 加入ImageView
			ImageView imageView = null;
			int index = 1;
			do {
				imageView = (ImageView) convertView
						.findViewById(getId("imageView" + index++));
				holder.ivs.add(imageView);
			} while (imageView != null);
			// 加入TextView
			TextView textView = null;
			index = 1;
			do {
				textView = (TextView) convertView.findViewById(getId("textView"
						+ index++));
				holder.tvs.add(textView);
			} while (textView != null);

			// 加入Button
			Button button = null;
			index = 1;
			do {
				button = (Button) convertView.findViewById(getId("button"
						+ index++));
				holder.btns.add(button);
			} while (button != null);

			// 加入RadioButton
			RadioButton rb = null;
			index = 1;
			do {
				rb = (RadioButton) convertView.findViewById(getId("radioButton"
						+ index++));
				holder.rbs.add(rb);
			} while (rb != null);

			// 加入CheckBox
			CheckBox cb = null;
			index = 1;
			do {
				cb = (CheckBox) convertView.findViewById(getId("checkBox"
						+ index++));
				holder.cbs.add(cb);
			} while (cb != null);

			// 加入EditText
			EditText editText = null;
			index = 1;
			do {
				editText = (EditText) convertView.findViewById(getId("editText"
						+ index++));
				holder.edts.add(editText);
			} while (editText != null);

			// 加入GridViews
			GridView gridView = null;
			index = 1;
			do {
				gridView = (GridView) convertView.findViewById(getId("gridView"
						+ index++));
				holder.gridViews.add(gridView);
			} while (gridView != null);

			// 加入Views
			View view = null;
			index = 1;
			do {
				view = convertView.findViewById(getId("view" + index++));
				holder.views.add(view);
			} while (view != null);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Map<String, String> item = data.get(position);
		if (handle != null) {
			handle.handle(holder, item, position);
		}
		return convertView;
	}

	private int getId(String id) {
		return context.getResources().getIdentifier(id, "id",
				context.getPackageName());
	}

	public interface ItemHandleCallBack {
		public void handle(ViewHolder holder, Map<String, String> item,
				final int position);
	}

	public class ViewHolder {
		public List<View> views = new ArrayList<View>();
		public List<ImageView> ivs = new ArrayList<ImageView>();
		public List<TextView> tvs = new ArrayList<TextView>();
		public List<Button> btns = new ArrayList<Button>();
		public List<RadioButton> rbs = new ArrayList<RadioButton>();
		public List<CheckBox> cbs = new ArrayList<CheckBox>();
		public List<EditText> edts = new ArrayList<EditText>();
		public List<GridView> gridViews = new ArrayList<GridView>();
	}
}
