package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class payYourBillDriver extends AppCompatActivity {
    String renter_id;
    String driver_id;
    ArrayList<String> renterIDlist;
    ArrayList<String> addressList;
    ArrayList<String> lattituteList;
    ArrayList<String> longituteList;
    ArrayList<String> rateList;
    String last_four_digit_phn;
    String transaction;
    String rate,date,renter_id1;
    EditText transaction_4_digit,phone_4_digit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pay_your_bill_driver);

        Intent intent = getIntent();
        renter_id= intent.getStringExtra("renter_id");
        renterIDlist= intent.getStringArrayListExtra("listOFRenter_id");
        addressList= intent.getStringArrayListExtra("address");
        lattituteList= intent.getStringArrayListExtra("lattitute");
        longituteList= intent.getStringArrayListExtra("longitute");
        rateList= intent.getStringArrayListExtra("rate");
        driver_id= intent.getStringExtra("driver_id");

        BackgroundBillVerification bill= new BackgroundBillVerification(getApplicationContext());
        try {
            String bill_txt= bill.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id).get().toString();
            String[] array= bill_txt.split(",");
            String name= array[0];
            String phone=array[1];
            String email= array[2];
            rate= array[3];
            date=array[4];
            renter_id1=array[5];


            TextView bill_header= (TextView) findViewById(R.id.textView12);
            String str= getString(R.string.due_bill_header);
            bill_header.setText(str+" "+rate+" "+getString(R.string.due_bill_middle)+" "+name);

            TextView contact=(TextView) findViewById(R.id.textView20);
            String cntct= getString(R.string.due_bill_middle_1);
            contact.setText(cntct+" "+date.substring(0,10));

            TextView phone_txt= (TextView) findViewById(R.id.textView21);
            String str_p= getString(R.string.phone);
            phone_txt.setText(str_p+" "+phone+".");

            TextView email_tx= findViewById(R.id.textView23);
            String str_mail=getString(R.string.mail);
            email_tx.setText(str_mail+" "+email);

            phone_4_digit= findViewById(R.id.phone4digit);
            transaction_4_digit= findViewById(R.id.transaction4digit);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void paymentInput(View view) {
        BackGroundPayment payment= new BackGroundPayment(getApplicationContext());
        try {
            last_four_digit_phn= phone_4_digit.getText().toString();
            transaction= transaction_4_digit.getText().toString();
            String msg= payment.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,driver_id,renter_id1,rate,date,last_four_digit_phn,transaction).get().toString();
            Toast.makeText(payYourBillDriver.this,msg,Toast.LENGTH_LONG).show();

            Intent intent= new Intent(payYourBillDriver.this,welcome_page.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
