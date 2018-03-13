package com.xrc.baseframeapp.view;

import android.graphics.Canvas;

/**
 * 绘制管理器
 * Created by xrc on 18/3/6.
 */

public class DrawManager {

    private Strategy mStrategy;

    public void setStrategy(Strategy strategy) {
        this.mStrategy = strategy;
    }

    public Strategy getStrategy() {
        return mStrategy;
    }

    public void draw(Canvas canvas) {
        mStrategy.draw(canvas);
    }
}
