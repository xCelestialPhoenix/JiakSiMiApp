package com.phoenix.jiaksimi.Storage;

import android.content.Context;
import android.os.Parcelable;

public interface Storage extends Parcelable {

    void loadData(Context context);
    void writeData(Context context, String data, String filepath);

}
