package com.phoenix.jiaksimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loader extends AppCompatActivity {

    //Arraylists to store the items from each category
    static ArrayList<String> mealList = new ArrayList<>();
    static ArrayList<String> meatList = new ArrayList<>();
    static ArrayList<String> soupList = new ArrayList<>();
    static ArrayList<String> vegList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        readData();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void readData() {
        readAndInit("meal.data", mealList);
        readAndInit("meat.data", meatList);
        readAndInit("soup.data", soupList);
        readAndInit("veg.data", vegList);
    }
    private void readAndInit(String fileName, ArrayList<String> list) {
        String line;
        FileInputStream inStream = null;
        try {
            inStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            while((line = reader.readLine()) != null) {
                list.add(line);
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
