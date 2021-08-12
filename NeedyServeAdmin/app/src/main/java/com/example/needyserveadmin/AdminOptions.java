package com.example.needyserveadmin;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminOptions extends AppCompatActivity {
    private String id, name, email, phone, gender, donor_status;
    private RecyclerView recyclerView, recyclerView1;
    private RecyclerView.LayoutManager layoutManager, layoutManager1;
    private int position;
    private ArrayList<FoodItem> foodItems;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Admin Panel ");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        email=intent.getStringExtra("email");
        name=intent.getStringExtra("name");
        phone=intent.getStringExtra("phone");
        gender=intent.getStringExtra("gender");
        donor_status=intent.getStringExtra("donor_status");

        TextView nameT = findViewById(R.id.adminName);
        nameT.setText("Welcome, "+name);
        TextView messageAdmin = findViewById(R.id.messageAdmin);
        messageAdmin.setText("As an System Administrator your main duties are:" +
                "\na) To check and review each volunteers' status." +
                "\nb) To review collected items as well as posted items by donor." +
                "\nNote that: You have the ultimate right to access and modify the internal database.");

    }


    public void ReviewCollectedItems(View view){
        Intent intent = new Intent(this,ViewAllIItem.class);
        intent.putExtra("flag","collect");
        startActivity(intent);

    }

    public void ReviewPostedItems(View view){
        Intent intent = new Intent(this,ViewAllIItem.class);
        intent.putExtra("flag","post");
        startActivity(intent);
    }

    public void VolunteerRequests(View view){
        Intent intent = new Intent(this,VolunteerRequestAccept.class);
        intent.putExtra("id",id);
        intent.putExtra("email", email);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("gender",gender);
        intent.putExtra("donor_status",donor_status);
        startActivity(intent);
    }
}
