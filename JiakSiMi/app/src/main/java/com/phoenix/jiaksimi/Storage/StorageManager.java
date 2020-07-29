package com.phoenix.jiaksimi.Storage;

import android.content.Context;
import android.os.Parcel;

import com.phoenix.jiaksimi.Model.Model;
import com.phoenix.jiaksimi.Util.FoodType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.MODE_APPEND;
import static com.phoenix.jiaksimi.Util.Filepath.LUNCH_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.MEAT_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.SOUP_DATA_FILEPATH;
import static com.phoenix.jiaksimi.Util.Filepath.VEG_DATA_FILEPATH;

public class StorageManager implements Storage {

    private Model model;

    public StorageManager(Model model) {
        this.model = model;
    }

    private StorageManager(Parcel in) {
        model = in.readParcelable(Model.class.getClassLoader());
    }

    //=============================================================================================

    public void loadData(Context context) {

        readAndInitFoodList(LUNCH_DATA_FILEPATH, model.getList(FoodType.LUNCH), context);
        readAndInitFoodList(MEAT_DATA_FILEPATH, model.getList(FoodType.MEAT), context);
        readAndInitFoodList(SOUP_DATA_FILEPATH, model.getList(FoodType.SOUP), context);
        readAndInitFoodList(VEG_DATA_FILEPATH, model.getList(FoodType.VEG), context);

    }

    public void writeData(Context context, String data, String filepath) {

        FileOutputStream outStream = null;

        try {
            outStream = context.openFileOutput(filepath, MODE_APPEND);
            outStream.write(data.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readAndInitFoodList(String filename, ArrayList<String> foodList, Context context) {

        String line;
        FileInputStream inStream = null;

        try {
            inStream = context.openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while ((line = reader.readLine()) != null) {
                foodList.add(line);
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //=============================================================================================

    public static final Creator<StorageManager> CREATOR = new Creator<StorageManager>() {

        @Override
        public StorageManager createFromParcel(Parcel in) {
            return new StorageManager(in);
        }

        @Override
        public StorageManager[] newArray(int size) {
            return new StorageManager[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(model, flags);
    }
}
