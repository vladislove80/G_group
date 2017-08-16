package com.oleynikov.hp.g_group.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.oleynikov.hp.g_group.R;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ACTION_SEND_REST_NAME = "rest_name" ;
    public static final String UPDATE_NAME = "update_name";

    private ImageButton mImageButtonAlMezze;
    private ImageButton mImageButtonElevenDogs;
    private ImageButton mImageButtonKinza;
    private Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        mImageButtonAlMezze = (ImageButton) findViewById(R.id.imageButtonAlMezzeСhoice);
        mImageButtonAlMezze.setOnClickListener(this);
        mImageButtonElevenDogs = (ImageButton) findViewById(R.id.imageButtonElevenСhoice);
        mImageButtonElevenDogs.setOnClickListener(this);
        mImageButtonKinza = (ImageButton) findViewById(R.id.imageButtonKinzaСhoice);
        mImageButtonKinza.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageButtonAlMezzeСhoice:
                intent = new Intent();
                intent.putExtra(UPDATE_NAME, "Al Mezze");
                intent.setAction(ACTION_SEND_REST_NAME);
                sendBroadcast(intent);
                finish();



                break;

            case R.id.imageButtonElevenСhoice:

                intent.putExtra(UPDATE_NAME, "Eleven Dogs");
                intent.setAction(ACTION_SEND_REST_NAME);
                sendBroadcast(intent);
                finish();

                break;
            case R.id.imageButtonKinzaСhoice:


                intent.putExtra(UPDATE_NAME, "Kinza");
                intent.setAction(ACTION_SEND_REST_NAME);
                sendBroadcast(intent);
                finish();

                break;

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
