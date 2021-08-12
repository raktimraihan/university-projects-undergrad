package com.example.needyserveadmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import background.BackgroundFetchAll;

public class ViewAllIItem extends AppCompatActivity {
    private RecyclerView recyclerView, recyclerView1;
    private RecyclerView.LayoutManager layoutManager, layoutManager1;
    private String flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_iitem);

        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Ad Posted by Donors ");

        recyclerView = findViewById(R.id.viewall_recycle);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        BackgroundFetchAll backgroundFetchAll = new BackgroundFetchAll(this,recyclerView,layoutManager, flag);
        backgroundFetchAll.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
