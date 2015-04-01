package edu.feicui.puzzle.main;

import android.content.Intent;
import android.os.Bundle;
import edu.feicui.puzzle.activity.OperationActivity;
import edu.feicui.puzzle.view.LogoView;

/**
 *Logo����ͼƬ�л����л������ת��OperationActivity
  */
public class MainActivity extends BaseActivity{
	/**
	 * չʾLogo����
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
	 * ���ܣ���ת��OperationActivity�����������̣߳��ͷ���Դ
	 */
	public void gotoOperation() {
		startActivity(new Intent(MainActivity.this, OperationActivity.class));
		mLogoView.mFlag = false;
		finish();
	}

	/** 
	 * ��ΪonDestroy���������һ��Activity���ִ�еķ�����
	 * ������ִ���Լ��Ĵ��빦�ܣ�Ȼ�󽻸�ϵͳ����������β
	 * ���Լ��Ĵ�����ǰ��super.onDestroy();�ں�
	 */
	@Override
	protected void onDestroy() {
		// ��Ա�������գ���������ܡ���дҲ���ԣ���Ϊֻ���ڵ�ǰActivityʹ�ã�����Ǿ�̬�����ͱ�������Ϊnull�� �ֶ�����
		// �����ݿ⡢IO��ϵͳ��ԴҲ�����ֶ�����
		mLogoView = null;
		super.onDestroy();
	}
}
