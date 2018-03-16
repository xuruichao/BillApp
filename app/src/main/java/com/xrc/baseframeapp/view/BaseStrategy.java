package com.xrc.baseframeapp.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * 基类策略
 * Created by xrc on 18/3/6.
 */

public class BaseStrategy implements Strategy {

    protected Paint mPaint;
    protected Config mConfig;
    protected Path mPath;

    public BaseStrategy(Config config) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        this.mConfig = config;
    }

    @Override
    public void draw(Canvas canvas) {
        drawAxis(canvas);
        drawInfo(canvas);
    }

    private void drawInfo(Canvas canvas) {
        
    }

    private void drawAxis(Canvas canvas) {
        canvas.drawLine(mConfig.getPaddingLeft(),
                mConfig.getViewHeight() - mConfig.getPaddingBottom(),
                mConfig.getViewWidth() - mConfig.getPaddingRight(),
                mConfig.getViewHeight() - mConfig.getPaddingBottom(),
                mPaint);
        canvas.drawLine(mConfig.getPaddingLeft(),
                mConfig.getPaddingTop(),
                mConfig.getViewWidth() - mConfig.getPaddingRight(),
                mConfig.getPaddingTop(),
                mPaint);
    }
}
