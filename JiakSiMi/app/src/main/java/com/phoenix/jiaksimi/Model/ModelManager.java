package com.phoenix.jiaksimi.Model;

import android.os.Parcel;

import com.phoenix.jiaksimi.Util.FoodType;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelManager implements Model {

    private final static String LOG_TAG = ModelManager.class.getName();
    private final static Logger logger = Logger.getLogger(LOG_TAG);

    private final ArrayList<String> MEAL_LIST;
    private final ArrayList<String> MEAT_LIST;
    private final ArrayList<String> SOUP_LIST;
    private final ArrayList<String> VEG_LIST;

    public ModelManager() {
        MEAL_LIST = new ArrayList<>();
        MEAT_LIST = new ArrayList<>();
        SOUP_LIST = new ArrayList<>();
        VEG_LIST = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    private ModelManager(Parcel in) {
        MEAL_LIST = in.readArrayList(String.class.getClassLoader());
        MEAT_LIST = in.readArrayList(String.class.getClassLoader());
        SOUP_LIST = in.readArrayList(String.class.getClassLoader());
        VEG_LIST = in.readArrayList(String.class.getClassLoader());
    }

    //=============================================================================================

    public boolean addFood(String food, FoodType type) {

        logger.setLevel(Level.WARNING);

        switch (type) {
            case MEAL:
                return MEAL_LIST.add(food);
            case MEAT:
                return MEAT_LIST.add(food);
            case VEG:
                return VEG_LIST.add(food);
            case SOUP:
                return SOUP_LIST.add(food);
            default:
                logger.log(Level.WARNING, "Invalid food type");
                return false;
        }
    }

    public ArrayList<String> getList(FoodType type) {

        logger.setLevel(Level.WARNING);

        switch (type) {
            case MEAL:
                return MEAL_LIST;
            case MEAT:
                return MEAT_LIST;
            case VEG:
                return VEG_LIST;
            case SOUP:
                return SOUP_LIST;
            default:
                logger.log(Level.WARNING, "Invalid food type");
                return new ArrayList<>();
        }
    }

    //=============================================================================================

    public static final Creator<ModelManager> CREATOR = new Creator<ModelManager>() {
        @Override
        public ModelManager createFromParcel(Parcel in) {
            return new ModelManager(in);
        }

        @Override
        public ModelManager[] newArray(int size) {
            return new ModelManager[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(MEAL_LIST);
        dest.writeList(MEAT_LIST);
        dest.writeList(SOUP_LIST);
        dest.writeList(VEG_LIST);
    }


}
