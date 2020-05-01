package com.phoenix.jiaksimi.Logic;

import android.content.Context;
import android.os.Parcelable;

import com.phoenix.jiaksimi.Util.FoodType;

import java.util.ArrayList;

public interface Logic extends Parcelable {

    //=============================================================================================
    // Model
    //=============================================================================================

    boolean addFoodItemToList(String item, FoodType type);
    ArrayList<String> getFoodList(FoodType type);
    String randomizeFood(FoodType type);

    //=============================================================================================
    // Storage
    //=============================================================================================

    void writeData(Context context, String data, String filepath);

}
