package com.oleynikov.hp.g_group.REST;

import com.oleynikov.hp.g_group.model.FoodCategory;

import com.oleynikov.hp.g_group.model.FoodItem;
import com.oleynikov.hp.g_group.model.Item;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 15.08.2017.
 */

public interface GorillaApi {

    @GET("/api/v1/category")
    Call<List<FoodCategory>> getCategory(@Query("storeId") int idRest);

    @GET("/api/v1/item")
    Call<FoodItem> getItem(@Query("storeId") int storeId, @Query("perPage") int perPage);

}
