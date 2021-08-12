package com.example.parksafe.parksafe.feature;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundDriverLogin extends AsyncTask<String, Void, String> {

    Context ctx;
    AlertDialog alrt;
    Toast toast;
    private String result1="";
    static final int  ACCESS_FINE_LOCATION=1;

    BackgroundDriverLogin(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type= params[0];
        String url= "http://raktimprova.apps19.com/PHP/login_driver.php";

        if(type.equals("driverLoginPage")){
            String userName= params[1];
            String passWord= params[2];
            try {
                URL loginurl= new URL(url);
                HttpURLConnection http= (HttpURLConnection) loginurl.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream out= http.getOutputStream();
                BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String post_data= URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                        +URLEncoder.encode("passWord","UTF-8")+"="+URLEncoder.encode(passWord,"UTF-8");
                bffr.write(post_data);
                bffr.flush();
                out.close();
                bffr.close();



                InputStream inpt= http.getInputStream();
                BufferedReader bfrdr= new BufferedReader(new InputStreamReader(inpt,"iso-8859-1"));
                StringBuilder strbldr= new StringBuilder();
                String line="";
                String result="";
                while((line=bfrdr.readLine())!=null){
                    strbldr.append(line+"\n");
                }
                bfrdr.close();
                inpt.close();
                http.disconnect();

                line=strbldr.toString().trim();
                result1=line;
                setResult1(result1);
                return line;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        Activity activity= (Activity) ctx;
        loginDriver lgn= new loginDriver();
        lgn.str1=result1;
        try {
            JSONObject jsonObject= new JSONObject(result1);
            JSONArray jsonArray= jsonObject.getJSONArray("response");
            int count=0;
            String driver_id="",message="",name="";
            while(count<jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                driver_id = jo.getString("driver_id");
                name = jo.getString("driver_name");
                message = jo.getString("message");
                count++;
            }
        if(!name.equals("")) {
            toast = Toast.makeText(ctx, name, Toast.LENGTH_LONG);
            toast.show();
        }
        ActivityCompat.requestPermissions(activity, new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION },
                ACCESS_FINE_LOCATION);


        if(message.contains("Please pay.")){
            Intent intent= new Intent(ctx,makePaymentOption.class);
            intent.putExtra("driver_id",driver_id);
            ctx.startActivity(intent);
        }
        if(message.contains("welcome")){
                Intent intr = new Intent(ctx, MapsActivity.class);
                Log.d("driver id",driver_id);
                intr.putExtra("driver_id",driver_id);
                ctx.startActivity(intr);
        }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void setResult1(String s){
        this.result1=s;
    }

}
