package com.oleynikov.hp.ggroup.activity.base;

import android.support.annotation.Nullable;

/**
 * Created by Vladyslav on 08.01.2018
 */

public abstract class BasePresenter<V extends View> implements Presenter<V> {

    private boolean isFirstAttach = true;

    @Nullable
    private V view;

    @Override
    public void bindView(@Nullable V view) {
        this.view = view;
        if (isFirstAttach) {
            isFirstAttach = false;
            this.onFirstBind();
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected void onFirstBind() {
    }

    @Override
    public void unbindView() {
        this.view = null;
    }

    @Nullable
    @Override
    public V getView() {
        return this.view;
    }
}
