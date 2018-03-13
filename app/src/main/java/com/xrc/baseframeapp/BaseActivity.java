package com.xrc.baseframeapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity父类
 * Created by xrc on 18/3/1.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    protected abstract int getLayoutId();

    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
