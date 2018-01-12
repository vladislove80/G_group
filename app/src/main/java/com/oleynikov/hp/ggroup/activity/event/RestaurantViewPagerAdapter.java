package com.oleynikov.hp.ggroup.activity.event;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by Vladyslav on 11.01.2018
 */

public class RestaurantViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "RestViewPagerAdapter";
    private int[] restaurantIdList;
    private final Context context;

    public RestaurantViewPagerAdapter(Context context, FragmentManager fm, int[] restaurantIdList) {
        super(fm);
        this.context = context;
        this.restaurantIdList = restaurantIdList;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: ");
        return RestaurantEventListFragment.newInstance(restaurantIdList[position]);
    }

    @Override
    public int getCount() {
        return restaurantIdList.length;
    }
}
