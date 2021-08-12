package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class arrived extends AppCompatActivity {

    private String renter_id;
    private String driver_id;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_arrived);

        Intent intn= getIntent();
        driver_id=intn.getStringExtra("driver_id");
        renter_id=intn.getStringExtra("renter_id");
        address=intn.getStringExtra("address");


        TextView arrival_success= findViewById(R.id.address_arrived);
        arrival_success.setText(address);
        Toast.makeText(getApplicationContext(),"Application will exit after 15 sec.",Toast.LENGTH_SHORT).show();



        Handler hndler= new Handler();
        hndler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finishAffinity();
                System.exit(0);
            }
        },15*1000);


    }
}
