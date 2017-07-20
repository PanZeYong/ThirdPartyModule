package com.pan.thirdpartymodule.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.pan.thirdpartymodule.ui.login.LoginFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.container)
    FrameLayout mContainer;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mPresenter.attachView(this);
        initActionBarDrawerToggle();
        replace(R.id.container, LoginFragment.newInstance());
        setStatusBarColor();
    }

    @Override
    protected void registerListener() {
        if (null != mDrawer) {
            mDrawer.addDrawerListener(mActionBarDrawerToggle);
        }

        mNavigationView.setNavigationItemSelectedListener(listener);
    }

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void release() {
        if (null != mDrawer) {
            mDrawer.removeDrawerListener(mActionBarDrawerToggle);
        }

        if (null != listener) {
            listener = null;
        }
    }

    @Override
    public void showFragment(int containerId, Fragment fragment) {
        replace(containerId, fragment);
    }

    @Override
    public void setTitle(String title) {
        mToolBar.setTitle(title);
    }

    private void initActionBarDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar,
                R.string.app_name, R.string.app_name);
        mActionBarDrawerToggle.syncState();
    }

    private NavigationView.OnNavigationItemSelectedListener listener =
            new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mPresenter.clickMenuItem(mContext, item);
            mDrawer.closeDrawers();
            return false;
        }
    };
}
