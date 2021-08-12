package com.example.parksafe.parksafe.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginRenter extends AppCompatActivity {
    EditText UserNameE, PasswordE, accessCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loginrenter);
        //

        //get editText variables from loginDriver Page

        UserNameE = (EditText) findViewById(R.id.mailRenter);
        PasswordE = (EditText) findViewById(R.id.passwordRenter);
        accessCode = (EditText) findViewById(R.id.accesCodeRenter);
    }

    public void loginOnClickRenter(View view){
        String userName= UserNameE.getText().toString();
        String password= PasswordE.getText().toString();
        String access= accessCode.getText().toString();
        String type = "renterLoginPage";

        BackgroundRenterLogin renterLogin= new BackgroundRenterLogin(this);
        renterLogin.execute(type, userName, password, access);
    }

}
