package com.phoenix.jiaksimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class FoodListAdder extends AppCompatActivity {

    Button addButton;
    TextView foodEditText;
    Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_adder);

        initView();
        initListener();

        Intent intent = getIntent();
        if(intent.hasExtra("index")) {
            categorySpinner.setSelection(intent.getExtras().getInt("index"));
        }
    }
    private void writeData(String data, String fileName) {
        FileOutputStream outStream = null;
        try {
            outStream = openFileOutput(fileName, MODE_APPEND);
            outStream.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView() {
        foodEditText = findViewById(R.id.foodEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        addButton = findViewById(R.id.addButton);
    }

    private void initListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Prevent adding empty entries into the dataset
                if(foodEditText.getText().toString().isEmpty()) {
                    Toast.makeText(FoodListAdder.this, "Insert a name to add",
                            Toast.LENGTH_SHORT).show();
                } else {
                    String food = foodEditText.getText().toString();
                    String category = categorySpinner.getSelectedItem().toString();

                    //Check which category to write into
                    switch (category) {
                        case "Meals":
                            writeData(food + "\n", "meal.data");
                            Loader.mealList.add(food);
                            break;
                        case "Meat/Diary":
                            writeData(food + "\n", "meat.data");
                            Loader.meatList.add(food);
                            break;
                        case "Soup":
                            writeData(food + "\n", "soup.data");
                            Loader.soupList.add(food);
                            break;
                        case "Vegetable":
                            writeData(food + "\n", "veg.data");
                            Loader.vegList.add(food);
                            break;
                    }

                    //Clear out foodEditText after the entry has been stored
                    foodEditText.setText("");
                    //Confirmation Notification
                    Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //To ensure recyclerView updates the data, we need to start FoodList activity instead of
    //going back to the original state so all the views will be re-instantiated.
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, FoodList.class);
        intent.putExtra("index", categorySpinner.getSelectedItemPosition());
        startActivity(intent);
    }
}
