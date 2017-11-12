package com.oleynikov.hp.g_group.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.REST.GorillaApi;
import com.oleynikov.hp.g_group.adapters.RecyclerDeliveryAdapter;
import com.oleynikov.hp.g_group.adapters.RecyclerNavDrawerDelivery;
import com.oleynikov.hp.g_group.model.FoodCategory;

import com.oleynikov.hp.g_group.model.FoodItem;

import com.oleynikov.hp.g_group.model.Item;
import com.oleynikov.hp.g_group.model.LoginGorilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.oleynikov.hp.g_group.activity.ChoiceActivity.ACTION_SEND_REST_NAME;
import static com.oleynikov.hp.g_group.activity.ChoiceActivity.UPDATE_NAME;

public class DeliveryActivity extends AppCompatActivity implements View.OnClickListener {


    private Gson gson;
    private Retrofit retrofit;
    private List<FoodCategory> mFoodCategory = new ArrayList<>();
    private List<Item> mListAllFood = new ArrayList<>();
    private Button mButtonChoiceRestoran;
    private ImageButton mImageButtonBackX;
    private ImageButton mImageButtonOpenPanel;
    private DrawerLayout mDrawerLayoutDelivery;
    private NavigationView mNavigationViewDelivery;
    private RecyclerView mRecyclerAlMezzeND;
    private RecyclerView mRecyclerElevenDogsND;
    private RecyclerView mRecyclerKinzaND;
    private List<Item> test = new ArrayList<>();
    private List<Item> mFoodListEleveneDogs = new ArrayList<>();
    private List<Item> mFoodListKinza = new ArrayList<>();
    private List<Item> mFoodListAlMezze = new ArrayList<>();
    private List<Item> mListChoiceRest = new ArrayList<>();
    private List<Item> mFoodChoiceUser = new ArrayList<>();
    private RecyclerView mMainRecycler;
    private RecyclerDeliveryAdapter mDeliveryAdapter;
    private LinearLayout mLinearAlMezzeND;
    private LinearLayout mLineareElevenND;
    private LinearLayout mLinearKinzaND;
    private BroadcastReceiver broadcastReceiverResrName;
    private TabLayout tabLayout;
    private RecyclerNavDrawerDelivery myRecyclerAdapter;
    private   GorillaApi gorillaApi;
    public static final String food = "food";

    private Button mButtonCreateOdrer;


    private int idRestCategory = 121;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        initializationGson();
        initializationRetrofit();
        gorillaApi = retrofit.create(GorillaApi.class);
        addFoodList();



        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        mListChoiceRest = mFoodListEleveneDogs;
        mNavigationViewDelivery = (NavigationView) findViewById(R.id.navigation_view_delivery);
        mButtonCreateOdrer = (Button) findViewById(R.id.buttonCreateOrder1);
        mButtonCreateOdrer.setOnClickListener(this);
        myRecyclerAdapter = new RecyclerNavDrawerDelivery(this, mFoodListAlMezze);
        mButtonChoiceRestoran = (Button) findViewById(R.id.buttonViewRestName);
        mButtonChoiceRestoran.setOnClickListener(this);
        mImageButtonBackX = (ImageButton) findViewById(R.id.imageButtonX);
        mImageButtonBackX.setOnClickListener(this);
        mImageButtonOpenPanel = (ImageButton) findViewById(R.id.imageButtonMoto);
        mImageButtonOpenPanel.setOnClickListener(this);

        mDrawerLayoutDelivery = (DrawerLayout) findViewById(R.id.drawerLayoutDelivery);
        mRecyclerAlMezzeND = (RecyclerView) findViewById(R.id.recyclerAlMezzeND);
        mRecyclerAlMezzeND.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAlMezzeND.setAdapter(myRecyclerAdapter);
        mRecyclerElevenDogsND = (RecyclerView) findViewById(R.id.recyclerElevenDogsND);
        mRecyclerElevenDogsND.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerElevenDogsND.setAdapter(new RecyclerNavDrawerDelivery(this, mFoodListEleveneDogs));
        mRecyclerKinzaND = (RecyclerView) findViewById(R.id.recyclerKinzaND);
        mRecyclerKinzaND.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerKinzaND.setAdapter(new RecyclerNavDrawerDelivery(this, mFoodListKinza));
        mLinearAlMezzeND = (LinearLayout) findViewById(R.id.linearDeliveryAlmezze);
        mLinearAlMezzeND.setVisibility(View.GONE);
        mLineareElevenND = (LinearLayout) findViewById(R.id.linearDeliveryElevenDogs);
        mLineareElevenND.setVisibility(View.GONE);
        mLinearKinzaND = (LinearLayout) findViewById(R.id.linearDeliveryKinza);
        mLinearKinzaND.setVisibility(View.GONE);


