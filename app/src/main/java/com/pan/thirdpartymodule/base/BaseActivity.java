package com.pan.thirdpartymodule.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.util.StatusBarUtils;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity{

    protected P mPresenter;

    protected Context mContext;

    private FragmentTransaction mTransaction;

    /**
     * 返回要加载布局id
     *
     * @return 返回布局id
     */
    protected abstract int initContentView();

    /**
     * 进行初始化工作
     */
    protected abstract void init();

    /**
     * 注册监听器
     */
    protected abstract void registerListener();

    /**
     * 绑定Presenter
     */
    protected abstract P bindPresenter();

    protected abstract void release();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        Timber.d("onCreate");
        ButterKnife.bind(this);

        this.mContext = this;
        this.mPresenter = bindPresenter();

        setStatusBarColor();
        init();
        registerListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Timber.d("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        Timber.d("onDestroy");
    }

    protected void replace(@IdRes int containerViewId, Fragment fragment) {
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(containerViewId, fragment);
        mTransaction.commit();
    }

    protected void hide(Fragment fragment) {
        if (null != fragment) {
            mTransaction.hide(fragment);
        }
    }

    protected void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtils.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer),
                    getColor(R.color.blue), 0);
        } else {
            StatusBarUtils.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.drawer),
                    getResources().getColor(R.color.blue), 0);
        }
    }
}

