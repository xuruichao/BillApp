package com.xrc.baseframeapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿鲨鱼记账统计图
 * Created by xrc on 18/3/2.
 */

public class SharkChart extends View {

    private Config mConfig;
    private DrawManager mDrawManager;
    private List<PointData> mData = new ArrayList<>();

    public SharkChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        mData.add(new PointData("03-12", "0"));
        mData.add(new PointData("03-13", "20"));
        mData.add(new PointData("03-14", "10"));
        mData.add(new PointData("03-15", "20"));
        mData.add(new PointData("03-16", "0"));
        mData.add(new PointData("03-17", "20"));
        mData.add(new PointData("03-18", "0"));
    }

    public void setData(@NonNull List<PointData> data) {
        this.mData = data;
    }

    private void init(Context context) {
        mConfig = new Config(context);
        mDrawManager = new DrawManager();
        mDrawManager.setStrategy(new WeekStrategy(mConfig));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();
        mConfig.setViewWidth(viewWidth);
        mConfig.setViewHeight(viewHeight);
        mConfig.bindDrawManager(mDrawManager).bindData(mData).build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawManager.draw(canvas);
    }
}
