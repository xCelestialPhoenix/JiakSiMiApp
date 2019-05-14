package com.phoenix.jiaksimi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class FoodList extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mealTab;
    TabItem meatTab;
    TabItem vegTab;
    TabItem soupTab;
    TextView addTextview;
    Context context;
    RecyclerView foodRecyclerView;
    FoodRecyclerViewAdapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        context = this;
        initView();
        initListener();

        //Setting the properties of recyclerView and its components
        rAdapter = new FoodRecyclerViewAdapter(this, Loader.mealList, "meal");
        foodRecyclerView.setAdapter(rAdapter);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerView.setHasFixedSize(true);

        //Setting the tab according to the spinner selection in FoodListAdder
        //If the activity is invoked from Lobby, the default is implemented.
        Intent intent = getIntent();
        if(intent.hasExtra("index")) {
            tabLayout.getTabAt(intent.getExtras().getInt("index")).select();
        }
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        mealTab = findViewById(R.id.mealTab);
        meatTab = findViewById(R.id.meatTab);
        vegTab = findViewById(R.id.vegTab);
        soupTab = findViewById(R.id.soupTab);
        addTextview = findViewById(R.id.addTextView);
        foodRecyclerView = findViewById(R.id.foodRecyclerView);
    }

    private void initListener() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                switch (index) {
                    case 0:
                        rAdapter.changeDataSet(Loader.mealList, "meal");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        rAdapter.changeDataSet(Loader.meatList, "meat");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        rAdapter.changeDataSet(Loader.vegList, "veg");
                        rAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        rAdapter.changeDataSet(Loader.soupList, "soup");
                        rAdapter.notifyDataSetChanged();
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Unused
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Unused
            }
        });

        addTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodListAdder.class);
                intent.putExtra("index", tabLayout.getSelectedTabPosition());
                startActivity(intent);
            }
        });
    }

    //To prevent going back to FoodListAdder when back is pressed.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Lobby.class);
        startActivity(intent);
    }
}
