package com.needyserve.android.needyserve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.needyserve.android.needyserve.com.needyserve.android.instances.AdaptarForVolunteerCollect;
import com.needyserve.android.needyserve.com.needyserve.android.instances.FoodItem;
import com.needyserve.android.needyserve.com.needyserve.background.BackgroundFetchForVolunteer;

import java.util.ArrayList;

public class VolunteerItemListAll extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerView1;
    private RecyclerView.LayoutManager layoutManager, layoutManager1;
    private int position;
    private ArrayList<FoodItem> foodItems;
    private RecyclerView.Adapter adapter;
    private OnVolunteerListItemClick onVolunteerListItemClick;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_item_list_all);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Available Foods ");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        recyclerView = findViewById(R.id.volunteerItemList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        BackgroundFetchForVolunteer backgroundItemFetchAll1 = new BackgroundFetchForVolunteer(VolunteerItemListAll.this, recyclerView, layoutManager,id);
        backgroundItemFetchAll1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        FromBackGroundFoodItem fromBackGroundFoodItem = new BackgroundFetchForVolunteer();
        foodItems= backgroundItemFetchAll1.foodItemList();

    }

}
