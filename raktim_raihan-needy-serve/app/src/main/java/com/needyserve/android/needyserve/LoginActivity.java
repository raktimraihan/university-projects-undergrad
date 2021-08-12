package com.needyserve.android.needyserve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.needyserve.android.needyserve.com.needyserve.background.BackGroundLoginRL;

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

    public void register(View view) {
        Intent intent= new Intent(getApplicationContext(),Register.class);
        startActivity(intent);
    }

    public void login(View view) {

        String mail = mail_Ed.getText().toString().trim();
        String password = password_Ed.getText().toString().trim();

        BackGroundLoginRL backGroundLoginRL = new BackGroundLoginRL(LoginActivity.this);
        backGroundLoginRL.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,mail,password);

    }
}
