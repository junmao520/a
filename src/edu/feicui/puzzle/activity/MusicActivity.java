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
	 * ��������
	 */
	AudioManager mAudioMgr;
	/**
	 * ��������
	 */
	ToggleButton mTglSwh;
	/**
	 * ��ť�ؼ�
	 */
	Button mBtnBack, mBtnMusicOff, mBtnMusicOn, mBtnMusicDown, mBtnMusicUp;
	/**
	 * ����������
	 */
	SeekBar mSkbVolume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		initViews();
	}

	/**
	 * ���ܣ���ʼ���ؼ�,�����¼�
	 */
	public void initViews() {
		mAudioMgr = (AudioManager) getSystemService(AUDIO_SERVICE);
		mTglSwh = (ToggleButton) findViewById(R.id.btn_audio_on);
		if (mAudioMgr.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
			// ע�⣺��ʼ��ToggleButton��״̬�ͱ���ͼƬ����ʾ�������߱���һ��
			mTglSwh.setChecked(false);// ����ѡ��״̬
			mTglSwh.setTextOff("�ر�");// ����״̬����
			mTglSwh.setBackgroundResource(R.drawable.audio_off);// ���ñ���ͼƬ

		} else {
			mTglSwh.setChecked(true);
			mTglSwh.setTextOn("��");
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
		// ע�⣺�����¼�����������֮ǰ
		mSkbVolume.setOnSeekBarChangeListener(this);
		mSkbVolume.setMax(mAudioMgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		setSkb();
	}

	/**
	 * ��ÿ�ζ��������޸ĺ󣬶���Ҫ��������SeekBar
	 */
	public void setSkb() {
		// ����seekBar�ĳ�ʼֵ
		mSkbVolume.setProgress(mAudioMgr
				.getStreamVolume(AudioManager.STREAM_MUSIC));
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			mTglSwh.setBackgroundResource(R.drawable.audio_on);
			mTglSwh.setTextOn("��");
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC,
					mAudioMgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
					AudioManager.FLAG_PLAY_SOUND);
			setSkb();
		} else {
			mTglSwh.setBackgroundResource(R.drawable.audio_off);
			mTglSwh.setTextOff("�ر�");
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, 0,
					AudioManager.FLAG_SHOW_UI);
			setSkb();
		}
	}

	@Override
	public void onClick(View v) {
		int key = v.getId();
		switch (key) {
		case R.id.btn_back_operation:// ���ص�Operation����
			finish();
			break;
		case R.id.btn_music_down:// ��������
			mAudioMgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_LOWER, 0);
			setSkb();
			break;
		case R.id.btn_music_up:// ��������
			mAudioMgr.adjustStreamVolume(AudioManager.STREAM_MUSIC,
					AudioManager.ADJUST_RAISE, 0);
			setSkb();
			break;
		case R.id.btn_music_off:// �����ر�
			mAudioMgr.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
			setSkb();
			break;
		case R.id.btn_music_on:// ��������
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
