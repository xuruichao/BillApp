package com.xrc.baseframeapp.view;

/**
 * 数据点对象封装
 * Created by xrc on 18/3/6.
 */

public class PointData {
    private String x;
    private String y;
    private float realY;

    public PointData(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public float getRealY() {
        return realY;
    }

    public void setRealY(float realY) {
        this.realY = realY;
    }
}
