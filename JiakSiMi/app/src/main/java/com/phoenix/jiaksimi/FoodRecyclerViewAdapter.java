package com.phoenix.jiaksimi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> dataList;
    String type;
    Context context;

    public FoodRecyclerViewAdapter(Context context, ArrayList<String> data, String type) {
        dataList = data;
        this.type = type;
        this.context = context;
    }

    void changeDataSet(ArrayList<String> data, String type) {
        dataList = data;
        this.type = type;
        FoodRecyclerViewAdapter.super.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.foodNameTextView.setText(dataList.get(position));
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.layout);
                popup.getMenuInflater().inflate(R.menu.food_list_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String selection = item.getTitle().toString();
                        switch (selection) {
                            case "Delete":
                                FileOutputStream outStream = null;
                                dataList.remove(position);
                                String filename = type + ".data";
                                String data = "";
                                for(String entry : dataList) {
                                    data += entry + "\n";
                                }
                                try {
                                     outStream = context.openFileOutput(filename,
                                             context.MODE_PRIVATE);
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
                                FoodRecyclerViewAdapter.super.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    void setDataList(ArrayList<String> newDataList) {
        dataList = newDataList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodNameTextView;
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            foodNameTextView = view.findViewById(R.id.foodNameTextView);
            layout = view.findViewById(R.id.foodViewLayout);
        }
    }
}
