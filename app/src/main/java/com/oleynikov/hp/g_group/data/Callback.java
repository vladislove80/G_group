package com.oleynikov.hp.g_group.data;

/**
 * Created by Vladyslav on 08.01.2018
 */

public interface Callback<T> {
    void onSuccess(T data);

    void onError(Throwable throwable);
}
