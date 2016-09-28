package com.francis.zhihuzhuanlandemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author taoc @ Zhihu Inc.
 * @since 09-28-2016
 */

public class ShineTextView extends TextView {

	private LinearGradient mLinearGradient;
	private Matrix mGradientMatrix;
	private Paint mPaint;
	private int mViewWidth = 0;
	private int mTranslate = 0;

	public ShineTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 在 onSizeChanged() 方法中进行一些对象的初始化工作,并根据 view 的宽带设置一个 LinearGradient 渐变渲染器
	 * @param w
	 * @param h
	 * @param oldw
	 * @param oldh
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(mViewWidth == 0){
			mViewWidth = getMeasuredWidth();
			if(mViewWidth > 0){
				//获取当前绘制 TextView 的 Paint 对象,并给这个 Paint 对象设置原生 TextView
				//没有的 LinearGradient 属性。
				mPaint = getPaint();
				mLinearGradient = new LinearGradient(
						0,
						0,
						mViewWidth,
						0,
						new int[]{
								Color.BLUE, 0xffffffff,
								Color.BLUE},
						null,
						Shader.TileMode.CLAMP);
				mPaint.setShader(mLinearGradient);
				mGradientMatrix = new Matrix();
			}
		}
	}

	/**
	 * 通过矩阵的方式来不断平移渐变效果,从而在绘制文字的时候产生闪动的动态效果
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mGradientMatrix != null) {
			mTranslate += mViewWidth / 5;
			if (mTranslate > 2 * mViewWidth) {
				mTranslate = -mViewWidth;
			}
			mGradientMatrix.setTranslate(mTranslate, 0);
			mLinearGradient.setLocalMatrix(mGradientMatrix);
			postInvalidateDelayed(100);
		}
	}
}
