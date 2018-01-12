package com.oleynikov.hp.ggroup.data.repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;

import com.oleynikov.hp.ggroup.REST.RetrofitApi;
import com.oleynikov.hp.ggroup.Utils;
import com.oleynikov.hp.ggroup.data.Callback;
import com.oleynikov.hp.ggroup.data.NoDataExeption;
import com.oleynikov.hp.ggroup.data.api.RestManager;
import com.oleynikov.hp.ggroup.model.Info;
import com.oleynikov.hp.ggroup.model.Post;

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
    private SparseArray<List<String>> restaurantEventImagesURLMap = new SparseArray<>();

    public RepositoryImpl() {
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
                            getImagesUrlFromInfo(array, mainListInfoImage, callback);
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

    private void getImagesUrlFromInfo(@NonNull String array, @NonNull final List<Info> mainListInfoImage, @NonNull final Callback<List<Info>> callback) {
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
                Log.d(TAG, "getImagesUrlFromInfo -> onResponse: ");
                callback.onError(new NoDataExeption());
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.d(TAG, "getImagesUrlFromInfo -> onFailure: ");
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

    @Override
    public void getEventListByRestaurantId(final int restaurantId, @NonNull final Callback<List<String>> callback) {
        List<String> imageUrls = restaurantEventImagesURLMap.get(restaurantId);
        if (imageUrls == null || imageUrls.isEmpty()) {
            restApi.getIdEvent(restaurantId).enqueue(new retrofit2.Callback<List<Post>>() {
                @Override
                public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                    Log.d(TAG, "getEventListByRestaurantId -> onResponse: ");
                    if (response.isSuccessful()) {
                        List<Post> posts = response.body();
                        List<String> idPost = new ArrayList<>();
                        if (posts != null && !posts.isEmpty()) {
                            for (int i = 0; i < posts.size(); i++) {
                                idPost.add(posts.get(i).getFeaturedMedia());
                            }
                            String array = idPost
                                    .toString()
                                    .replace("[", "")
                                    .replace("]", "");
                            posts.clear();
                            getImagesUrlFromPost(array, restaurantId, callback);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {

                }
            });
        } else {
            callback.onSuccess(imageUrls);
        }
    }

    private void getImagesUrlFromPost(@NonNull String array, @NonNull final int restaurantId, @NonNull final Callback<List<String>> callback) {
        restApi.getImage(array).enqueue(new retrofit2.Callback<List<Post>>() {

            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                Log.d(TAG, "getImagesUrlFromPosts -> onResponse: ");
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    if (posts != null && !posts.isEmpty()) {
                        List<String> imageUrls = restaurantEventImagesURLMap.get(restaurantId);
                        if(imageUrls == null) imageUrls = new ArrayList<>();
                        for (int i = 0; i < posts.size(); i++) {
                            imageUrls.add(posts.get(i).getSourceUrl());
                        }
                        restaurantEventImagesURLMap.put(restaurantId, imageUrls);
                        callback.onSuccess(imageUrls);
                        return;
                    }
                }
                callback.onError(new NoDataExeption());
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.d(TAG, "getImagesUrlFromPosts -> onFailure: ");
                callback.onError(t);
            }
        });
    }
}
