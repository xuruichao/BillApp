package com.xrc.baseframeapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xrc.baseframeapp.view.PointData;
import com.xrc.baseframeapp.view.SharkChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private List<PointData> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharkChart sc = findViewById(R.id.sc);
        mData.add(new PointData("03-12", "0"));
        mData.add(new PointData("03-13", "20"));
        mData.add(new PointData("03-14", "10"));
        mData.add(new PointData("03-15", "20"));
        mData.add(new PointData("03-16", "0"));
        mData.add(new PointData("03-17", "20"));
        mData.add(new PointData("03-18", "10"));
        sc.setData(mData);
        sc.refresh();
        sc.setOnSelectedItemChangeListener(new SharkChart.OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChange(PointData pointData) {
                Log.e("TAG", pointData.getX());
            }
        });
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
