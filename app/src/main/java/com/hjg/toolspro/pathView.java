package com.hjg.toolspro;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * @author houjiguo
 * @data 2018/12/12 15:27
 * @description
 */

public class pathView extends View {


    private double firstlength;
    private int i;
    private boolean isStart = true;
    private float pathLength;
    private float[] x;
    private ArrayList<float[]> list = new ArrayList();
    private Paint mPaint1;
    private PathMeasure measure;
    private float guanNum = 15f;

    public pathView(Context context) {
        super(context);
    }

    public pathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint();
        mPaint1 = new Paint();
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint1.setStrokeWidth(10);

        Path mPath = new Path();
        mPaint.setColor(Color.parseColor("#ff0000"));
        mPaint.setStyle(Paint.Style.STROKE);
        x = new float[]{0, 100};
        mPath.moveTo(0, 100);
//        mPath.lineTo(349.9f, 269.61f);
        mPath.lineTo(300, 100);
        mPath.quadTo(400, 275, 300, 400);
        mPath.quadTo(350, 450, 600, 700);
        mPath.lineTo(300, 600);
        mPath.lineTo(500, 700);
        mPath.quadTo(50, 800, 50, 900);

        canvas.drawPath(mPath, mPaint);
        measure = new PathMeasure();
        measure.setPath(mPath, false);
        pathLength = measure.getLength();
        Log.d("pathView", "pathLength:" + pathLength);
        Log.d("pathView", "pathLength/8f:" + (pathLength / guanNum));
        //102.63885
//        float pathEvery = pathLength / 8f;
//        float[] x, y;
//        measure.getPosTan(pathEvery, x, y);
        //每段的长度
        firstlength = pathLength / guanNum;

        startAnim(measure, canvas);
    }

    private void startAnim(final PathMeasure measure, Canvas canvas) {
        if (!isStart) {
            for (int j = 0; j < list.size(); j++) {
                canvas.drawCircle(list.get(j)[0], list.get(j)[1], 10, mPaint1);
            }
            return;
        }
        isStart = false;
        ValueAnimator animator = ValueAnimator.ofFloat(0, pathLength);
        animator.setDuration(5 * 1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public double num = firstlength;
            int i = 1;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float distance = (float) animation.getAnimatedValue();
                Log.d("pathView", "distance:" + distance);
                if (distance > num) {
                    i++;
                    num = firstlength * i;
                    Log.d("pathView", "i:" + i);
                    measure.getPosTan(distance, x, null);
                    list.add(x);
                    postInvalidate();
                }

            }
        });
        animator.start();
    }

}
