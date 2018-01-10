package com.oleynikov.hp.ggroup.data.repository;

import android.support.annotation.NonNull;

import com.oleynikov.hp.ggroup.data.Callback;
import com.oleynikov.hp.ggroup.model.Info;

import java.util.List;

/**
 * Created by Vladyslav on 08.01.2018
 */

public interface Repository {

    void getInfoFromPosts(@NonNull Callback<List<Info>> callback);

    void getBalanceCard();

    void getTransactionList();
}
