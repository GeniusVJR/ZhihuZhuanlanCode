package com.francis.zhihuzhuanlandemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author taoc @ Zhihu Inc.
 * @since 09-28-2016
 */

public class MyTextView extends TextView {

	private Paint mPaint1, mPaint2;

	public MyTextView(Context context) {
		super(context);
		initView();
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	//初始化画笔等
	private void initView(){
		mPaint1 = new Paint();
		mPaint1.setColor(getResources().getColor(
				android.R.color.holo_blue_light));
		mPaint1.setStyle(Paint.Style.FILL);
		mPaint2 = new Paint();
		mPaint2.setColor(Color.YELLOW);
		mPaint2.setStyle(Paint.Style.FILL);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//绘制外层矩形
		canvas.drawRect(
				0,
				0,
				getMeasuredWidth(),
				getMeasuredHeight(),
				mPaint1
		);
		//绘制内层矩形
		canvas.drawRect(
				0,
				0,
				getMeasuredWidth() - 10,
				getMeasuredHeight() - 10,
				mPaint2
		);
		canvas.save();
		//绘制文字前平移 10 像素
		canvas.translate(10, 0);
		// 在回调父类方法前,实现自己的逻辑,对 TextView 来说既是在绘制文本内容前
		super.onDraw(canvas);
		// 在回调父类方法后,实现自己的逻辑,对 TextView 来说既是在绘制文本内容后
		canvas.restore();
	}
}
