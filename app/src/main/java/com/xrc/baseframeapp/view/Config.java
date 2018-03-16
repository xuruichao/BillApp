package com.xrc.baseframeapp.view;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * 配置文件
 * Created by xrc on 18/3/2.
 */

public class Config {

    private OnMeasureCompleteListener listener;

    private DrawManager drawManager;

    private int viewWidth;
    private int viewHeight;
    private int realWidth;

    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    private int horizontalPiece;
    private List<PointData> list;

    public void setOnMeasureCompleteListener(OnMeasureCompleteListener listener) {
        this.listener = listener;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
        if (listener != null) {
            listener.onMeasureComplete();
        }
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
        realWidth = viewWidth - paddingLeft - paddingRight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public int getHorizontalPiece() {
        return horizontalPiece;
    }

    public List<PointData> getDataList() {
        return list;
    }

    public int getRealWidth() {
        return realWidth;
    }

    public Config(Context context) {
        init(context);
    }

    private void init(Context context) {
        paddingLeft = dip2px(context.getApplicationContext(), 10);
        paddingRight = paddingLeft;
        paddingTop = dip2px(context.getApplicationContext(), 100);
        paddingBottom = dip2px(context.getApplicationContext(), 25);
    }

    private static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void bindDrawManager(@NonNull DrawManager drawManager) {
        this.drawManager = drawManager;
    }

    public void bindData(@NonNull List<PointData> list) {
        this.list = list;
    }

    public void build() {
        Strategy strategy = drawManager.getStrategy();
        if (strategy instanceof WeekStrategy) {
            horizontalPiece = (viewWidth - getPaddingLeft() - getPaddingRight()) / 6;
        } else if (strategy instanceof MonthStrategy) {
            horizontalPiece = (viewWidth - getPaddingLeft() - getPaddingRight()) / 29;
        }
        float maxY = 0;
        for (int i = 0; i < list.size(); i++) {
            PointData pointData = list.get(i);
            float y = Float.parseFloat(pointData.getY());
            if (y > maxY) {
                maxY = y;
            }
        }
        int totalValidHeight = viewHeight - getPaddingTop() - getPaddingBottom();
        for (int i = 0; i < list.size(); i++) {
            PointData pointData = list.get(i);
            float y = Float.parseFloat(pointData.getY());
            pointData.setRealY(totalValidHeight - y / maxY * totalValidHeight + getPaddingTop());
        }
    }

    public interface OnMeasureCompleteListener {
        void onMeasureComplete();
    }
}
