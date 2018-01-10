package com.oleynikov.hp.g_group.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.oleynikov.hp.g_group.GgroopApplication;
import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.Utils;
import com.oleynikov.hp.g_group.activity.main.view.MainActivity;
import com.oleynikov.hp.g_group.data.Callback;
import com.oleynikov.hp.g_group.data.NoDataExeption;
import com.oleynikov.hp.g_group.model.Info;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = SplashActivity.class.getSimpleName();
    private boolean isConnected = false;
    private BroadcastReceiver broadcastReceiverNetworkState;
    private boolean isReceiverRegistered = false;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (Utils.isConnected(this)) {
            getPostID(this);
        } else {
            showAlertDialog(getString(R.string.internet_alert));
            initializationBroadCastNetwork();
        }
    }

    public void getPostID(final Context context) {
        GgroopApplication.getRepository().getInfoFromPosts(new Callback<List<Info>>() {
            @Override
            public void onSuccess(List<Info> data) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "onError: isConnected = " + isConnected);
                if (throwable instanceof NoDataExeption) {
                    showAlertDialog(getString(R.string.server_alert));
                    return;
                }
                showAlertDialog(getString(R.string.connection_alert));
                if (!isReceiverRegistered) initializationBroadCastNetwork();
            }
        });
    }

    private void initializationBroadCastNetwork() {
        Log.d(TAG, "initializationBroadCastNetwork: isReceiverRegistered = " + isReceiverRegistered);
        broadcastReceiverNetworkState = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "onReceive: ");
                isNetworkAvailable(context);
            }

            private void isNetworkAvailable(Context context) {
                if (Utils.isConnected(context)) {
                    lazyRequest();
                    return;
                }
                isConnected = false;
            }
        };

        IntentFilter intentNetwork = new IntentFilter();
        intentNetwork.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiverNetworkState, intentNetwork);
        isReceiverRegistered = true;
    }

    private void lazyRequest() {
        Log.d(TAG, "isNetworkAvailable -> isConnected " + isConnected);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isConnected) {
                    getPostID(SplashActivity.this);
                    isConnected = true;
                }
            }
        }, 3000);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        if (isReceiverRegistered) {
            unregisterReceiver(broadcastReceiverNetworkState);
            Log.d(TAG, "StopBroadcast");
        }
        finish();
    }

    private void showAlertDialog(String text) {
        if (alertDialog == null) alertDialog = new AlertDialog.Builder(this).create();
        else if (!alertDialog.isShowing()) {
            alertDialog.setTitle("Ошибка!");
            alertDialog.setMessage(text);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            isConnected = false;
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
