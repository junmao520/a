package edu.feicui.puzzle.activity;

import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.ToggleButton;
import edu.feicui.puzzle.R;
import edu.feicui.puzzle.main.BaseActivity;

public class MusicActivity extends BaseActivity implements
		OnCheckedChangeListener, View.OnClickListener,
		SeekBar.OnSeekBarChangeListener {

	/**
	 * 音量控制
	 */
	AudioManager mAudioMgr;
	/**
	 * 声音开关
	 */
	ToggleButton mTglSwh;
	/**
	 * 按钮控件
	 */
	Button mBtnBack, mBtnMusicOff, mBtnMusicOn, mBtnMusicDown, mBtnMusicUp;
	/**
	 * 音量进度条
	 */
	SeekBar mSkbVolume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		initViews();
	}

	/**
	 * 功能：初始化控件,并绑定事件
	 */
	public void initViews() {
		mAudioMgr = (AudioManager) getSystemService(AUDIO_SERVICE);
		mTglSwh = (ToggleButton) findViewById(R.id.btn_audio_on);
		if (mAudioMgr.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
			// 注意：初始化ToggleButton的状态和背景图片、提示文字三者保持一致
			mTglSwh.setChecked(false);// 设置选择状态
			mTglSwh.setTextOff("关闭");// 设置状态文字
			mTglSwh.setBackgroundResource(R.drawable.audio_off);// 设置背景图片

		} else {
			mTglSwh.setChecked(true);
			mTglSwh.setTextOn("打开");
			mTglSwh.setBackgroundResource(R.drawable.audio_on);
		}
		mTglSwh.setOnCheckedChangeListener(this);
		mBtnBack = (Button) findViewById(R.id.btn_back_operation);
		mBtnBack.setOnClickListener(this);
		mBtnMusicOff = (Button) findViewById(R.id.btn_music_off);
		mBtnMusicOff.setOnClickListener(this);
		mBtnMusicOn = (Button) findViewById(R.id.btn_music_on);
		mBtnMusicOn.setOnClickListener(this);
		mBtnMusicDown = (Button) findViewById(R.id.btn_music_down);
		mBtnMusicDown.setOnClickListener(this);
		mBtnMusicUp = (Button) findViewById(R.id.btn_music_up);
		mBtnMusicUp.setOnClickListener(this);
		mSkbVolume = (SeekBar) findViewById(R.id.volume_seekbar);
		// 注意：设置事件在设置数据之前
		mSkbVolume.setOnSeekBarChangeListener(this);
		mSkbVolume.setMax(mAudioMgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		setSkb();
	}

	/**
	 * 在每次对音量的修改后，都需要重新设置SeekBar
	 */
	public void setSkb() {
		// 设置seekBar的初始值
		mSkbVolume.setProgress(mAudioMgr
				.getStreamVolume(AudioManager.STREAM_MUSIC));
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			mTglSwh.setBackgroundResource(R.drawable.audio_on);
			mTglSwh.setTextOn("打开");
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC,
					mAudioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
					AudioManager.FLAG_PLAY_SOUND);
			setSkb();
		} else {
			mTglSwh.setBackgroundResource(R.drawable.audio_off);
			mTglSwh.setTextOff("关闭");
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, 0,
					AudioManager.FLAG_SHOW_UI);
			setSkb();
		}
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.btn_back_operation:// 返回到Operation界面
			finish();
			break;
		case R.id.btn_music_down:// 音量减少
			mAudioMgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, 0);
			setSkb();
			break;
		case R.id.btn_music_up:// 音量增大
			mAudioMgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, 0);
			setSkb();
			break;
		case R.id.btn_music_off:// 音量关闭
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
			setSkb();
			break;
		case R.id.btn_music_on:// 音量开启
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC,
					mAudioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
			setSkb();
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (fromUser) {
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}
}
