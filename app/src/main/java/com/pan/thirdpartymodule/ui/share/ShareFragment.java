package com.pan.thirdpartymodule.ui.share;

import android.view.View;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseFragment;


import butterknife.OnClick;

/**
 * Author : Pan
 * Date : 18/07/2017
 */

public class ShareFragment extends BaseFragment<SharePresenter>
        implements ShareView{

    @Override
    protected int initContentView() {
        return R.layout.share;
    }

    @Override
    protected void init(View view) {
        mPresenter.attachView(this);
    }

    @Override
    protected SharePresenter bindPresenter() {
        return new SharePresenterImpl(getActivity());
    }

    @Override
    protected String getClassTag() {
        return ShareFragment.class.getCanonicalName();
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    @OnClick({R.id.btn_wechat_share, R.id.btn_weibo_share})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_wechat_share:
                mPresenter.startWeChatShareActivity();
                break;

            case R.id.btn_weibo_share:
                mPresenter.startWeiBoShareActivity();
                break;

            default:
                break;
        }
    }

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }
}
