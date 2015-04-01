package edu.feicui.puzzle.view;

import edu.feicui.puzzle.R;
import edu.feicui.puzzle.activity.SelectActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//��Ϸ����Ĳ���
public class GameView extends SurfaceView implements Callback, Runnable {
	// ����surfaceView����Ƕ��surface����������
	SurfaceHolder holder;
	Paint paint;
	boolean flag;
	int[] colors = { Color.GREEN, Color.RED, Color.BLUE };
	int index;
	long statTime;
	Bitmap mBig, mSmall, mClock, mPlay;

	public GameView(Context context) {
		this(context, null);
	}

	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews();
	}

	public void initViews() {
		holder = getHolder();
		holder.addCallback(this);
		// �ص����ѷ����Ķ���ͷ�����ʵ�ַֿ�
		paint = new Paint();

//		mBig = BitmapFactory.decodeResource(getResources(),
//				R.drawable.bg_select6);
		mBig = BitmapFactory.decodeResource(getResources(),
				SelectActivity.sViewId);
		
		mSmall = Bitmap.createScaledBitmap(mBig, 100, 100, true);
		mPlay = BitmapFactory.decodeResource(getResources(),
				R.drawable.game_play);
		mClock = BitmapFactory.decodeResource(getResources(),
				R.drawable.game_clock);
	}

	public void drawViews(Canvas canvas) {
		// �����canvas
		canvas.drawColor(Color.BLACK);

		canvas.drawBitmap(mBig, 10, 110, null);
		canvas.drawBitmap(mSmall, 10, 10, null);
		canvas.drawBitmap(mPlay, 220, 60, null);
		canvas.drawBitmap(mClock, 220, 10, null);
		// ��ʱ�䣬��ȡ��Ϸ��ǰʱ��-һ����surfaceView ��ȡ��ǰʱ��=��Ϸʱ��

		getTime(canvas);
	}

	// ��ʱ��
	public void getTime(Canvas canvas) {
		long endTime = System.currentTimeMillis();
		long time = (endTime - statTime) / 1000;
		long sec = time % 60;// ��
		long min = time / 60 % 60;// ��
		long hour = time / 60 / 60;// ʱ
		String text = (hour < 10 ? "0" + hour : hour) + ":"
				+ (min < 10 ? "0" + min : min) + ":"
				+ (sec < 10 ? "0" + sec : sec);
		paint.reset();
		paint.setTextSize(80);
		paint.setColor(Color.YELLOW);
		FontMetrics fm=paint.getFontMetrics();
		//fm.ascent  10 + �����¶����� =baseline
		canvas.drawText(text, 270, 10-fm.ascent, paint);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("test", "surfaceCreated");
		flag = true;
		statTime = System.currentTimeMillis();
		new Thread(this).start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.d("test", "surfaceChanged");

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		flag = false;
		Log.d("test", "surfaceDestroyed");
	}

	@Override
	public void run() {
		while (flag) {
			Canvas canvas = null;
			// ����canvas
			try {
				canvas = holder.lockCanvas();
				drawViews(canvas);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// ������ͷţ���һ�λ滭���ò���canvas
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);

					/*
					 * canvas ��һ�εĻ滭������Ȼ����
					 * ˫������ƣ�1��canvas--������--һ���Ե�չʾ����2���滭��ʱ����һ������ �ڰ�--ԲȦ��ֱ��
					 */
				}

			}

		}

	}

}
