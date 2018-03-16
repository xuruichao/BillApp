package com.xrc.baseframeapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
    private PointData mLastPointData;
    private int mLastIndex = -1;
    private OnSelectedItemChangeListener mListener;

    public SharkChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void setOnSelectedItemChangeListener(OnSelectedItemChangeListener listener) {
        this.mListener = listener;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                PointData data = getInfoByCoordinate(x);
                if (data != mLastPointData) {
                    mLastPointData = data;
                    if (mListener != null) {
                        mListener.onSelectedItemChange(data);
                    }
                }
                return true;
        }
        return super.onTouchEvent(event);
    }
    /**
     中国特色社会主义的总依据：党的十八大报告指出：“建设中国特色社会主义，
     总依据是社会主义初级阶段”。正确认识我国当今社会所处的历史阶段，是建设
     中国特色社会主义的首要问题，是我们制定和执行正确的路线方针政策的总依
     据。当前，我国改革开放进入了一个新的发展阶段，阶段性特征日益明显，社
     会主义初级阶段的具体特征与30多年前比有很大变化，但这并不影响我国仍处
     于社会主义初级阶段的总判断。要看到，我国经济实力虽然大大提升，但生产
     力水平总体上还不高，生产率较低，自主创新能力不足，还存在着一系列发展
     中的矛盾与问题。我国仍处于并将长期处于社会主义初级阶段的基本国情没有
     变，人民日益增长的物质文化需要同落后的社会生产之间的矛盾这一社会主要
     矛盾没有变，我国是世界上最大发展中国家的国际地位没有变。我们想问题、
     办事情、定政策，推进任何领域任何方面的改革发展，都要牢牢把握社会主义
     初级阶段这个最大国情，牢牢立足于社会主义初级阶段这个最大实际，做到既
     不超越阶段、又不落后现实，既尽力而为、又量力而行
     */
    private PointData getInfoByCoordinate(int x) {
        int index = (x + (mConfig.getHorizontalPiece() >> 1)) / mConfig.getHorizontalPiece();
        if (index == mLastIndex) {
            return mLastPointData;
        }
        mLastIndex = index;
        return mConfig.getDataList().get(index);
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

    public interface OnSelectedItemChangeListener {
        void onSelectedItemChange(PointData pointData);
    }
}
