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

public class BackgroundBillVerification extends AsyncTask<String,Void,String> {

    Context ctx;
    private String json_string="";
    BackgroundBillVerification(Context ctx){
        this.ctx=ctx;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String driver_id=params[0];
        String url= "http://raktimprova.apps19.com/PHP/background_bill_verfication.php";

        try {
            Thread.sleep(200);
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection)loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bfwr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8");
            bfwr.write(post_data);
            bfwr.flush();
            out.close();
            bfwr.close();

            InputStream inptStrm= http.getInputStream();
            BufferedReader bfrrdr= new BufferedReader(new InputStreamReader(inptStrm));
            StringBuilder strBldr= new StringBuilder();

            while((json_string=bfrrdr.readLine())!=null){
                strBldr.append(json_string+"\n");
            }
            json_string=strBldr.toString().trim();

            bfrrdr.close();
            inptStrm.close();
            http.disconnect();

            JSONObject jo= new JSONObject(json_string);
            JSONArray jArray= jo.getJSONArray("response_information");
            int count=0;
            String name="",phone="",email="",rate="",date="",renter_id="";

            while(count<jArray.length()){
                JSONObject jo1= jArray.getJSONObject(count);
                name=jo1.getString("name");
                phone=jo1.getString("phone");
                email=jo1.getString("email");
                rate= jo1.getString("rate");
                date=jo1.getString("date");
                renter_id=jo1.getString("renter_id");
                count++;
            }



            return name+","+phone+","+email+","+rate+","+date+","+renter_id;

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
}

