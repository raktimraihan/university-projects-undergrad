package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.os.Build.ID;

public class loginDriver extends AppCompatActivity {
    EditText UserNameEt, PasswordEt;
    String str1= "";
    int str=0;
    Intent intr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_logindriver);

        //get editText variables from loginDriver Page

        UserNameEt =(EditText) findViewById(R.id.mailRenter);
        PasswordEt =(EditText) findViewById(R.id.passwordRenter);

    }

    public void loginOnClick(View view) {
        String userName= UserNameEt.getText().toString();
        String password= PasswordEt.getText().toString();
        String type = "driverLoginPage";


        BackgroundDriverLogin driverLogin= new BackgroundDriverLogin(this);
        driverLogin.execute(type, userName, password);
        if(str1.equals("Wrong User ID or Password")){
            str=1;
        }
        else {
            str=2;
        }
        switch(str) {
            case 1:
                //intr = new Intent(this, this);
                break;
            case 2:
                //intr = new Intent(this, MapsActivity.class);
                //startActivity(intr);
                break;


            default: break;

        }

    }


}
