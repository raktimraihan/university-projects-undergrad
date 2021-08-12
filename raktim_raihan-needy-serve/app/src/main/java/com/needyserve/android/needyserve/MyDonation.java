package com.needyserve.android.needyserve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.needyserve.android.needyserve.com.needyserve.background.MyDonationFetchAll;

public class MyDonation extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private RecyclerView.LayoutManager layoutManager1;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donation);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText("My Donations ");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        recyclerView1 = findViewById(R.id.recyclerView_my_donation);
        layoutManager1 = new LinearLayoutManager(getApplicationContext());

        MyDonationFetchAll myDonationFetchAll = new MyDonationFetchAll(MyDonation.this, recyclerView1, layoutManager1);
        myDonationFetchAll.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,id);

    }
}
