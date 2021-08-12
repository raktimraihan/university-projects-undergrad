package com.example.needyserveadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import background.BackGroundLoginRL;

public class LoginActivity extends AppCompatActivity {

    private EditText mail_Ed, password_Ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().hide();

        mail_Ed = findViewById(R.id.emailContcs);
        password_Ed = findViewById(R.id.password_login);
    }

    public void login(View view) {

        String mail = mail_Ed.getText().toString().trim();
        String password = password_Ed.getText().toString().trim();

        BackGroundLoginRL backGroundLoginRL = new BackGroundLoginRL(LoginActivity.this);
        backGroundLoginRL.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,mail,password);


    }

}
