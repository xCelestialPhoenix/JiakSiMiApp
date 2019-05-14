package com.phoenix.jiaksimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Lobby extends AppCompatActivity {

    TextView menuTextview;
    TextView lunchTextView;
    TextView meatTextView;
    TextView vegTextView;
    TextView soupTextView;
    Context context;
    Button randomLunchButton;
    Button randomMeatButton;
    Button randomVegButton;
    Button randomSoupButton;
    Button massRandomButton;
    Random randGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        context = this;
        randGenerator = new Random();
        initView();
        initListener();
    }

    private void initView() {
        menuTextview = findViewById(R.id.menuTextView);
        randomLunchButton = findViewById(R.id.lunchRandButton);
        randomMeatButton = findViewById(R.id.meatRandButton);
        randomVegButton = findViewById(R.id.vegRandButton);
        randomSoupButton = findViewById(R.id.soupRandButton);
        massRandomButton = findViewById(R.id.massRandButton);
        lunchTextView = findViewById(R.id.lunchTextView);
        meatTextView = findViewById(R.id.meatTextView);
        vegTextView = findViewById(R.id.vegTextView);
        soupTextView = findViewById(R.id.soupTextView);
    }

    private void initListener() {
        menuTextview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodList.class);
                startActivity(intent);
            }
        });

        randomLunchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lunchTextView.setText(Loader.mealList.get(randGenerator.nextInt(Loader.mealList.size() - 1)));
            }
        });

        randomMeatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                meatTextView.setText(Loader.meatList.get(randGenerator.nextInt(Loader.meatList.size() - 1)));
            }
        });

        randomVegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                vegTextView.setText(Loader.vegList.get(randGenerator.nextInt(Loader.vegList.size() - 1)));
            }
        });

        randomSoupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                soupTextView.setText(Loader.soupList.get(randGenerator.nextInt(Loader.soupList.size() - 1)));
            }
        });

        massRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lunchTextView.setText(Loader.mealList.get(randGenerator.nextInt(Loader.mealList.size() - 1)));
                meatTextView.setText(Loader.meatList.get(randGenerator.nextInt(Loader.meatList.size() - 1)));
                vegTextView.setText(Loader.vegList.get(randGenerator.nextInt(Loader.vegList.size() - 1)));
                soupTextView.setText(Loader.soupList.get(randGenerator.nextInt(Loader.soupList.size() - 1)));
            }
        });
    }

    //Since Lobby is called from loader, to prevent the app from going back into loader using the
    //back button, the app will exit if back is pressed.
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
