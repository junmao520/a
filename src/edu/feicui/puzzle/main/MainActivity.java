package edu.feicui.puzzle.main;

import android.content.Intent;
import android.os.Bundle;
import edu.feicui.puzzle.activity.OperationActivity;
import edu.feicui.puzzle.view.LogoView;

/**
 *Logo界面图片切换，切换完毕跳转到OperationActivity
  */
public class MainActivity extends BaseActivity{
	/**
	 * 展示Logo界面
	 */
	LogoView mLogoView;

	OperationActivity o;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mLogoView = new LogoView(this);
		setContentView(mLogoView);
	}


	/**
	 * 功能：跳转到OperationActivity，并结束子线程，释放资源
	 */
	public void gotoOperation() {
		startActivity(new Intent(MainActivity.this, OperationActivity.class));
		mLogoView.mFlag = false;
		finish();
	}

	/** 
	 * 因为onDestroy方法是最后一个Activity最后执行的方法，
	 * 所以先执行自己的代码功能，然后交给系统来做最后的收尾
	 * 即自己的代码在前，super.onDestroy();在后
	 */
	@Override
	protected void onDestroy() {
		// 成员变量回收，能提高性能。不写也可以，因为只是在当前Activity使用，如果是静态变量就必须设置为null来 手动回收
		// 如数据库、IO等系统资源也必须手动回收
		mLogoView = null;
		super.onDestroy();
	}
}
