package com.oleynikov.hp.g_group.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.REST.RetrofitApi;
import com.oleynikov.hp.g_group.model.Image;
import com.oleynikov.hp.g_group.model.Info;
import com.oleynikov.hp.g_group.model.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {
    private Gson gson;
    private retrofit2.Retrofit retrofit;
    private List<String> idPost;
    private ArrayList<Info> mainListInfoImage = new ArrayList<Info>();
    private List<String> sourseURLImage;
    private boolean isConnected = false;
    private BroadcastReceiver broadcastReceiverNetworkState;
    private boolean checkRegistr = false;
    public static final String LIST_INFO = "listInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        idPost = new ArrayList<>();
        sourseURLImage = new ArrayList<>();
        initializationGson();
        initializationRetrofit();


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                getPostID();

                return null;
            }

        }.execute();

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

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<List<Post>> call = retrofitApi.getIdPost();


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                for (int i = 0; i < response.body().size(); i++) {
                    idPost.add(response.body().get(i).getFeaturedMedia());

                    mainListInfoImage.add(new Info(response.body().get(i).getTitle().getRendered(), ""));


                }


                String array = idPost.toString();

                RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
                Call<List<Post>> call1 = retrofitApi.getImage(array.replace("[", "").replace("]", ""));

                call1.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call1, Response<List<Post>> response) {
                        for (int i = 0; i < response.body().size(); i++) {

                            mainListInfoImage.get(i).setSourceUrl(response.body().get(i).getSourceUrl());

                        }


                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.putExtra(LIST_INFO, mainListInfoImage);
                        startActivity(intent);


                    }

                    @Override
                    public void onFailure(Call<List<Post>> call1, Throwable t) {
                        Log.d("False", "FalseDownloadImage");
                    }
                });

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Включите интернет", Toast.LENGTH_SHORT).show();
                initializationBroadCastNetwork();

            }
        });

    }

    @Override
    protected void onPause() {

        super.onPause();
        if (checkRegistr == true) {
            unregisterReceiver(broadcastReceiverNetworkState);
            Log.d("Unregisrer", "StopBroadcast");

        }
        finish();

    }

    private void initializationBroadCastNetwork() {
        broadcastReceiverNetworkState = new BroadcastReceiver() {


            @Override
            public void onReceive(Context context, Intent intent) {

                isNetworkAvailable(context);

            }

            private boolean isNetworkAvailable(Context context) {
                ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivity != null) {
                    NetworkInfo[] info = connectivity.getAllNetworkInfo();
                    if (info != null) {
                        for (int i = 0; i < info.length; i++) {
                            if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                                if (!isConnected) {

                                    getPostID();

                                    isConnected = true;


                                }
                                return true;
                            }
                        }
                    }
                }

                isConnected = false;
                return false;
            }

        };

        IntentFilter intentNetwork = new IntentFilter();
        intentNetwork.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentNetwork.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiverNetworkState, intentNetwork);
        checkRegistr = true;

    }


}
