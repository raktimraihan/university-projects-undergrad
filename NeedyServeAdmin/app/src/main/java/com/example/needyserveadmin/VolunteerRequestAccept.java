package com.example.needyserveadmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import background.BackGroundVolunteerRequestAccept;

public class VolunteerRequestAccept extends AppCompatActivity {

    private String id, name, email, phone, gender, donor_status;
    private RecyclerView recyclerView, recyclerView1;
    private RecyclerView.LayoutManager layoutManager, layoutManager1;
    private int position;
    private ArrayList<FoodItem> foodItems;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_request_accept);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Volunteer Request List ");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        email=intent.getStringExtra("email");
        name=intent.getStringExtra("name");
        phone=intent.getStringExtra("phone");
        gender=intent.getStringExtra("gender");
        donor_status=intent.getStringExtra("donor_status");

        recyclerView = findViewById(R.id.volunteerList);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        BackGroundVolunteerRequestAccept backGroundVolunteerRequestAccept = new BackGroundVolunteerRequestAccept(this, recyclerView,layoutManager, id, email,name,phone, gender,donor_status);
        backGroundVolunteerRequestAccept.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AdminOptions.class);
        intent.putExtra("id",id);
        intent.putExtra("email", email);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("gender",gender);
        intent.putExtra("donor_status",donor_status);
        startActivity(intent);
    }
}
