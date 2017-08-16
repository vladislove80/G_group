package com.oleynikov.hp.g_group.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oleynikov.hp.g_group.R;
import com.oleynikov.hp.g_group.model.Restaurant;
import com.shamanland.facebook.likebutton.FacebookLikeButton;

import java.util.ArrayList;

import static com.oleynikov.hp.g_group.activity.MainActivity.rest;

public class ShareActivity extends AppCompatActivity {

    private FacebookLikeButton mFacebookLikeButton;
    private TextView mTextViewRestName;
    private TextView mTextViewAbout;
    private TextView mTextViewAdress;
    private TextView mTextViewURL;
    private LinearLayout mLinearLayoutMain;
    private ArrayList<Restaurant> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Intent i = getIntent();
         list = (ArrayList<Restaurant>) i
                .getSerializableExtra(rest);

        mFacebookLikeButton = (FacebookLikeButton) findViewById(R.id.facebookLikeButton) ;
        mFacebookLikeButton.setPageUrl(list.get(0).getFacebookURL());
        mLinearLayoutMain = (LinearLayout) findViewById(R.id.layoutMainRest);
        mTextViewRestName = (TextView) findViewById(R.id.textViewRememberEvent);
        mTextViewRestName.setText(list.get(0).getName());
        mTextViewAdress = (TextView) findViewById(R.id.textViewAdressRest);
        mTextViewAdress.setText(list.get(0).getAdress());
        mTextViewAbout = (TextView) findViewById(R.id.textViewAbout);
        mTextViewAbout.setText(list.get(0).getAbout());
        mTextViewURL = (TextView) findViewById(R.id.textViewUrlRest);
        mTextViewURL.setPaintFlags(mTextViewURL.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        mTextViewURL.setText(list.get(0).getName());
        mTextViewURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri mURL = Uri.parse(list.get(0).getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, mURL);
                startActivity(intent);
            }
        });

        if(list.get(0).getName().equals(getString(R.string.AlMezze)))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.all_mezze_back);
        }
        if(list.get(0).getName().equals("Eleven Dogs"))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.eleven_dogs_back);
        }
        if(list.get(0).getName().equals("<<Горячие перцы>> на Королева"))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.prci_korolrva_back);
        }
        if(list.get(0).getName().equals("«Горячие Перцы» на Маршала Говорова"))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.perci_govorova_back);
        }
        if(list.get(0).getName().equals("Горячие Перцы | Черноморск"))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.prtci_chernomorsk_back);
        }
        if(list.get(0).getName().equals("Kinza"))
        {
            mLinearLayoutMain.setBackgroundResource(R.drawable.kinza_back);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        list.clear();
    }
}
