package com.xrc.baseframeapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * 仿鲨鱼记账统计图
 * Created by xrc on 18/3/2.
 */

public class SharkChart extends View implements Config.OnMeasureCompleteListener {

    private Config mConfig;
    private DrawManager mDrawManager;
    private boolean needInvalidate = false;

    public SharkChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setData(@NonNull List<PointData> data) {
        mConfig.bindData(data);
    }

    private void init(Context context) {
        mConfig = new Config(context);
        mDrawManager = new DrawManager();
        mDrawManager.setStrategy(new WeekStrategy(mConfig));
        mConfig.bindDrawManager(mDrawManager);
        mConfig.setOnMeasureCompleteListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();
        mConfig.setViewWidth(viewWidth);
        mConfig.setViewHeight(viewHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawManager.draw(canvas);
    }

    public void refresh() {
        needInvalidate = true;
        mConfig.build();
        invalidate();
    }

    @Override
    public void onMeasureComplete() {
        if (needInvalidate) {
            mConfig.build();
            invalidate();
        }
    }
}
