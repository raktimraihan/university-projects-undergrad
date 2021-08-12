package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
    Context ctx;
    EditText Name, Phone, Email, Password, V_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.signup);

        Name= (EditText) findViewById(R.id.mailRenter);
        Phone= (EditText) findViewById(R.id.passwordRenter);
        Email= (EditText) findViewById(R.id.accesCodeRenter);
        Password= (EditText) findViewById(R.id.editText5);
        V_reg= (EditText) findViewById(R.id.editText7);
    }


    public void loginDriver(View view) {

        String name= Name.getText().toString();
        String phone= Phone.getText().toString();
        String email= Email.getText().toString();
        String password= Password.getText().toString();
        String v_reg= V_reg.getText().toString();
        String type= "signup";

        BackGroundSignup signup=new BackGroundSignup(this);
        signup.execute(name,phone,email,password,v_reg,type);

        Name.getText().clear();
        Phone.getText().clear();
        Password.getText().clear();
        Email.getText().clear();
        V_reg.getText().clear();

        Intent inter= new Intent(this,welcome_page.class);
        startActivity(inter);

    }
}
