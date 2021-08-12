package com.needyserve.android.needyserve.com.needyserve.android.instances;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.needyserve.android.needyserve.CheckoutVolunteer;
import com.needyserve.android.needyserve.FromBackGroundFoodItem;
import com.needyserve.android.needyserve.OnVolunteerListItemClick;
import com.needyserve.android.needyserve.R;

import java.io.Serializable;
import java.util.ArrayList;

public class AdaptarForVolunteerCollect extends RecyclerView.Adapter<AdaptarForVolunteerCollect.ExampleViewHolder> implements FromBackGroundFoodItem {
    private ArrayList<FoodItem> foodItems;
    private Context context;
    private String id;

    @Override
    public AdaptarForVolunteerCollect.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_navigation_drawer_item, parent, false);
        AdaptarForVolunteerCollect.ExampleViewHolder exampleViewHolder = new AdaptarForVolunteerCollect.ExampleViewHolder(view);
        return  exampleViewHolder;
    }

    @Override
    public void onBindViewHolder(AdaptarForVolunteerCollect.ExampleViewHolder holder, final int position) {
        FoodItem foodItem = foodItems.get(position);
        holder.vdName.setText(foodItem.getDonorName().toUpperCase());
        holder.vFoodName.setText("Food: "+foodItem.getFoodName());
        holder.vFoodDescription.setText(foodItem.getFoodDescription());
        holder.vServingPerson.setText("Approx. Serv: "+foodItem.getServingPerson());
        holder.vLocation.setText(foodItem.getLocation());
        holder.vTime.setText("Collect Before: "+ foodItem.getTimeCollect());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodItemList();
                Intent intent = new Intent(context, CheckoutVolunteer.class);
                intent.putExtra("id",id);
                intent.putExtra("position",Integer.toString(position));
                Bundle args = new Bundle();
                args.putSerializable("foodItems",(Serializable)foodItems);
                intent.putExtra("BUNDLE",args);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    @Override
    public ArrayList<FoodItem> foodItemList() {
        return foodItems;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView vdName, vLocation, vTime, vServingPerson, vFoodName, vFoodDescription;
        public ConstraintLayout constraintLayout;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            vdName = itemView.findViewById(R.id.donor_name);
            vLocation = itemView.findViewById(R.id.location);
            vTime = itemView.findViewById(R.id.collect_time);
            vServingPerson = itemView.findViewById(R.id.servingPerson);
            vFoodName = itemView.findViewById(R.id.food_name);
            vFoodDescription = itemView.findViewById(R.id.food_description);
            constraintLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

    public AdaptarForVolunteerCollect(ArrayList<FoodItem> foodItems,Context context,String id){
        this.foodItems=foodItems;
        this.context=context;
        this.id=id;
    }
    public AdaptarForVolunteerCollect(){

    }

}
