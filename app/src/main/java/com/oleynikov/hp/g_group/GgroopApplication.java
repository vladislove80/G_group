package com.oleynikov.hp.g_group;

import android.app.Application;

import com.oleynikov.hp.g_group.data.repository.Repository;
import com.oleynikov.hp.g_group.data.repository.RepositoryImpl;

/**
 * Created by Vladyslav on 08.01.2018
 */

public class GgroopApplication extends Application {

    private static Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new RepositoryImpl(this);
    }

    public static Repository getRepository() {
        return repository;
    }
}
