package com.xrc.baseframeapp;

/**
 * Created by xrc on 18/3/1.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public void login(final String username, final String pwd) {
        mView.showLoading();
        postDelay(new Runnable() {
            @Override
            public void run() {
                if (username.equals("xrc") && pwd.equals("123456")) {
                    mView.success();
                } else {
                    mView.failed();
                }

            }
        }, 2000);
    }
}
