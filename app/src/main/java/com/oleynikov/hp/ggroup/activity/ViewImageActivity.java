package com.oleynikov.hp.ggroup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.oleynikov.hp.ggroup.R;

public class ViewImageActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        mImageView = (ImageView) findViewById(R.id.imageViewFullScreen);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        String j = (String) b.get("URL");
        Log.d("StringInActivity", j);
        Glide.with(this).load(j).into(mImageView);
    }
}
