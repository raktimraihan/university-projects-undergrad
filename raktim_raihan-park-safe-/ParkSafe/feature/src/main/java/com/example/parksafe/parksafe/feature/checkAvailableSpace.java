package com.example.parksafe.parksafe.feature;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class checkAvailableSpace extends AppCompatActivity {

    private int record;
    private String type="";
    private String renter_id;
    private String message;
    private String address;
    private String rate;
    private String space;
    private String space_set;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_check_available_space);

        Intent itn=getIntent();
        renter_id= itn.getStringExtra("renter_id");

        edt= findViewById(R.id.editText2);

        Spinner spn= findViewById(R.id.spinner);
        String[] item= {"1 hour","2 hour", "3 hour", "4 hour", "5 hour", "6 hour"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(checkAvailableSpace.this,android.R.layout.simple_spinner_dropdown_item,item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0: record= 1;
                        break;
                    case 1: record= 2;
                        break;
                    case 2: record=3;
                        break;
                    case 3: record= 4;
                        break;
                    case 4: record= 5;
                        break;
                    case 5: record=6;
                        break;

                    default: record=1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                record=1;
            }
        });


        Spinner spn2= findViewById(R.id.spinner2);
        String[] item2= {"Car", "Bike", "Both"};
        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(checkAvailableSpace.this,android.R.layout.simple_spinner_dropdown_item,item2);
        spn2.setAdapter(arrayAdapter2);

        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: type="Car";
                    break;
                    case 1: type="Bike";
                    break;
                    case 2: type= "Both";
                    break;
                    default: type="Car";
                    break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type="Car";
            }
        });

        BackGroundCheckAvailableSpace bkchk= new BackGroundCheckAvailableSpace(getApplicationContext());

        try {
            message=bkchk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,renter_id).get().toString();
            String[] array= message.split(";");
            address=array[0];
            rate=array[1];
            space=array[2];

            TextView addrs= findViewById(R.id.address_location);
            addrs.setText("Location: "+address);
            TextView rat= findViewById(R.id.rate);
            rat.setText("Current rate: "+rate+" taka per 30 minute stay.");
            TextView spac=findViewById(R.id.spaceNow);
            spac.setText("Currently "+space+" spaces in up for rent.");





        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void processUpdate(View view) {
        String msg;
        space_set=edt.getText().toString();
        String time=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()+record*3600*1000));
        Log.d("timeNow",time);
        BackGroundCheckAvailableSpaceOnClick bclck=new BackGroundCheckAvailableSpaceOnClick(getApplicationContext());
        try {
            msg=bclck.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,renter_id,time,type,space_set).get().toString();
            Log.d("massageee",msg);

            String[] array= msg.split(";");
            Toast.makeText(checkAvailableSpace.this,"Location Online till: "+array[1].substring(11),Toast.LENGTH_LONG).show();

            Intent itn=new Intent(checkAvailableSpace.this,renterLanding.class);
            itn.putExtra("renter_id",renter_id);
            itn.putExtra("time",time);
            itn.putExtra("status","1");
            startActivity(itn);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void userParkNowList(View view) {


    }
}
