package com.example.parksafe.parksafe.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class userWhoAreStaying extends AppCompatActivity {
    String renter_id;
    String json_string;
    String[] namevreg;
    ArrayList<String> arrList=new ArrayList<>();
    ArrayList<String> driver_id_list=new ArrayList<>();
    ArrayList<String> driver_phone_list= new ArrayList<>();
    ArrayList<String> driver_mail_list= new ArrayList<>();
    ArrayList<String> driver_reg_list=new ArrayList<>();
    ArrayList<String> driver_time= new ArrayList<>();
    ArrayList<String> ratelist= new ArrayList<>();
    ArrayList<String> driver_name=new ArrayList<>();
    private String status,time;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_user_who_are_staying);


        String[] array= {"asd","asd","asd","asd"};
        Intent itn= getIntent();
        renter_id= itn.getStringExtra("renter_id");
        status=itn.getStringExtra("status");
        time=itn.getStringExtra("time");
        BackGroundUserWhoAreStaying bksty= new BackGroundUserWhoAreStaying(getApplicationContext());
        try {
            json_string=bksty.execute(renter_id).get().toString();

            JSONObject jo= new JSONObject(json_string);
            JSONArray jArray= jo.getJSONArray("response");
            int count=0;
            String address="",space="",rate="";
            while(count<jArray.length()){
                JSONObject jo1= jArray.getJSONObject(count);
                String str =jo1.getString("driver_name")+".- Registration: "+jo1.getString("driver_reg");
                driver_name.add(jo1.getString("driver_name"));
                driver_id_list.add(jo1.getString("driver_id"));
                driver_phone_list.add(jo1.getString("driver_phone"));
                driver_mail_list.add(jo1.getString("driver_mail"));
                driver_reg_list.add(jo1.getString("driver_reg"));
                driver_time.add(jo1.getString("startTime"));
                ratelist.add(jo1.getString("rate"));
               arrList.add(str);
                count++;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        namevreg=arrList.toArray(new String[arrList.size()]);
        ListView lstvw= findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(userWhoAreStaying.this,android.R.layout.simple_list_item_1,namevreg);
        lstvw.setAdapter(arrayAdapter);

        lstvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(userWhoAreStaying.this,String.valueOf(position),Toast.LENGTH_LONG).show();

                Intent intn= new Intent(userWhoAreStaying.this,bill.class);
                intn.putStringArrayListExtra("driver_id_list",driver_id_list);
                intn.putStringArrayListExtra("driver_phone_list",driver_phone_list);
                intn.putStringArrayListExtra("driver_mail_list",driver_mail_list);
                intn.putStringArrayListExtra("driver_reg_list",driver_reg_list);
                intn.putStringArrayListExtra("rate",ratelist);
                intn.putStringArrayListExtra("StarTtime",driver_time);
                intn.putExtra("position",String.valueOf(position));
                intn.putExtra("renter_id",renter_id);
                intn.putStringArrayListExtra("driver_name_list",driver_name);
                intn.putExtra("status",status);
                intn.putExtra("time",time);

                startActivity(intn);

            }
        });
    }
}
