package com.oleynikov.hp.ggroup.activity.event;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oleynikov.hp.ggroup.R;

/**
 * Created by Vladyslav on 11.01.2018
 */

public class RestaurantEventsActivity extends AppCompatActivity {
    public static final String TAG = RestaurantEventsActivity.class.getSimpleName();

    private ViewPager viewPager;
    private int[] ids = {17, 6, 7, 9};
    private String[] restaurants = {"Al Mezze", "Eleven Dogs", "Горячие перцы", "Kinza"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_events);

        final TextView tvRestaurantName = (TextView) findViewById(R.id.tvRestName);
        final ImageButton ibNextArrow = (ImageButton) findViewById(R.id.imageButtonNextRest);
        final ImageButton ibBackArrow = (ImageButton) findViewById(R.id.imageButtonBackRest);
        ibBackArrow.setVisibility(View.GONE);
        tvRestaurantName.setText(restaurants[0]);

        ibBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewPagerPosition = viewPager.getCurrentItem();
                if (viewPagerPosition != 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                }
            }
        });

        ibNextArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewPagerPosition = viewPager.getCurrentItem();
                if (viewPagerPosition != restaurants.length - 1) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new RestaurantViewPagerAdapter(this, getSupportFragmentManager(), ids));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tvRestaurantName.setText(restaurants[position]);
                arrowSelector(position, ibBackArrow, ibNextArrow);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void arrowSelector(int position, ImageButton backArrow, ImageButton nextArrow) {
        if (position == 0) {
            backArrow.setVisibility(View.GONE);
        } else {
            backArrow.setVisibility(View.VISIBLE);
        }
        if (position == restaurants.length - 1) {
            nextArrow.setVisibility(View.GONE);
        } else {
            nextArrow.setVisibility(View.VISIBLE);
        }
    }
}
