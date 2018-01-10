package com.oleynikov.hp.ggroup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.oleynikov.hp.ggroup.R;

public class LogoActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mImageButtonBack;
    Button mButtonViewRegistation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mImageButtonBack = (ImageButton) findViewById(R.id.imageButtonBack);
        mImageButtonBack.setOnClickListener(this);
        mButtonViewRegistation = (Button) findViewById(R.id.buttonViewReg);
        mButtonViewRegistation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch( view.getId()) {
            case R.id.imageButtonBack:

              onBackPressed();


                break;

        }
        switch( view.getId()) {
            case R.id.buttonViewReg:

                startActivity(new Intent(this, RegistrationActivity.class));


                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
