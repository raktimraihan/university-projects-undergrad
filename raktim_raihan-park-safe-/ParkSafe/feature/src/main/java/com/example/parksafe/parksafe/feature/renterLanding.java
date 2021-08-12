package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class renterLanding extends AppCompatActivity {
    private String renter_id;
    private String time;
    String time2;
    String status;
    Date date1,date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_renter_landing);

        Intent itn = getIntent();
        renter_id=itn.getStringExtra("renter_id");
        status=itn.getStringExtra("status");
        time=itn.getStringExtra("time");

        SimpleDateFormat time_now= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time2=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

        try {
             date1= time_now.parse(time);
             date2= time_now.parse(time2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView onl= findViewById(R.id.online);
        if(status.equals("1") && date2.before(date1)){
            onl.setText("Online.");
        }
        else{
            onl.setText("Offline.");
            BackGroundGoOffline backGroundGoOffline= new BackGroundGoOffline(getApplicationContext());
            backGroundGoOffline.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"",renter_id);
        }


    }

    public void reviewYourSpace(View view) {
        Button btn_view_space= findViewById(R.id.reviewPlace);
        Intent itn=new Intent(this,checkAvailableSpace.class);
        itn.putExtra("renter_id",renter_id);
        itn.putExtra("status",status);
        itn.putExtra("time",time);
        startActivity(itn);
    }

    public void userParkNowList(View view) {

        Intent itn=new Intent(this,userWhoAreStaying.class);
        itn.putExtra("renter_id",renter_id);
        itn.putExtra("status",status);
        itn.putExtra("time",time);
        startActivity(itn);
    }

    public void stayingUsersArrival(View view) {
        Intent itn=new Intent(this,UsersStayingAfterArrival.class);
        itn.putExtra("renter_id",renter_id);
        itn.putExtra("status",status);
        itn.putExtra("time",time);
        startActivity(itn);
    }

    public void goOffline(View view) {
        BackGroundGoOffline off= new BackGroundGoOffline(getApplicationContext());
        off.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"",renter_id);
        Toast.makeText(renterLanding.this,"Location Offline, won't be visible.",Toast.LENGTH_LONG).show();
        Intent itn=new Intent(this,renterLanding.class);
        itn.putExtra("renter_id",renter_id);
        itn.putExtra("status","0");
        itn.putExtra("time",time);
        startActivity(itn);
    }
}
