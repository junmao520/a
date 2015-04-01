package edu.feicui.puzzle.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * ��Ϊ����Activity�Ļ��ࡣ����ȫ��,��ȡ��Ļ���  
 */
public class BaseActivity extends Activity {
	/**
	 * Log��־�е�tag
	 */
	protected final String TAG = this.getClass().getName();
	/**
	 * ��ȡ��Ļ��ȡ��߶�
	 */
	public int mScreenW, mScreenH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullScreen();
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		mScreenW = metrics.widthPixels;
		mScreenH = metrics.heightPixels;
	}

	/**
	 * ���ô����� ����ȫ��
	 * ������������ظ�ʹ�ô�������3��
	 */
	public void setFullScreen() {
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ��������Ϣ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
