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
 *��Ϸ��������
 *
 */
public class HelpActivity extends BaseActivity implements
		AdapterView.OnItemClickListener {
	/**
	 * ����Դ
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
	 * ���ܣ���ʼ���ؼ��������¼�
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
	 * ���ܣ���������Դ
	 */
	public void setData() {
		mLstData = new ArrayList<Map<String, Object>>();
		String content1 = "\nһ����������\n" + "������ƶ����ӽ����ƶ�.\n" + "�������ͼ�鿴ԭͼ��\n"
				+ "�������ͼ���ط���Ϸ���衣\n" + "��Ϸ�а��ֻ����ؼ�:�����˵���ѡ�񷵻�ѡ�ؽ���;\n\n"
				+ "�����Ѷ��趨\n" + "��Ϸ���ݼ򵥣���ͨ�����������ѶȽ�ƴͼ�ָ�Ϊ3*3,4*4,5*5������ʽ��\n\n";
		String content2 = "\n�����˵��������������ť�ɲ鿴ÿ���Ѷ��¹��ص����ʱ�䡣\n";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "��Ϸ����");
		map.put("content", content1);
		map.put("img", R.drawable.ic_launcher);
		mLstData.add(map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "��Ϸϵͳ");
		map2.put("content", content2);
		map2.put("img", R.drawable.ic_launcher);
		mLstData.add(map2);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView txt = (TextView) view.findViewById(R.id.txt_content);
		// ���������ʾ��״̬
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
			// ���ظö���Ĺ�ϣ�룬��Ϊ��Ԫ�ص�Ψһ��ʶ
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
