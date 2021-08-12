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

public class BackGroundGoOffline extends AsyncTask<String,Void,Void> {

    private Context ctx;
    private  String driver_id,renter_id;

    BackGroundGoOffline(Context ctx){
        this.ctx=ctx;
    }


    @Override
    protected Void doInBackground(String... params) {
        driver_id=params[0];
        renter_id=params[1];
        String url= "http://raktimprova.apps19.com/PHP/goOffline.php";
        try {
            URL lurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection) lurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8")+"&"
                    +URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8");
            bffr.write(post_data);
            bffr.flush();
            out.close();
            bffr.close();

            InputStream inptStrm= http.getInputStream();
            BufferedReader bfrrdr= new BufferedReader(new InputStreamReader(inptStrm));
            StringBuilder strBldr= new StringBuilder();

            bfrrdr.close();
            inptStrm.close();
            http.disconnect();


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
