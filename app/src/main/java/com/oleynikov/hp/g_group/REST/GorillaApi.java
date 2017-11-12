package com.oleynikov.hp.g_group.REST;

import com.oleynikov.hp.g_group.model.FoodCategory;

import com.oleynikov.hp.g_group.model.FoodItem;
import com.oleynikov.hp.g_group.model.Item;
import com.oleynikov.hp.g_group.model.LoginGorilla;
import com.oleynikov.hp.g_group.model.Order;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 15.08.2017.
 */

public interface GorillaApi {

    @GET("/api/v1/category")
    Call<List<FoodCategory>> getCategory(@Query("storeId") int idRest);

    @GET("/api/v1/item")
    Call<FoodItem> getItem(@Query("storeId") int storeId, @Query("perPage") int perPage);

    @POST("/api/v1/auth/login")
    Call<LoginGorilla> login(@Query("login") String login , @Query("password") String password);

    @POST("/api/v1/auth/logout")
    Call <Order> logout(@Header("Authorization") String headers );
    @POST("/api/v1/auth/login")
    Call createOrder(@Header("Authorization") String headers );

}
