package com.phoenix.jiaksimi.Logic;

import android.content.Context;
import android.os.Parcel;

import com.phoenix.jiaksimi.Model.Model;
import com.phoenix.jiaksimi.Storage.StorageManager;
import com.phoenix.jiaksimi.Util.FoodType;

import java.util.ArrayList;
import java.util.Random;

public class LogicManager implements Logic {

    private Model model;
    private StorageManager storage;
    private Random randomizer;

    public LogicManager(Model model, StorageManager storage) {
        this.model = model;
        this.storage = storage;
        this.randomizer = new Random();
    }

    private LogicManager(Parcel in) {
        model = in.readParcelable(Model.class.getClassLoader());
        storage = in.readParcelable(StorageManager.class.getClassLoader());
        randomizer = new Random();
    }

    //=============================================================================================
    // Model
    //=============================================================================================

    @Override
    public boolean addFoodItemToList(String item, FoodType type) {

        return model.addFood(item, type);

    }

    @Override
    public ArrayList<String> getFoodList(FoodType type) {

        return model.getList(type);

    }

    @Override
    public String randomizeFood(FoodType type) {

        ArrayList<String> list = model.getList(type);

        if (list.size() == 0) {
            return "";
        }

        return list.get(randomizer.nextInt(list.size()));

    }

    //=============================================================================================
    // Storage
    //=============================================================================================

    @Override
    public void writeData(Context context, String data, String filepath) {

        storage.writeData(context, data, filepath);

    }

    //=============================================================================================

    public static final Creator<LogicManager> CREATOR = new Creator<LogicManager>() {

        @Override
        public LogicManager createFromParcel(Parcel in) {

            return new LogicManager(in);

        }

        @Override
        public LogicManager[] newArray(int size) {

            return new LogicManager[size];

        }
    };

    @Override
    public int describeContents() {

        return 0;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(model, flags);
        dest.writeParcelable(storage, flags);

    }
}
