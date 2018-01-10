package com.oleynikov.hp.ggroup.activity;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.oleynikov.hp.ggroup.R;

public class AboutUsActivity extends AppCompatActivity {
    ImageButton mImageButtonFacebookURL;
    ImageButton mImageButtonInstaURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mImageButtonFacebookURL = (ImageButton) findViewById(R.id.imageButtonFacebookURL);
        mImageButtonFacebookURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mUriFacebookURL = Uri.parse("https://www.facebook.com/ggrouprestaurant/");
                Intent intent = new Intent(Intent.ACTION_VIEW, mUriFacebookURL);
                startActivity(intent);
            }
        });

        mImageButtonInstaURL =(ImageButton) findViewById(R.id.imageButtonInstaURL);
        mImageButtonInstaURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mUriInstaURL = Uri.parse("https://www.instagram.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, mUriInstaURL);
                startActivity(intent);


            }
        });
    }
}
