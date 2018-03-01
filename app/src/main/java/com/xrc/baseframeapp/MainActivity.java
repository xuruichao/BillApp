package com.xrc.baseframeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showLoading() {
        Log.e("TAG", "正在加载中");
    }

    @Override
    public void success() {
        Log.e("TAG", "登录成功");
    }

    @Override
    public void failed() {
        Log.e("TAG", "登录失败");
    }

    public void click(View view) {
        mPresenter.login("xrc", "123456");
    }
}
