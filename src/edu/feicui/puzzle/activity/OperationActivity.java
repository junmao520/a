package edu.feicui.puzzle.activity;

import java.io.IOException;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import edu.feicu.puzzle.util.LogWrapper;
import edu.feicui.puzzle.R;
import edu.feicui.puzzle.main.BaseActivity;

/**
 * ��Ϸ�Ĳ�������
 */
public class OperationActivity extends BaseActivity implements OnClickListener,
		OnCompletionListener {
	/**
	 * ���ڶԻ���ID
	 */
	final static int DIALOG_ID_ABOUT = 0x1;
	/**
	 * �˳��Ի���ID
	 */
	final static int DIALOG_ID_EXIT = 0x2;
	/**
	 * ��ť�ؼ�
	 */
	Button mBtnStart, mBtnRank, mBtnSet, mBtnHelp, mBtnAbout, mBtnExit;
	/**
	 * ������Ϸ����
	 */
	MediaPlayer mMediaPlayer;
	/**
	 * ��������ļ�
	 */
	int[] mResource = { R.raw.benpao, R.raw.nanren, R.raw.zou };
	/**
	 * ���������ļ��±�
	 */
	int mIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operation);
		initViews();
		musicPlay2();
	}

	/**
	 * ����ѭ����������
	 */
	public void musicPlay() {
		mMediaPlayer = MediaPlayer.create(this, R.raw.nanren);
		mMediaPlayer.start();
		// mMediaPlayer.seekTo(msec);//seekTo�������
		mMediaPlayer.setLooping(true);
	}

	/**
	 * ���ܣ���ʼ���ؼ�,�����¼�
	 */
	public void initViews() {
		mBtnStart = (Button) findViewById(R.id.btn_start_game);
		mBtnStart.setOnClickListener(this);
		mBtnRank = (Button) findViewById(R.id.btn_rank_game);
		mBtnRank.setOnClickListener(this);
		mBtnSet = (Button) findViewById(R.id.btn_set_game);
		mBtnSet.setOnClickListener(this);
		mBtnHelp = (Button) findViewById(R.id.btn_help_game);
		mBtnHelp.setOnClickListener(this);
		mBtnAbout = (Button) findViewById(R.id.btn_about_game);
		mBtnAbout.setOnClickListener(this);
		mBtnExit = (Button) findViewById(R.id.btn_exit_game);
		mBtnExit.setOnClickListener(this);
	}

	@Deprecated
	public void about() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setIcon(R.drawable.ic_about).setTitle("����")
				.setMessage("����Ϸ������俪��").setCancelable(false)
				.setPositiveButton("����", null);
		builder.show();
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_ID_ABOUT:// ���ڶԻ���
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_about).setTitle("����")
					.setMessage("����Ϸ������俪��").setCancelable(false)
					.setPositiveButton("����", null);
			return builder.create();
		case DIALOG_ID_EXIT:// �˳��Ի���
			ExitDialog dialog = new ExitDialog();
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setIcon(R.drawable.ic_about).setTitle("�˳���Ϸ")
					.setMessage("ȷ���˳���Ϸ��").setCancelable(false)
					.setPositiveButton("ȷ��", dialog)
					.setNegativeButton("ȡ��", null);
			return builder2.create();
		default:
			return super.onCreateDialog(id, args);
		}
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.btn_start_game:// ��ת��ѡͼ����
			startActivity(new Intent(this,SelectActivity.class));
			break;
		case R.id.btn_rank_game:
			LogWrapper.d(TAG, "rank_game");// ����������δʵ��
			break;
		case R.id.btn_set_game:// ��ת���������ý���
			startActivity(new Intent(this,MusicActivity.class));
			break;
		case R.id.btn_help_game:// ��ת����������
			startActivity(new Intent(this,HelpActivity.class));
			break;
		case R.id.btn_about_game:// ���ڶԻ���
			showDialog(DIALOG_ID_ABOUT);
			break;
		case R.id.btn_exit_game:// �˳��Ի���
			showDialog(DIALOG_ID_EXIT);
			break;
		}
	}

	/**
	 * ���ܣ��ص��¼��������˳��ж�
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDialog(DIALOG_ID_EXIT);
		}
		return true;
	}

	/**
	 * ����ѭ����������
	 */
	public void musicPlay2() {
		mMediaPlayer = MediaPlayer.create(this, mResource[mIndex]);
		mMediaPlayer.start();
		mMediaPlayer.setOnCompletionListener(this);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mp.reset();
		AssetFileDescriptor afd = getResources().openRawResourceFd(
				mResource[++mIndex % mResource.length]);
		try {
			mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),
					afd.getLength());
			mp.prepare();
			mp.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		mBtnStart = null;
		mBtnRank = null;
		mBtnSet = null;
		mBtnHelp = null;
		mBtnAbout = null;
		mBtnExit = null;
		mMediaPlayer.release();
		super.onDestroy();
	}

	/**
	 * ��Ӧ�˳��Ի����¼�
	 */
	class ExitDialog implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				finish();
				break;
			}
		}
	}
	// ��չ

}
