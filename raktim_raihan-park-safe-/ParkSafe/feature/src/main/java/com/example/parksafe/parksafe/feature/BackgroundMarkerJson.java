package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class BackgroundMarkerJson extends AsyncTask<Void, Void, String> {
    Context ctx;
    String json_string;
     String json_url;

    public BackgroundMarkerJson(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        json_url= "http://raktimprova.apps19.com/PHP/location.php";

    }
     @Override
     protected String doInBackground(Void... voids) {
        json_string="";
         try {
             URL url = new URL(json_url);
             HttpURLConnection http= (HttpURLConnection) url.openConnection();
             InputStream inpt= http.getInputStream();
             BufferedReader bffr= new BufferedReader(new InputStreamReader(inpt));
             StringBuilder sttr= new StringBuilder();

             while((json_string=bffr.readLine())!=null){
                 //json_string= json_string+bffr.readLine();
                sttr.append(json_string+"\n");
             }

             bffr.close();
             inpt.close();
             http.disconnect();

             json_string=sttr.toString().trim();
             return sttr.toString().trim();
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return json_string;

     }


    @Override
    protected void onPostExecute(String aVoid) {
        //Toast.makeText(ctx.getApplicationContext(),aVoid,Toast.LENGTH_LONG).show();
        //setjson(aVoid);
        /*JSONObject json_object= null;
        try {
            json_object = new JSONObject(json_string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray json_array= null;
        try {
            json_array = json_object.getJSONArray("response");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int count = 0;
        String renter_id,address,lat,longitu,rate;
        renterMarkerInformation rntr= new renterMarkerInformation();
        try {
        while(count<json_array.length()){
            JSONObject jo= null;

                jo = json_array.getJSONObject(count);
                renter_id= jo.getString("renter_id");
                address= jo.getString("address");
                lat= jo.getString("latitude");
                longitu= jo.getString("longitute");
                rate=jo.getString("rateperhour");
            rntr.setRenter_id(renter_id);
            rntr.setAddress(address);
            rntr.setLattitude(Double.parseDouble(lat));
            rntr.setLongitude(Double.parseDouble(longitu));
            rntr.setRate(Integer.parseInt(rate));
            count++;
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    public void setjson(String json){
        new MapsActivity().setjson(json);
    }
}
