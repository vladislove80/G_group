package com.oleynikov.hp.g_group.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.REST.RetrofitApi;
import com.oleynikov.hp.g_group.adapters.EventAdapter;
import com.oleynikov.hp.g_group.model.Image;
import com.oleynikov.hp.g_group.model.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerViewEvent;
    private GridLayoutManager mGridLayoutManager;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonBack;
    private TextView mTextViewRestName;
    private List listRest;
    int position = 0;
    private Gson gson;
    private List<String> idPost;
    private List<String> sourseURLImage;
    private retrofit2.Retrofit retrofit;
    private int kinza = 9;
    private int perzi = 6;
    private int elevenDogs = 7;
    private int alMezze = 17;
    private int idRest = 17;
    private String restName;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        idPost = new ArrayList();
        sourseURLImage = new ArrayList();
        mRecyclerViewEvent = (RecyclerView) findViewById(R.id.recyclerEvent);
        mGridLayoutManager = new GridLayoutManager(EventActivity.this, 2);

        mRecyclerViewEvent.setHasFixedSize(true);
        mRecyclerViewEvent.setLayoutManager(mGridLayoutManager);

        eventAdapter = new EventAdapter(EventActivity.this, sourseURLImage);
        mRecyclerViewEvent.setAdapter(eventAdapter);

        initListRest();
        initializationGson();
        initializationRetrofit();
        getPostID();


        mTextViewRestName = (TextView) findViewById(R.id.textViewNameRestInGalerry);

        mImageButtonBack = (ImageButton) findViewById(R.id.imageButtonBackRest);
        mImageButtonBack.setVisibility(View.GONE);
        mImageButtonBack.setOnClickListener(this);
        mImageButtonNext = (ImageButton) findViewById(R.id.imageButtonNextRest);
        mImageButtonNext.setOnClickListener(this);


    }

    public void initRecycler() {


        eventAdapter.update(sourseURLImage);


    }

    private void initializationRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://g-group.com.ua/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void initializationGson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public void getPostID() {
//        eventAdapter.update(sourseURLImage);
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<List<Post>> call = retrofitApi.getIdEvent(idRest);


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                idPost.clear();

                for (int i = 0; i < response.body().size(); i++) {
                    idPost.add(response.body().get(i).getFeaturedMedia());
                }


                String array = idPost.toString();
                RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
                Call<List<Post>> call1 = retrofitApi.getImage(array.replace("[", "").replace("]", ""));

                call1.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call1, Response<List<Post>> response) {
                        sourseURLImage.clear();
                        Log.d("SizeInEvent", String.valueOf(sourseURLImage.size()));

                        for (int i = 0; i < response.body().size(); i++) {

                            sourseURLImage.add(response.body().get(i).getSourceUrl());
                        }


                        initRecycler();


                    }

                    @Override
                    public void onFailure(Call<List<Post>> call1, Throwable t) {
                        Log.d("False", "FalseDownloadImage");
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Log.d("Failed", "FailedGetIdImage");

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonNextRest:
                if (position < listRest.size() - 1) {
                    position++;
                    restName = (String) listRest.get(position);
                    mTextViewRestName.setText(restName);

                    checkIdRest();
                    getPostID();


                }
                break;
            case R.id.imageButtonBackRest:
                if (position > 0) {
                    position--;
                    restName = (String) listRest.get(position);
                    mTextViewRestName.setText(restName);

                    checkIdRest();
                    getPostID();


                }
                break;

        }
    }

    public void initListRest() {
        listRest = new ArrayList();
        listRest.add("Al Mezze");
        listRest.add("Eleven Dogs");
        listRest.add("Горячие перцы");
        listRest.add("Kinza");
    }

    public void checkIdRest() {
        if (restName.equals("Al Mezze")) {
            idRest = alMezze;
            mImageButtonBack.setVisibility(View.GONE);
        }
        if (restName.equals("Eleven Dogs")) {
            idRest = elevenDogs;
            mImageButtonBack.setVisibility(View.VISIBLE);
        }
        if (restName.equals("Горячие перцы")) {
            idRest = perzi;
            mImageButtonNext.setVisibility(View.VISIBLE);
        }
        if (restName.equals("Kinza")) {
            idRest = kinza;
            mImageButtonNext.setVisibility(View.GONE);
        }
    }
}
