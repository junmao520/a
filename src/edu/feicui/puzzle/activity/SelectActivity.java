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
//ѡͼһ�������徲̬����
	public static int sViewId;
	public static int sLevel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		initViews();
	}

	/**
	 * ����:��ʼ���ؼ��������¼�
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
		case DIALOG_SELECT_ID:// ����ѡ����Ϸ�ѶȶԻ���
			SelectItemDialog dialog = new SelectItemDialog();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher).setTitle("��Ϸ�Ѷ�")
					.setMessage("��ѡ����Ϸ�Ѷ�").setNegativeButton("��", dialog)
					.setNeutralButton("һ��", dialog)
					.setPositiveButton("����", dialog);
			return builder.create();
			// ���Խ�������
		default:
			return super.onCreateDialog(id, args);
		}
	}

	/*
	 * long id��adapter�е�getItemId�����ķ���ֵ��
	 * Ҳ����baseAdapter�����getItemId����ֵ���ᱻonItemClick�еĲ���id���Զ���ȡ �� ��
	 * id=parent.getAdapter().getItemId(position); int position����ǰ�����view���±�
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//��ȡ����ĵ�ǰͼƬ������GameView
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
	 * ѡ����Ϸ�Ѷ�
	 * ���ã���ת��GameActivity������ͼƬID���ݹ�ȥ
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
