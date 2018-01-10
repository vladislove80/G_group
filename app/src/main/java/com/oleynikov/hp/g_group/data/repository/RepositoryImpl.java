package com.oleynikov.hp.g_group.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.oleynikov.hp.g_group.REST.RetrofitApi;
import com.oleynikov.hp.g_group.Utils;
import com.oleynikov.hp.g_group.data.Callback;
import com.oleynikov.hp.g_group.data.NoDataExeption;
import com.oleynikov.hp.g_group.data.api.RestManager;
import com.oleynikov.hp.g_group.model.Info;
import com.oleynikov.hp.g_group.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Vladyslav on 08.01.2018
 */

public class RepositoryImpl implements Repository {
    public static final String TAG = RepositoryImpl.class.getSimpleName();

    private final RetrofitApi restApi;
    private List<Info> mainListInfoImage = new ArrayList<>();

    public RepositoryImpl(@NonNull Context context) {
        restApi = new RestManager().provideRetrofitApi(Utils.BASE_URL);
    }

    @Override
    public void getInfoFromPosts(@NonNull final Callback<List<Info>> callback) {
        if (mainListInfoImage.isEmpty()) {
            restApi.getIdPost().enqueue(new retrofit2.Callback<List<Post>>() {

                @Override
                public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                    if (response.isSuccessful()) {
                        List<Post> posts = response.body();
                        List<String> idPost = new ArrayList<>();

                        if (posts != null && !posts.isEmpty()) {
                            for (int i = 0; i < posts.size(); i++) {
                                idPost.add(posts.get(i).getFeaturedMedia());
                                mainListInfoImage.add(new Info(posts.get(i).getTitle().getRendered(), ""));
                            }
                            String array = idPost
                                    .toString()
                                    .replace("[", "")
                                    .replace("]", "");
                            posts.clear();
                            getImagesUrl(array, mainListInfoImage, callback);
                            return;
                        }
                    }
                    Log.d(TAG, "onResponse -> getInfoFromPosts -> onResponse: ");
                    callback.onError(new NoDataExeption());
                }

                @Override
                public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                    Log.d(TAG, "getInfoFromPosts -> onFailure: ");
                    callback.onError(t);
                }
            });
        } else {
            callback.onSuccess(mainListInfoImage);
        }
    }

    private void getImagesUrl(@NonNull String array, @NonNull final List<Info> mainListInfoImage, @NonNull final Callback<List<Info>> callback) {
        restApi.getImage(array).enqueue(new retrofit2.Callback<List<Post>>() {

            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    if (posts != null && !posts.isEmpty()) {
                        for (int i = 0; i < posts.size(); i++) {
                            mainListInfoImage.get(i).setSourceUrl(posts.get(i).getSourceUrl());
                        }
                        callback.onSuccess(mainListInfoImage);
                        return;
                    }
                }
                Log.d(TAG, "getImagesUrl -> onResponse: ");
                callback.onError(new NoDataExeption());
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.d(TAG, "getImagesUrl -> onFailure: ");
                callback.onError(t);
            }
        });
    }

    @Override
    public void getBalanceCard() {

    }

    @Override
    public void getTransactionList() {

    }
}
