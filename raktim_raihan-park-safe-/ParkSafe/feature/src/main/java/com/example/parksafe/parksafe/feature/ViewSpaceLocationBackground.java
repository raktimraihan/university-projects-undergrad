package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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



public class ViewSpaceLocationBackground extends AsyncTask<String,Void,String> {
    private Context ctx;
    String renter_id="";
    private String name,phone,email,address,rate;


    ViewSpaceLocationBackground(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        int value= Integer.valueOf(params[0]);
        renter_id= String.valueOf(value);
        String url= "http://raktimprova.apps19.com/PHP/viewspace.php";

        try {
            Thread.sleep(100);
            URL renterInormationUrl= new URL(url);
            HttpURLConnection http= (HttpURLConnection)renterInormationUrl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bwrtr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8");
            bwrtr.write(post_data);
            bwrtr.flush();
            out.close();
            bwrtr.close();

            String line="";
            InputStream inpt= http.getInputStream();
            BufferedReader bfrdr= new BufferedReader(new InputStreamReader(inpt,"iso-8859-1"));
            StringBuilder strbldr= new StringBuilder();
            while((line=bfrdr.readLine())!=null){
                strbldr.append(line+"\n");
            }
            line=strbldr.toString().trim();

            JSONObject json_object= new JSONObject(line);
            JSONArray json_array= json_object.getJSONArray("response_information");
            int count=0;
            while(count<json_array.length()) {
                JSONObject jo = json_array.getJSONObject(count);
                name = jo.getString("name");
                phone = jo.getString("phone");
                email = jo.getString("email");
                address=jo.getString("address");
                rate=jo.getString("rate");
                count++;
            }

            return name+";"+phone+";"+email+";"+address+";"+rate;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
