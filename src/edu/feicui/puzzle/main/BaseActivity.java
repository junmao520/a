package edu.feicui.puzzle.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

/**
 * 做为所有Activity的基类。设置全屏,获取屏幕宽高  
 */
public class BaseActivity extends Activity {
	/**
	 * Log日志中的tag
	 */
	protected final String TAG = this.getClass().getName();
	/**
	 * 获取屏幕宽度、高度
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
	 * 设置窗体风格： 设置全屏
	 * 如果方法代码重复使用次数超过3次
	 */
	public void setFullScreen() {
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 设置无信息栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
}
