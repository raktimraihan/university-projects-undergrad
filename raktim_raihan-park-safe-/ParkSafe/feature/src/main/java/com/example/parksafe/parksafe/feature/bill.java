package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class bill extends AppCompatActivity {

    String position,renter_id,status;
    ArrayList<String> rateList = new ArrayList<String>();
    ArrayList<String> driver_id_list= new ArrayList<String>();
    ArrayList<String> driver_mail_list= new ArrayList<String>();
    ArrayList<String> driver_phone_list= new ArrayList<String>();
    ArrayList<String> registration_list= new ArrayList<String>();
    ArrayList<String> driver_name_list= new ArrayList<String>();
    ArrayList<String> time= new ArrayList<String>();
    String time2;
    Date date1,date2;
    long diff;
    private String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bill);

        Intent intn=getIntent();
        driver_id_list=intn.getStringArrayListExtra("driver_id_list");
        driver_phone_list=intn.getStringArrayListExtra("driver_phone_list");
        driver_mail_list=intn.getStringArrayListExtra("driver_mail_list");
        registration_list=intn.getStringArrayListExtra("driver_reg_list");
        rateList=intn.getStringArrayListExtra("rate");
        time=intn.getStringArrayListExtra("StarTtime");
        position=intn.getStringExtra("position");
        renter_id=intn.getStringExtra("renter_id");
        driver_name_list=intn.getStringArrayListExtra("driver_name_list");
        status=intn.getStringExtra("status");
        time2=intn.getStringExtra("time");

        /*
        SimpleDateFormat time_now= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time2=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

        try {
            date1= time_now.parse(time.get(Integer.valueOf(position)));
            date2= time_now.parse(time2);
            diff= (date2.getTime()-date1.getTime())/(1000*60*30);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView txt= findViewById(R.id.textView10);
        int time=(int) diff;
        int rate= Integer.parseInt(rateList.get(Integer.parseInt(position)));

        int bill= (int)time*rate;
        txt.setText(String.valueOf(bill));
        */

        TextView name= findViewById(R.id.name);
        name.setText("Name: "+driver_name_list.get(Integer.parseInt(position))+". ");
        TextView phone = findViewById(R.id.phone);
        phone.setText("Phone Number: "+driver_phone_list.get(Integer.parseInt(position)));
        TextView email = findViewById(R.id.email);
        email.setText("Email: "+driver_mail_list.get(Integer.parseInt(position)));
        TextView reg = findViewById(R.id.reg);
        reg.setText("Vehicle Reg no: "+registration_list.get(Integer.parseInt(position)));
        TextView timeOfrequest = findViewById(R.id.timeOfRequest);
        timeOfrequest.setText("Request placed on: "+time.get(Integer.parseInt(position)));
        TextView fare = findViewById(R.id.fare);
        fare.setText("Total money to be paid: "+rateList.get(Integer.parseInt(position)));
        TextView datePaid= findViewById(R.id.timeOfPay);
        TextView trans= findViewById(R.id.lastTrans);
        TextView phoneLast= findViewById(R.id.lastPhn);
        TextView noPayment= findViewById(R.id.noPayment);
        Button btn_receive= findViewById(R.id.payReceive);

        BackGroundOnrequestBillInformation billInformation= new BackGroundOnrequestBillInformation(getApplicationContext());
        try {
            String message= billInformation.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id_list.get(Integer.parseInt(position)),renter_id).get().toString();
            array=message.split(";.;");
            if(!array[3].equals("empty")){
                datePaid.setText("Payment made on: "+array[0]);
                trans.setText("Last four digit of Trans.ID: "+array[2]);
                phoneLast.setText("Last four digit of bKash num: "+array[1]);
                btn_receive.setVisibility(View.VISIBLE);

            }
            else{
                noPayment.setText("No Payment Has made till now.");
                btn_receive.setVisibility(View.INVISIBLE);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void receivePay(View view) {
        String driver_id= driver_id_list.get(Integer.parseInt(position));
        BackgroundReceivePayRequest bcr= new BackgroundReceivePayRequest(getApplicationContext());
        bcr.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id,renter_id);
        Toast.makeText(getApplicationContext(),"Payment Received!",Toast.LENGTH_LONG).show();
        Intent intn= new Intent(bill.this,renterLanding.class);
        intn.putExtra("renter_id",renter_id);
        intn.putExtra("status",status);
        intn.putExtra("time",time2);
        startActivity(intn);


    }

    public void freeSpace(View view) {
        String driver_id= driver_id_list.get(Integer.parseInt(position));
        BackGroundSpaceFree bs= new BackGroundSpaceFree(getApplicationContext());
        try {
            String message= bs.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id,renter_id).get().toString();
            Toast.makeText(bill.this,message,Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
