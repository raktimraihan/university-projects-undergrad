package com.needyserve.android.needyserve.com.needyserve.android.instances;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.needyserve.android.needyserve.R;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<FoodItem> foodItems;

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_navigation_drawer_item, parent, false);
        ExampleAdapter.ExampleViewHolder exampleViewHolder = new ExampleAdapter.ExampleViewHolder(view);
        return  exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(ExampleAdapter.ExampleViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.vdName.setText(foodItem.getDonorName().toUpperCase());
        holder.vFoodName.setText("Food: "+foodItem.getFoodName());
        holder.vFoodDescription.setText(foodItem.getFoodDescription());
        holder.vServingPerson.setText("Approx. Serv: "+foodItem.getServingPerson());
        holder.vLocation.setText(foodItem.getLocation());
        holder.vTime.setText("Collect Before: "+ foodItem.getTimeCollect());
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView vdName, vLocation, vTime, vServingPerson, vFoodName, vFoodDescription;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            vdName = itemView.findViewById(R.id.donor_name);
            vLocation = itemView.findViewById(R.id.location);
            vTime = itemView.findViewById(R.id.collect_time);
            vServingPerson = itemView.findViewById(R.id.servingPerson);
            vFoodName = itemView.findViewById(R.id.food_name);
            vFoodDescription = itemView.findViewById(R.id.food_description);
        }
    }

    public ExampleAdapter(ArrayList<FoodItem> foodItems) {
        this.foodItems=foodItems;
    }
}
