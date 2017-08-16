package com.oleynikov.hp.g_group.other;

import java.io.Serializable;

/**
 * Created by HP on 6/25/2017.
 */

public class FoodList implements Serializable {
    public String mFoodName;
    public int mFoodCount;
    public String mFoodCost;

    public FoodList(String mFoodName, int mFoodCount, String mFoodCost) {
        this.mFoodName = mFoodName;
        this.mFoodCount = mFoodCount;
        this.mFoodCost = mFoodCost;
    }
}
