package com.oleynikov.hp.ggroup.REST;


import com.oleynikov.hp.ggroup.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 23.06.2017.
 */

public interface RetrofitApi {

    @GET("/wp-json/wp/v2/posts")
    Call<List<Post>> getIdPost();


    @GET("/wp-json/wp/v2/media")
    Call<List<Post>> getImage(@Query("include") String id);

    @GET("/wp-json/wp/v2/posts")
    Call<List<Post>> getIdEvent(@Query("categories") int id);

}
