package com.pan.thirdpartymodule.base;

import android.support.annotation.Nullable;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public abstract class BasePresenter <V extends BaseView> {
    public abstract void attachView(@Nullable V view);

    public abstract void detachView();
}
