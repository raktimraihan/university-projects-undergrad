package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class makePaymentOption extends AppCompatActivity {
    private String driver_id,message,name,rate,phone,start_time,end_time,lastFourPhone,lastFourTrans,renter_id;
    private String[] array;
    private TextView description,mobile;
    private Date date1,date2;
    private String  dateToday;
    private long diff;
    private int bill;
    private EditText phoneNum,transNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_make_payment_option);

        description= findViewById(R.id.dueDescription);
        mobile=findViewById(R.id.mobile);
        phoneNum=findViewById(R.id.last_4_phn);
        transNum=findViewById(R.id.last_4_trans);
        Button btn= findViewById(R.id.button3);
        Intent intn=getIntent();
        driver_id=intn.getStringExtra("driver_id");

        BackGroundMakePaymentOption pOption= new BackGroundMakePaymentOption(getApplicationContext());
        try {
            message=pOption.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id).get().toString();
            array=message.split(";.;");
            name=array[0];
            phone=array[1];
            rate=array[2];
            start_time=array[3];
            end_time=array[4];
            renter_id=array[5];

            SimpleDateFormat time_now= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateToday=time_now.format(new Date(System.currentTimeMillis()));
            date1= time_now.parse(start_time);
            date2=time_now.parse(end_time);
            diff= (date2.getTime()-date1.getTime())/(1000*60*30);
            if(diff==0){
                bill= Integer.parseInt(rate);
            }else {
                bill = (int) (diff * Integer.parseInt(rate) + 0.5);
            }
            if(bill>0) {
                description.setText("You have due to renter " + name + ". Due bill= " + bill + " taka.");
                mobile.setText("Bkash mobile no: " + phone);
                btn.setVisibility(View.VISIBLE);
            }
            else {
                description.setText("Please inform renter to end your session FIRST");
                btn.setVisibility(View.GONE);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void makePayment(View view) {
        lastFourPhone=phoneNum.getText().toString();
        lastFourTrans=transNum.getText().toString();
        Toast.makeText(makePaymentOption.this,"Wait untill renter reviews your payment.",Toast.LENGTH_LONG).show();
        BackgroundMakePaymentInsertDB mDB= new BackgroundMakePaymentInsertDB(getApplicationContext());
        mDB.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id,renter_id,String.valueOf(bill),dateToday,lastFourPhone,lastFourTrans);
        Intent intn= new Intent(makePaymentOption.this,welcome_page.class);
        startActivity(intn);


    }
}
