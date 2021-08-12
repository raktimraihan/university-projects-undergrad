package com.example.parksafe.parksafe.feature;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.time.Instant;

public class welcome_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome_page);
    }

    public void loginPark(View view){
        Button btn= (Button) findViewById(R.id.driverLogin);
        Intent intnt = new Intent(this,loginDriver.class);
        startActivity(intnt);
    }

    public void renter(View view) {
        Button btn = findViewById(R.id.button2);
        Intent intn= new Intent(this,loginRenter.class);
        startActivity(intn);
    }

    public void signupUser(View view) {
        TextView txt= findViewById(R.id.textView4);
        Intent itn=new Intent(this,Signup.class);
        startActivity(itn);
    }
}

