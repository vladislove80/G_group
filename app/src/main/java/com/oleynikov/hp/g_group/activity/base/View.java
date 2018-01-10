package com.oleynikov.hp.g_group.activity.base;

/**
 * Created by Vladyslav on 08.01.2018
 */

public interface View<P extends Presenter> {

    void bindPresenter(P presenter);

    P getMainActivityPresenter();
}
