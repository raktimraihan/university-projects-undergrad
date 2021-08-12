package com.needyserve.android.needyserve;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.needyserve.android.needyserve.com.needyserve.background.LoginBackGround;

import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    private EditText name,email,phone,password;
    private RadioGroup radioGroup;
    private String name_str, email_str,phone_str,password_str,gender_str;
    private static String message = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().hide();

        //edittext
        name = (EditText)findViewById(R.id.name_reg);
        name_str = name.getText().toString();
        email = (EditText)findViewById(R.id.email_reg);
        email_str = email.getText().toString();
        phone = (EditText)findViewById(R.id.phone_reg);
        phone_str = phone.getText().toString();
        password = (EditText)findViewById(R.id.password_reg);
        password_str = password.getText().toString();
        //radioGroup
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);




    }

    public void register(View view) throws ExecutionException, InterruptedException {
        gender_str = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        name_str = name.getText().toString();
        email_str = email.getText().toString();
        phone_str = phone.getText().toString();
        password_str = password.getText().toString();

        LoginBackGround loginBackGround = new LoginBackGround(Register.this);
        loginBackGround.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,name_str,email_str,phone_str,password_str,gender_str);
        //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

}
