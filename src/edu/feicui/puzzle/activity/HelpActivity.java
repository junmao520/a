package edu.feicui.puzzle.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import edu.feicui.puzzle.R;
import edu.feicui.puzzle.main.BaseActivity;

/**
 *游戏帮助界面
 *
 */
public class HelpActivity extends BaseActivity implements
		AdapterView.OnItemClickListener {
	/**
	 * 数据源
	 */
	List<Map<String, Object>> mLstData;
	/**
	 * ListView
	 */
	ListView mLstHelp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		setData();
		initViews();
	}

	/**
	 * 功能：初始化控件，并绑定事件
	 */
	public void initViews() {
		mLstHelp = (ListView) findViewById(R.id.help_listview);
		mLstHelp.setAdapter(new ListViewAdapter());
		mLstHelp.setOnItemClickListener(this);
//		Button btn=(Button)findViewById(R.id.btn_back);
//		btn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//				
//			}
//		});
	}

	/**
	 * 功能：设置数据源
	 */
	public void setData() {
		mLstData = new ArrayList<Map<String, Object>>();
		String content1 = "\n一、基本操作\n" + "点击欲移动格子进行移动.\n" + "点击缩略图查看原图。\n"
				+ "点击播放图标重放游戏步骤。\n" + "游戏中按手机返回键:弹出菜单中选择返回选关界面;\n\n"
				+ "二、难度设定\n" + "游戏根据简单，普通，困难三个难度将拼图分割为3*3,4*4,5*5三种样式。\n\n";
		String content2 = "\n在主菜单点击积分排名按钮可查看每个难度下过关的最短时间。\n";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "游戏操作");
		map.put("content", content1);
		map.put("img", R.drawable.ic_launcher);
		mLstData.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "游戏系统");
		map2.put("content", content2);
		map2.put("img", R.drawable.ic_launcher);
		mLstData.add(map2);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView txt = (TextView) view.findViewById(R.id.txt_content);
		// 设计内容显示的状态
		if (txt.getVisibility() == View.GONE) {
			txt.setVisibility(View.VISIBLE);
		} else {
			txt.setVisibility(View.GONE);
		}
	}

	static class ViewHolder {
		TextView txtTitle;
		TextView txtContent;
		ImageView imgIcon;
	}

	class ListViewAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return mLstData.size();
		}

		@Override
		public Object getItem(int position) {
			return mLstData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// 返回该对象的哈希码，作为该元素的唯一标识
			return mLstData.get(position).hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.item_help, null);
				holder = new ViewHolder();
				holder.txtTitle = (TextView) convertView
						.findViewById(R.id.txt_title);
				holder.txtContent = (TextView) convertView
						.findViewById(R.id.txt_content);
				holder.imgIcon = (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.txtTitle.setText((String) mLstData.get(position).get("title"));
			holder.txtContent.setText((String) mLstData.get(position).get(
					"content"));
			holder.imgIcon.setImageResource((Integer) mLstData.get(position).get(
					"img"));
			return convertView;
		}
	}
}
