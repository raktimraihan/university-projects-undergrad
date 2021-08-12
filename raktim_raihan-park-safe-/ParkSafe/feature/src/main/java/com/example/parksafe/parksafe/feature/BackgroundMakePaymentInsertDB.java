package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

public class BackgroundMakePaymentInsertDB extends AsyncTask<String, Void, Void> {
    private Context ctx;
    private String driver_id, renter_id, fare, date, last4phone,last4trans;


    BackgroundMakePaymentInsertDB(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected Void doInBackground(String... params) {
        driver_id=params[0];
        renter_id=params[1];
        fare=params[2];
        date=params[3];
        last4phone=params[4];
        last4trans=params[5];

        String url= "http://raktimprova.apps19.com/PHP/makePaymentDBinsert.php";

        try {
            URL inserURl= new URL(url);
            HttpURLConnection http= (HttpURLConnection) inserURl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8")+"&"
                    +URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8")+"&"
                    +URLEncoder.encode("fare","UTF-8")+"="+URLEncoder.encode(fare,"UTF-8")+"&"
                    +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                    +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(last4phone,"UTF-8")+"&"
                    +URLEncoder.encode("transaction","UTF-8")+"="+URLEncoder.encode(last4trans,"UTF-8");

            bffr.write(post_data);
            bffr.flush();
            out.close();
            bffr.close();

            InputStream inpt= http.getInputStream();
            BufferedReader bfrdr= new BufferedReader(new InputStreamReader(inpt,"iso-8859-1"));
            StringBuilder strbldr= new StringBuilder();
            String line="";
            while((line=bfrdr.readLine())!=null){
                strbldr.append(line+"\n");
            }

            bfrdr.close();
            inpt.close();
            http.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Toast.makeText(ctx,"Please wait untill renter varifies your bill.",Toast.LENGTH_LONG);
    }
}
