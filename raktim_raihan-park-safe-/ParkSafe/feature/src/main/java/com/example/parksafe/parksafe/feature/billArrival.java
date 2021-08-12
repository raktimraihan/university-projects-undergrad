package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class billArrival extends AppCompatActivity {

    String position,renter_id,status;
    ArrayList<String> rateList = new ArrayList<String>();
    ArrayList<String> driver_id_list= new ArrayList<String>();
    ArrayList<String> driver_mail_list= new ArrayList<String>();
    ArrayList<String> driver_phone_list= new ArrayList<String>();
    ArrayList<String> registration_list= new ArrayList<String>();
    ArrayList<String> driver_name_list= new ArrayList<String>();
    ArrayList<String> time= new ArrayList<String>();
    ArrayList<String> endTimelist= new ArrayList<>();
    String time2;
    Date date1,date2;
    long diff;
    private String[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bill_arrival);

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
        endTimelist=intn.getStringArrayListExtra("endTime_list");

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

        TextView datePaid= findViewById(R.id.timeOfPay);
        TextView trans= findViewById(R.id.lastTrans);
        TextView phoneLast= findViewById(R.id.lastPhn);
        TextView noPayment= findViewById(R.id.noPayment);
        Button btn_receive= findViewById(R.id.freeSpace);

        BackGroundArrivalBillInformation billInformation= new BackGroundArrivalBillInformation(getApplication());


        try {
            String message= billInformation.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id_list.get(Integer.parseInt(position)),renter_id).get().toString();
            array=message.split(";.;");
            if(!array[3].equals("empty")){
                datePaid.setText("Payment made on: "+array[0]);
                trans.setText("Last four digit of Trans.ID: "+array[2]);
                phoneLast.setText("Last four digit of bKash num: "+array[1]);
                fare.setText("Fare to be paid: "+array[4]+" taka.");
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

    public void endSession(View view) {
        BackGroundEndSession bend = new BackGroundEndSession(getApplicationContext());
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        bend.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id_list.get(Integer.parseInt(position)),renter_id,timeStamp);
        Toast.makeText(getApplicationContext(),"Session Ended.",Toast.LENGTH_LONG).show();
        Intent intn= new Intent(billArrival.this,renterLanding.class);
        intn.putExtra("renter_id",renter_id);
        intn.putExtra("status",status);
        intn.putExtra("time",time2);
        startActivity(intn);
    }

    public void payReceive(View view) {
        BackGroundArrivalBillReceive bell= new BackGroundArrivalBillReceive(getApplicationContext());
        Toast.makeText(getApplicationContext(),"Paymet Reviewed.",Toast.LENGTH_LONG).show();
        bell.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id_list.get(Integer.parseInt(position)),renter_id);
        Toast.makeText(getApplicationContext(),"Session Ended.",Toast.LENGTH_LONG).show();
        Intent intn= new Intent(billArrival.this,renterLanding.class);
        intn.putExtra("renter_id",renter_id);
        intn.putExtra("status",status);
        intn.putExtra("time",time2);
        startActivity(intn);
    }
}
