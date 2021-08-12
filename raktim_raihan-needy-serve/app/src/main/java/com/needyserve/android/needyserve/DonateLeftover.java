package com.needyserve.android.needyserve;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.needyserve.android.needyserve.com.needyserve.background.BackGroundRegDonate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DonateLeftover extends AppCompatActivity {
    private String id, date1;
    private EditText fName, fDes, Flocation, Fserving;
    private RadioGroup Ftime;
    private int timeDonate;
    private Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_leftover);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.activity_custom_action_title);
        TextView titleBar = findViewById(R.id.titleBar);
        titleBar.setText("List Your Food for Donation. ");

        Intent intent = getIntent();
        id=intent.getStringExtra("id");

        fName = findViewById(R.id.food_name_donate);
        fDes = findViewById(R.id.food_des_donate);
        Ftime = (RadioGroup)findViewById(R.id.donate);
        Flocation = findViewById(R.id.location_donate);
        Fserving = findViewById(R.id.serving_donate);
        Ftime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton3:
                        timeDonate = 6;
                        break;
                    case R.id.radioButton2:
                        timeDonate = 12;
                        break;
                    case R.id.radioButton:
                        timeDonate = 24;
                        break;

                    default: timeDonate = 12; break;
                }
            }
        });



    }

    public void donateReg(View view) {
        date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        date1 = dateFormat.format(addHoursToJavaUtilDate(date, timeDonate));

        String f_nameT = fName.getText().toString();
        //String ftimeT = Ftime.getText().toString();
        String flocT = Flocation.getText().toString();
        String fDesT = fDes.getText().toString();
        String fSerT = Fserving.getText().toString();

        BackGroundRegDonate backGroundRegDonate = new BackGroundRegDonate(DonateLeftover.this);
        backGroundRegDonate.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,f_nameT,fDesT,date1, flocT, fSerT, id);


    }



    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
