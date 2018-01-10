package com.oleynikov.hp.g_group.activity.base;

/**
 * Created by Vladyslav on 08.01.2018
 */

public interface Presenter<V extends View> {

    void bindView(V view);

    void unbindView();

    V getView();
}
