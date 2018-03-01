package com.xrc.baseframeapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * MVP Presenter父类
 * Created by xrc on 18/3/1.
 */

public class BasePresenter<T extends BaseView> {

    protected T mView;
    protected Context mContext;
    private BaseHandler mHandler;

    protected void attachView(T view) {
        mView = view;
    }

    protected Handler getHandler() {
        if (mHandler == null) {
            mHandler = new BaseHandler(Looper.getMainLooper());
        }
        return mHandler;
    }

    protected void post(Runnable r) {
        postDelay(r, 0);
    }

    protected void postDelay(Runnable r, int time) {
        getHandler().postDelayed(r, time);
    }

    protected void detachView(boolean removeHandlerCallbacksAndMessages) {
        if (removeHandlerCallbacksAndMessages) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (mView != null) {
            mView = null;
        }
    }

    protected void detachView() {
        detachView(false);
    }

    private static class BaseHandler extends Handler {
        BaseHandler(Looper looper) {
            super(looper);
        }
    }
}
