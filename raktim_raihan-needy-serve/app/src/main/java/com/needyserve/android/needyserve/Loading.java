package com.needyserve.android.needyserve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.needyserve.android.needyserve.com.needyserve.android.instances.LandingNavigationDrawer;

public class Loading extends AppCompatActivity {

    private String id ="", name="", email="", phone="", gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        phone=intent.getStringExtra("phone");
        gender=intent.getStringExtra("gender");

        Intent intent1 = new Intent(this, LandingNavigationDrawer.class);
        intent1.putExtra("id",id);
        intent1.putExtra("email", email);
        intent1.putExtra("name",name);
        intent1.putExtra("phone",phone);
        intent1.putExtra("gender",gender);
        startActivity(intent1);
    }
}
