package com.oleynikov.hp.g_group.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.REST.GorillaApi;
import com.oleynikov.hp.g_group.model.Info;
import com.oleynikov.hp.g_group.model.Item;
import com.oleynikov.hp.g_group.model.LoginGorilla;
import com.oleynikov.hp.g_group.model.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CreateOrderActivity extends AppCompatActivity {
    private List<Item> mFoodList = new ArrayList<>();
    private GorillaApi gorillaApi;
    private Gson gson;
    private Retrofit retrofit;
    private String mLogin = "ggroup@integration.com";
    private String mPassword = "ggroup123";
    private String mToken ;
    private String mAddress;
    private String mUserName;
    private String mUserPhone;
    private int mNoCall;
    private int mNoSms;
    private String mPeopleCount;
    private String mCard = "card";
    private String mTime;
    private EditText mEditTextName, mEditTextPhone, mEditTextAddress, mEditTextCountPeople;
    private ImageButton mCreateOrder;
    private RadioButton mRadioBtnReCall, mRadioBtnSendSms, mRadioBtnRightNow, mRadioBtnOnTime;
    private boolean checkCall = false;
    private boolean checkSms = false;
    int DIALOG_TIME = 1;
    private TextView mTextViewCostOrder;

    Calendar dateAndTime = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        getFoodList();
        initializationGson();
        initializationRetrofit();
        login();
        mTextViewCostOrder = (TextView) findViewById(R.id.textViewCostOrder) ;
        mEditTextName = (EditText) findViewById(R.id.editTextName);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);
        mEditTextAddress = (EditText) findViewById(R.id.editTextAddress);
        mEditTextCountPeople = (EditText) findViewById(R.id.editTextCountPeople);
        mRadioBtnReCall = (RadioButton) findViewById(R.id.radioButtonReCall);
        mRadioBtnReCall.setOnClickListener(radioButtonClickListener);
        mRadioBtnSendSms = (RadioButton) findViewById(R.id.radioButtonSendSms);
        mRadioBtnSendSms.setOnClickListener(radioButtonClickListener);
        mRadioBtnRightNow = (RadioButton) findViewById(R.id.radioRightNow);
        mRadioBtnRightNow.setOnClickListener(radioButtonClickListener);
        mRadioBtnOnTime = (RadioButton) findViewById(R.id.radioOnTime);
        mRadioBtnOnTime.setOnClickListener(radioButtonClickListener);
        mCreateOrder = (ImageButton) findViewById(R.id.imageButtonCreateOrder);
        mCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserName = mEditTextName.getText().toString();
                mUserPhone = mEditTextPhone.getText().toString();
                mAddress = mEditTextAddress.getText().toString();
                mPeopleCount = mEditTextCountPeople.getText().toString();
                createOrder();
            }
        });
        getCostOrder();

    }

    public void getFoodList() {
        if (!mFoodList.isEmpty()) {
            mFoodList.clear();
        }

        mFoodList = (ArrayList<Item>) getIntent().getSerializableExtra("food");
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;

            switch (rb.getId()) {
                case R.id.radioButtonReCall:
                    if (!checkCall) {
                        mNoCall = 1;
                        checkCall = true;
                        Log.d("RadioReCall", String.valueOf(mNoCall));
                    } else {
                        checkCall = false;
                        rb.setChecked(false);
                        mNoCall = 0;
                        Log.d("RadioReCall", String.valueOf(mNoCall));
                    }

                    break;
                case R.id.radioButtonSendSms:
                    if (!checkSms) {
                        mNoSms = 1;
                        checkSms = true;

                    } else {
                        checkSms = false;
                        rb.setChecked(false);
                        mNoSms = 0;

                    }
                    Log.d("RadioSms", String.valueOf(mNoSms));
                    break;
                case R.id.radioRightNow:
                    mTime = String.valueOf(dateAndTime.get(Calendar.HOUR_OF_DAY))+ ":" + String.valueOf(dateAndTime.get(Calendar.MINUTE));
                    Log.d("RadioNow", mTime);

                    break;
                case R.id.radioOnTime:
                    showDialog(DIALOG_TIME);
                    Log.d("RadioOnTime", String.valueOf(mNoSms));
                    break;


                default:
                    break;
            }
        }
    };

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this,R.style.DialogTheme,  myCallBack, dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true);

            return tpd;
        }
        return super.onCreateDialog(id);
    }

    TimePickerDialog.OnTimeSetListener myCallBack = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mTime = String.valueOf(hourOfDay)+ ":" + String.valueOf(minute);
            Log.d("Time", String.valueOf(mTime));
        }
    };

    public void getCostOrder()
    {
        int sum = 0;
        for(int i =0;i <mFoodList.size();i++){
            int foo = Integer.parseInt(mFoodList.get(i).getPrice())* mFoodList.get(i).getCount();
            sum = (sum + foo);

        }
        mTextViewCostOrder.setText(String.valueOf(sum));

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
        gorillaApi = retrofit.create(GorillaApi.class);
    }
    public void createOrder()
    {
        Call<Order> call = gorillaApi.createOrder("Bearer "+mToken);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.d("OrderApply", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("OrderFailed", String.valueOf(t));
            }
        });
    }
    public void login(){

        Call<LoginGorilla> call = gorillaApi.login(mLogin,mPassword);

        call.enqueue(new Callback<LoginGorilla>() {
            @Override
            public void onResponse(Call<LoginGorilla> call, Response<LoginGorilla> response) {
                Log.d("token",response.body().getToken());
                mToken = response.body().getToken();
                Log.d("token",mToken);


            }

            @Override
            public void onFailure(Call<LoginGorilla> call, Throwable t) {

            }
        });

    }
    public void logout(){
        Call  call = gorillaApi.logout("Bearer "+mToken);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("LogoutNormal", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("LogoutFailed", String.valueOf(t));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logout();
    }
}
