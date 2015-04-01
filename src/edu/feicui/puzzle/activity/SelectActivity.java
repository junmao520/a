package edu.feicui.puzzle.activity;

import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import edu.feicui.puzzle.R;
import edu.feicui.puzzle.main.BaseActivity;

public class SelectActivity extends BaseActivity implements
		OnItemClickListener, OnItemSelectedListener {

	final static int DIALOG_SELECT_ID = 0x1;
	Gallery mGallery;
//选图一步：定义静态变量
	public static int sViewId;
	public static int sLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		initViews();
	}

	/**
	 * 功能:初始化控件，并绑定事件
	 */
	public void initViews() {
		mGallery = (Gallery) findViewById(R.id.select_gallery);
		mGallery.setAdapter(new GalleryAdapter());
		mGallery.setOnItemClickListener(this);
		mGallery.setOnItemSelectedListener(this);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_SELECT_ID:// 弹出选择游戏难度对话框
			SelectItemDialog dialog = new SelectItemDialog();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher).setTitle("游戏难度")
					.setMessage("请选择游戏难度").setNegativeButton("简单", dialog)
					.setNeutralButton("一般", dialog)
					.setPositiveButton("困难", dialog);
			return builder.create();
			// 可以进行扩充
		default:
			return super.onCreateDialog(id, args);
		}
	}

	/*
	 * long id：adapter中的getItemId方法的返回值。
	 * 也就是baseAdapter里面的getItemId返回值，会被onItemClick中的参数id所自动获取 即 ：
	 * id=parent.getAdapter().getItemId(position); int position：当前被点击view的下标
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//获取点击的当前图片，传给GameView
		sViewId=(Integer)parent.getAdapter().getItem(position);
		showDialog(DIALOG_SELECT_ID);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}

	static class ViewHolder {
		ImageView img;
	}

	class GalleryAdapter extends BaseAdapter {
		Integer[] resource = { R.drawable.bg_select0, R.drawable.bg_select1,
				R.drawable.bg_select2, R.drawable.bg_select3,
				R.drawable.bg_select4, R.drawable.bg_select5,
				R.drawable.bg_select6, R.drawable.bg_select7,
				R.drawable.bg_select8, R.drawable.bg_select9 };

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public Object getItem(int position) {
			return resource[position % resource.length];
		}

		@Override
		public long getItemId(int position) {
			return resource[position % resource.length].hashCode();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				LayoutInflater inflater = getLayoutInflater();
				convertView = inflater.inflate(R.layout.item_select,
						null);
				holder = new ViewHolder();
				holder.img = (ImageView) convertView
						.findViewById(R.id.img_gallery);
				convertView.setTag(holder);
				Gallery.LayoutParams params = new Gallery.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				convertView.setLayoutParams(params);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.img.setImageResource(resource[position % resource.length]);
			return convertView;
		}
	}

	/**
	 * 选择游戏难度
	 * 作用：跳转到GameActivity，并把图片ID传递过去
	 */
	class SelectItemDialog implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_NEGATIVE:
				sLevel=3;
				startActivity(new Intent(SelectActivity.this,GameActivity.class));
				break;
			case DialogInterface.BUTTON_NEUTRAL:
				sLevel=4;
				startActivity(new Intent(SelectActivity.this,GameActivity.class));
				break;
			case DialogInterface.BUTTON_POSITIVE:
				sLevel=5;
				startActivity(new Intent(SelectActivity.this,GameActivity.class));
				break;
			}
		}
	}
}
