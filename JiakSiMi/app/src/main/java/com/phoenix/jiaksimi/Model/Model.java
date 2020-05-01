package com.phoenix.jiaksimi.Model;

import android.os.Parcelable;

import com.phoenix.jiaksimi.Util.FoodType;

import java.util.ArrayList;

public interface Model extends Parcelable {

    boolean addFood(String food, FoodType type);
    ArrayList<String> getList(FoodType type);

}
