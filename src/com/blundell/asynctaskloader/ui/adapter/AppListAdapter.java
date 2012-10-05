package com.blundell.asynctaskloader.ui.adapter;

import java.util.List;

import com.blundell.asynctaskloader.R;
import com.blundell.asynctaskloader.domain.AppEntry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AppListAdapter extends BaseAdapter {

	private final LayoutInflater inflater;
	private List<AppEntry> data;

	public AppListAdapter(Context context) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<AppEntry> data){
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		if(convertView == null){
			view = inflater.inflate(R.layout.list_item_icon_text, parent, false);
		} else {
			view = convertView;
		}

		AppEntry item = data.get(position);
		((ImageView) view.findViewById(R.id.icon)).setImageDrawable(item.getIcon());
		((TextView) view.findViewById(R.id.text)).setText(item.getLabel());

		return view;
	}

	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

	@Override
	public Object getItem(int position) {
		return data == null ? null : data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}