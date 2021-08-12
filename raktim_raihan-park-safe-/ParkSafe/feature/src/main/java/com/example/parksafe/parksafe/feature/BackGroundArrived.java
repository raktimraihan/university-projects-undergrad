package com.example.parksafe.parksafe.feature;

import android.content.ContentProvider;
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

public class BackGroundArrived extends AsyncTask<String, Void, String> {
    private Context ctx;
    private String renter_id;
    private String driver_id;


    BackGroundArrived(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        renter_id= params[0];
        driver_id=params[1];

        //String url= "http://raktimprova.apps19.com/PHP/arrival.php";
        try {
            String url= "http://raktimprova.apps19.com/PHP/arrival.php";
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection)loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bfwr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8")+"&"
                    +URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8");

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

            return "";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
