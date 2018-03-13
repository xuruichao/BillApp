package com.xrc.baseframeapp.view;

import android.graphics.Canvas;

import java.util.List;

/**
 * 按周绘制
 * Created by xrc on 18/3/6.
 */

public class WeekStrategy extends BaseStrategy {

    public WeekStrategy(Config config) {
        super(config);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawLines(canvas);
    }

    private void drawLines(Canvas canvas) {
        List<PointData> dataList = mConfig.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            PointData data = dataList.get(i);
            if (i != 0) {
                mPath.lineTo(mConfig.getPaddingLeft() + mConfig.getHorizontalPiece() * i, data.getRealY());
            } else {
                mPath.moveTo(mConfig.getPaddingLeft(), data.getRealY());
            }
        }
        canvas.drawPath(mPath, mPaint);
    }
}
