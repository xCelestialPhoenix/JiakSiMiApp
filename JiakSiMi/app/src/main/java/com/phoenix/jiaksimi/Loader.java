package com.phoenix.jiaksimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.phoenix.jiaksimi.Logic.LogicManager;
import com.phoenix.jiaksimi.Model.Model;
import com.phoenix.jiaksimi.Model.ModelManager;
import com.phoenix.jiaksimi.Storage.StorageManager;

public class Loader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        Model model = new ModelManager();
        StorageManager storage = new StorageManager(model);
        storage.loadData(this);
        LogicManager logic = new LogicManager(model, storage);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("logic", logic);
        startActivity(intent);
    }

}
