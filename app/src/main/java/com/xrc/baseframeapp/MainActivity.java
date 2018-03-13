package com.xrc.baseframeapp;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xrc.baseframeapp.view.PointData;
import com.xrc.baseframeapp.view.SharkChart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    private List<PointData> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ConstraintLayout parent_layout = findViewById(R.id.parent_layout);
//        Button b1 = new Button(this);
//        b1.setText("1");
//        b1.setId(View.generateViewId());
//        Button b2 = new Button(this);
//        b2.setText("2");
//        b2.setId(View.generateViewId());
//        parent_layout.addView(b1);
//        parent_layout.addView(b2);
//        ConstraintLayout.LayoutParams p = (ConstraintLayout.LayoutParams) b2.getLayoutParams();
//        p.topToBottom = b1.getId();
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
