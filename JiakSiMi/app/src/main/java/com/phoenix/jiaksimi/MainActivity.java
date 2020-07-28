package com.phoenix.jiaksimi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.phoenix.jiaksimi.Logic.Logic;
import com.phoenix.jiaksimi.Ui.Adder;
import com.phoenix.jiaksimi.Ui.Menu;
import com.phoenix.jiaksimi.Ui.Randomizer;
import com.phoenix.jiaksimi.Ui.SectionsPagerAdapter;
import com.phoenix.jiaksimi.Util.FoodType;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Randomizer.OnFragmentInteractionListener, Menu.OnFragmentInteractionListener, Adder.OnFragmentInteractionListener {

    private Logic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        logic = intent.getParcelableExtra("logic");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //Do nothing
    }

    /*
     * Since MainActivity is called from loader, to prevent the app from going back into loader
     * using the back button, the app will exit if back is pressed.
     */
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //============================================================================================

    public void writeData(String data, String filepath) {
        logic.writeData(this, data, filepath);
    }

    public void addFoodItem(String item, FoodType type) {

        logic.addFoodItemToList(item, type);

    }

    public ArrayList<String> getFoodList(FoodType type) {

        return logic.getFoodList(type);

    }

    public String randomizeFood(FoodType type) {

        return logic.randomizeFood(type);

    }
}