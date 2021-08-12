package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackGroundPayment extends AsyncTask<String, Void, String> {
    private Context ctx;
    String renter_id,driver_id,rate,date,phone,transaction;

    BackGroundPayment(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String ... params) {

        renter_id=params[1];
        driver_id = params[0];
        rate= params[2];
        date=params[3];
        phone=params[4];
        transaction=params[5];


        try{
            String url= "http://raktimprova.apps19.com/PHP/payment.php";
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection)loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bfwr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8")+"&"
                    +URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8")+"&"
                    +URLEncoder.encode("rate","UTF-8")+"="+URLEncoder.encode(rate,"UTF-8")+"&"
                    +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                    +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                    +URLEncoder.encode("trans","UTF-8")+"="+URLEncoder.encode(transaction,"UTF-8");

            bfwr.write(post_data);
            bfwr.flush();
            out.close();
            bfwr.close();

            InputStream inpt= http.getInputStream();
            BufferedReader bfrdr= new BufferedReader(new InputStreamReader(inpt,"iso-8859-1"));
            String line="";
            String result="";
            while((line=bfrdr.readLine())!=null){
                result+=line;
            }
            bfrdr.close();
            inpt.close();
            http.disconnect();

            return result;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
