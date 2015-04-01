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
 * 游戏的操作界面
 */
public class OperationActivity extends BaseActivity implements OnClickListener,
		OnCompletionListener {
	/**
	 * 关于对话框ID
	 */
	final static int DIALOG_ID_ABOUT = 0x1;
	/**
	 * 退出对话框ID
	 */
	final static int DIALOG_ID_EXIT = 0x2;
	/**
	 * 按钮控件
	 */
	Button mBtnStart, mBtnRank, mBtnSet, mBtnHelp, mBtnAbout, mBtnExit;
	/**
	 * 播放游戏音乐
	 */
	MediaPlayer mMediaPlayer;
	/**
	 * 存放音乐文件
	 */
	int[] mResource = { R.raw.benpao, R.raw.nanren, R.raw.zou };
	/**
	 * 控制音乐文件下标
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
	 * 单曲循环播放音乐
	 */
	public void musicPlay() {
		mMediaPlayer = MediaPlayer.create(this, R.raw.nanren);
		mMediaPlayer.start();
		// mMediaPlayer.seekTo(msec);//seekTo快进快退
		mMediaPlayer.setLooping(true);
	}

	/**
	 * 功能：初始化控件,并绑定事件
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
		builder.setIcon(R.drawable.ic_about).setTitle("关于")
				.setMessage("本游戏属于翡翠开发").setCancelable(false)
				.setPositiveButton("返回", null);
		builder.show();
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case DIALOG_ID_ABOUT:// 关于对话框
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_about).setTitle("关于")
					.setMessage("本游戏属于翡翠开发").setCancelable(false)
					.setPositiveButton("返回", null);
			return builder.create();
		case DIALOG_ID_EXIT:// 退出对话框
			ExitDialog dialog = new ExitDialog();
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setIcon(R.drawable.ic_about).setTitle("退出游戏")
					.setMessage("确认退出游戏？").setCancelable(false)
					.setPositiveButton("确认", dialog)
					.setNegativeButton("取消", null);
			return builder2.create();
		default:
			return super.onCreateDialog(id, args);
		}
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.btn_start_game:// 跳转到选图界面
			startActivity(new Intent(this,SelectActivity.class));
			break;
		case R.id.btn_rank_game:
			LogWrapper.d(TAG, "rank_game");// 积分排名，未实现
			break;
		case R.id.btn_set_game:// 跳转到声音设置界面
			startActivity(new Intent(this,MusicActivity.class));
			break;
		case R.id.btn_help_game:// 跳转到帮助界面
			startActivity(new Intent(this,HelpActivity.class));
			break;
		case R.id.btn_about_game:// 关于对话框
			showDialog(DIALOG_ID_ABOUT);
			break;
		case R.id.btn_exit_game:// 退出对话框
			showDialog(DIALOG_ID_EXIT);
			break;
		}
	}

	/**
	 * 功能：回调事件，二次退出判断
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDialog(DIALOG_ID_EXIT);
		}
		return true;
	}

	/**
	 * 连续循环播放音乐
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
	 * 响应退出对话框事件
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
	// 扩展

}
