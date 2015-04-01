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
 * Logo����ͼƬ�Ļ��ƺ��л�
 */
public class LogoView extends View implements Runnable {
	/**
	 * XXX ����app �ڴ�й¶����
	 */
	MainActivity mActivity;
	/**
	 * ��ͼƬ
	 */
	Bitmap[] mBitmap;
	/**
	 * ͼƬ����
	 */
	int mIndex;
	/**
	 * �������̱߳��
	 */
	public boolean mFlag;

	public LogoView(Context context) {
		this(context, null);
	}

	public LogoView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * ��ʼ��Bitmap,�������߳�
	 */
	public LogoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mActivity = (MainActivity) context;
		mFlag = true;
		mBitmap = new Bitmap[3];
		mBitmap[0] = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_logo1);
		// createScaledBitmap�����ػ�
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
			// �����߳��е���onDraw,������Ļˢ��
			postInvalidate();
			/*
			 * ����Ҫˢ�½����ʱ��ʹ��View��invalidate���� invalidate()��֪ͨϵͳ��ˢ�µ�ǰ����,����ý�������ʾ��
			 * �÷��������߳���ʹ�ã������߳���ʹ�û����
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