        choiceRest();


    }

    public void initMainRecycler() {
        mMainRecycler = (RecyclerView) findViewById(R.id.rv_recycler_view);
        mMainRecycler.setHasFixedSize(true);

        mDeliveryAdapter = new RecyclerDeliveryAdapter(this, test, idRestCategory);
        mMainRecycler.setAdapter(mDeliveryAdapter);
        mDeliveryAdapter.notifyDataSetChanged();

        mDeliveryAdapter.setOnItemClickListener(new RecyclerDeliveryAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                switch (view.getId()) {
                    case R.id.buttonAddInND:


                        if (!mListChoiceRest.contains(mListAllFood.get(position))) {
                            mListChoiceRest.add(mListAllFood.get(position));
                            Log.d("Add", "First");
                            updateAdapters();
                        } else {
//                            Log.d("Add", "Second");
//
//                            mListChoiceRest.get(0).setCount(infoRestItem.get(position).getCount() + 1);
//                            Log.d("Add", String.valueOf(mListChoiceRest.get(position).getCount()));
//                            updateAdapters();
                        }


                        break;

                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mMainRecycler.setLayoutManager(llm);

    }


    public void initTabLayout() {


        tabLayout.setTabMode(0);

        for (int i = 0; i < mFoodCategory.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(mFoodCategory.get(i).getTitle()));
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (tabLayout.getSelectedTabPosition() == position) {
                    test.clear();
                    for (int i = 0; i < mListAllFood.size(); i++) {
                        if (mFoodCategory.get(position).getId().equals(mListAllFood.get(i).getCategoryId())) {
                            test.add(mListAllFood.get(i));

                        }
                    }

                    mDeliveryAdapter = new RecyclerDeliveryAdapter(DeliveryActivity.this, test, idRestCategory);
                    mDeliveryAdapter.setOnItemClickListener(new RecyclerDeliveryAdapter.onRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClickListener(View view, int position) {
                            switch (view.getId()) {
                                case R.id.buttonAddInND:


                                    if (!mListChoiceRest.contains(test.get(position))) {
                                        mListChoiceRest.add(test.get(position));
                                        updateAdapters();
                                    }

                                    break;

                            }
                        }
                    });
                    mMainRecycler.setAdapter(mDeliveryAdapter);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButtonX:
                finish();

                break;
            case R.id.buttonViewRestName:

                startActivity(new Intent(this, ChoiceActivity.class));

                break;
            case R.id.imageButtonMoto:
                checkVisibleLinear();
                mDrawerLayoutDelivery.openDrawer(mNavigationViewDelivery);

                break;
            case R.id.buttonCreateOrder1:

                if(!mFoodChoiceUser.isEmpty())
                {
                    mFoodChoiceUser.clear();
                }
                mFoodChoiceUser.addAll(mFoodListAlMezze);
                mFoodChoiceUser.addAll(mFoodListEleveneDogs);
                mFoodChoiceUser.addAll(mFoodListKinza);
                Intent intent = new Intent(DeliveryActivity.this, CreateOrderActivity.class);
                intent.putExtra(food, (Serializable) mFoodChoiceUser);
                startActivity(intent);

                Log.d("ChoiceListSize", String.valueOf(mFoodChoiceUser.size()));
//                startActivity(new Intent(this, CreateOrderActivity.class));

                break;

        }

    }


    public void choiceRest() {
        broadcastReceiverResrName = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String nameRest = intent.getExtras().getString(UPDATE_NAME);
                mButtonChoiceRestoran.setText(nameRest);

                if (nameRest.equals("Al Mezze")) {
                    mListChoiceRest = mFoodListAlMezze;
                    idRestCategory = 120;

                }

                if (nameRest.equals("Eleven Dogs")) {
                    mListChoiceRest = mFoodListEleveneDogs;
                    idRestCategory = 121;
                }
                if (nameRest.equals("Kinza")) {
                    mListChoiceRest = mFoodListKinza;
                    idRestCategory = 119;
                }
                mListAllFood.clear();
                mFoodCategory.clear();
                tabLayout.removeAllTabs();


                addFoodList();


            }

        };


        IntentFilter intFilt = new IntentFilter();
        intFilt.addAction(ACTION_SEND_REST_NAME);
        registerReceiver(broadcastReceiverResrName, intFilt);


    }

    public void updateAdapters() {
        mRecyclerKinzaND.getAdapter().notifyDataSetChanged();
        mRecyclerAlMezzeND.getAdapter().notifyDataSetChanged();

        mRecyclerElevenDogsND.getAdapter().notifyDataSetChanged();
    }

    public void addFoodList() {

        Call<List<FoodCategory>> call = gorillaApi.getCategory(idRestCategory);

        call.enqueue(new Callback<List<FoodCategory>>() {
            @Override
            public void onResponse(Call<List<FoodCategory>> call, Response<List<FoodCategory>> response) {
                for (int i = 0; i < response.body().size(); i++) {
                    mFoodCategory.add(response.body().get(i));
                }


            }

            @Override
            public void onFailure(Call<List<FoodCategory>> call, Throwable t) {

            }
        });

        Call<FoodItem> callItem = gorillaApi.getItem(idRestCategory, 120);
        callItem.enqueue(new Callback<FoodItem>() {
            @Override
            public void onResponse(Call<FoodItem> callItem, Response<FoodItem> response) {


                for (int i = 0; i < response.body().getItems().size(); i++) {
                    mListAllFood.add(response.body().getItems().get(i));
                }
                for (int i = 0; i < mListAllFood.size(); i++) {
                    if (mFoodCategory.get(0).getId().equals(mListAllFood.get(i).getCategoryId())) {
                        test.add(mListAllFood.get(i));

                    }
                }
                initTabLayout();
                initMainRecycler();

                Log.d("Gorilla", String.valueOf(mListAllFood.size()));
            }

            @Override
            public void onFailure(Call<FoodItem> call, Throwable t) {
                Log.d("Gorilla", "Failed");
            }
        });


    }


    public void checkVisibleLinear() {
        if (!mFoodListAlMezze.isEmpty()) {
            mLinearAlMezzeND.setVisibility(View.VISIBLE);
        } else {
            mLinearAlMezzeND.setVisibility(View.GONE);
        }
        if (!mFoodListEleveneDogs.isEmpty()) {
            mLineareElevenND.setVisibility(View.VISIBLE);
        } else {
            mLineareElevenND.setVisibility(View.GONE);
        }

        if (!mFoodListKinza.isEmpty()) {
            mLinearKinzaND.setVisibility(View.VISIBLE);
        } else {
            mLinearKinzaND.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiverResrName);
    }

    private void initializationGson() {
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    private void initializationRetrofit() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gorilla.com.ua/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
