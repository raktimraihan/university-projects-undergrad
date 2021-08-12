package com.needyserve.android.needyserve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.needyserve.android.needyserve.com.needyserve.background.BackGroundContactUs;

public class ContactUs extends AppCompatActivity {

    private String name, email, phone, type, message, id;
    private TextView nameT, emailT,phoneT,messageT;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText(" Contact Us ");

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        id = intent.getStringExtra("id");

        nameT = findViewById(R.id.nameContactus);
        nameT.setText(name);
        emailT = findViewById(R.id.emailContcs);
        emailT.setText(email);
        phoneT = findViewById(R.id.phoneContactUs);
        phoneT.setText(phone);

        messageT = findViewById(R.id.messageContactUs);
        radioGroup = findViewById(R.id.radioGroup2);

    }

    public void sendMessage(View view) {
        type = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        message = messageT.getText().toString();
        BackGroundContactUs backGroundContactUs = new BackGroundContactUs(this);
        backGroundContactUs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,name,email,phone,type,message,id);

    }
}
