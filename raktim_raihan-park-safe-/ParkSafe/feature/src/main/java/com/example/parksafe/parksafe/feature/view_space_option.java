package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class view_space_option extends AppCompatActivity {
    String renter_id;
    String driver_id;
    ArrayList<String> renterIDlist;
    ArrayList<String> addressList;
    ArrayList<String> lattituteList;
    ArrayList<String> longituteList;
    ArrayList<String> rateList;
    String name,phone_value_get,email_value,address1,rate12;
    String json_string="";
    String msg="";
    String rate1="";
    String timeStamp="";
    String[] array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_space_option);
        Intent intent = getIntent();
        renter_id= intent.getStringExtra("renter_id");
         renterIDlist= intent.getStringArrayListExtra("listOFRenter_id");
         addressList= intent.getStringArrayListExtra("address");
         lattituteList= intent.getStringArrayListExtra("lattitute");
         longituteList= intent.getStringArrayListExtra("longitute");
         rateList= intent.getStringArrayListExtra("rate");
         driver_id= intent.getStringExtra("driver_id");

        timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        //background
        ViewSpaceLocationBackground viewSpaceLocationBackground=new ViewSpaceLocationBackground(getApplicationContext());
        try {
            json_string = viewSpaceLocationBackground.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,renter_id).get().toString();
            array= json_string.split(";");
            name=array[0];
            phone_value_get=array[1];
            email_value=array[2];
            address1=array[3];
            rate12= array[4];

            TextView phone_1= (TextView) findViewById(R.id.textView11);
            phone_1.setText(phone_value_get);
            TextView email_1 = (TextView) findViewById(R.id.textView28);
            email_1.setText(email_value);
            TextView name_1= (TextView) findViewById(R.id.textView);
            name_1.setText(name);
            TextView address= (TextView) findViewById(R.id.textView9);
            address.setText(address1);

            TextView rate= (TextView) findViewById(R.id.textView17);
            rate.setText(rate12);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void confirmRent(View view) {
        try {
            CheckRequestInfo check= new CheckRequestInfo(getApplicationContext());
            msg=check.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id,renter_id,rate12,timeStamp).get().toString();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(msg.equals("Request Confirmed!")){

        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
        Intent intn= new Intent(view_space_option.this,direction.class);
        intn.putExtra("renter_id",renter_id);
        intn.putStringArrayListExtra("listOFRenter_id",renterIDlist);
        intn.putStringArrayListExtra("address",addressList);
        intn.putStringArrayListExtra("lattitute",lattituteList);
        intn.putStringArrayListExtra("longitute",longituteList);
        intn.putStringArrayListExtra("rate",rateList);
        intn.putExtra("driver_id",driver_id);
        startActivity(intn);

        }

        if(msg.equals("Please select another location.")){
            Toast.makeText(getApplicationContext(),msg+" This location is taken already.",Toast.LENGTH_LONG).show();
            Intent itn= new Intent(getApplicationContext(),MapsActivity.class);
            itn.putExtra("driver_id",driver_id);
            startActivity(itn);
        }

        if(msg.equals("Oops!")){
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            Intent intn=new Intent(view_space_option.this,payYourBillDriver.class);
            intn.putExtra("renter_id",renter_id);
            intn.putStringArrayListExtra("listOFRenter_id",renterIDlist);
            intn.putStringArrayListExtra("address",addressList);
            intn.putStringArrayListExtra("lattitute",lattituteList);
            intn.putStringArrayListExtra("longitute",longituteList);
            intn.putStringArrayListExtra("rate",rateList);
            intn.putExtra("driver_id",driver_id);
            startActivity(intn);

        }

    }
}
