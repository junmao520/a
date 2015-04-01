package edu.feicui.puzzle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import edu.feicui.puzzle.R;
import edu.feicui.puzzle.main.MainActivity;

/**
 * Logo界面图片的绘制和切换
 */
public class LogoView extends View implements Runnable {
	/**
	 * XXX 存在app 内存泄露隐患
	 */
	MainActivity mActivity;
	/**
	 * 画图片
	 */
	Bitmap[] mBitmap;
	/**
	 * 图片索引
	 */
	int mIndex;
	/**
	 * 结束子线程标记
	 */
	public boolean mFlag;

	public LogoView(Context context) {
		this(context, null);
	}

	public LogoView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 初始化Bitmap,并启动线程
	 */
	public LogoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mActivity = (MainActivity) context;
		mFlag = true;
		mBitmap = new Bitmap[3];
		mBitmap[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_logo1);
		// createScaledBitmap进行重绘
		mBitmap[0] = Bitmap.createScaledBitmap(mBitmap[0], mActivity.mScreenW,
				mActivity.mScreenH, true);
		mBitmap[1] = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_logo2);
		mBitmap[1] = Bitmap.createScaledBitmap(mBitmap[1], mActivity.mScreenW,
				mActivity.mScreenH, true);
		mBitmap[2] = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_logo3);
		mBitmap[2] = Bitmap.createScaledBitmap(mBitmap[2], mActivity.mScreenW,
				mActivity.mScreenH, true);
		new Thread(this).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int x = (mActivity.mScreenW - mBitmap[mIndex].getWidth()) / 2;
		int y = (mActivity.mScreenH - mBitmap[mIndex].getHeight()) / 2;
		canvas.drawBitmap(mBitmap[mIndex], x, y, null);
	}

	@Override
	public void run() {
		while (mFlag) {
			// 在子线程中调用onDraw,进行屏幕刷新
			postInvalidate();
			/*
			 * 在需要刷新界面的时候，使用View中invalidate方法 invalidate()：通知系统该刷新当前界面,如果该界面能显示。
			 * 该方法在主线程中使用，在子线程中使用会崩溃
			 */
			// invalidate();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mIndex++;
			if (mIndex >= mBitmap.length) {
				mActivity.gotoOperation();
			}
		}
	}

}
