package com.pan.thirdpartymodule.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pan.thirdpartymodule.ThirdPartyModuleApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public abstract class BaseFragment<P extends BasePresenter>
        extends Fragment {

    protected P mPresenter;

    private Unbinder unbind;

    protected Context mContext;

    /**
     * 返回要加载布局id
     *
     *
     * @return 返回布局id
     */
    protected abstract int initContentView();

    /**
     * 进行初始化工作，类似适配器
     *
     *
     * @param view 当前Fragment根View
     */
    protected abstract void init(View view);

    protected abstract P bindPresenter();

    protected abstract String getClassTag();

    protected abstract void release();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d(getClassTag() + " : onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Timber.d(getClassTag() + " : onCreateView");
        View view = inflater.inflate(initContentView(), container, false);
        this.unbind = ButterKnife.bind(this, view);
        this.mPresenter = bindPresenter();
        this.mContext = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d(getClassTag() + " : onViewCreated");
        init(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d(getClassTag() + " : onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Timber.d(getClassTag() + " : onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Timber.d(getClassTag() + " : onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.d(getClassTag() + " : onDestroyView");
        unbind.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d(getClassTag() + " : onDestroy");
        release();
        ThirdPartyModuleApplication.getRefWatcher(getActivity()).watch(this);
    }
}

