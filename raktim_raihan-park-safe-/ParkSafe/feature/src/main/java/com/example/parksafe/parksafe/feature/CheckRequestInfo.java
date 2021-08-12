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

public class CheckRequestInfo extends AsyncTask<String,Void,String> {
    private Context ctx;


    CheckRequestInfo(Context ctx){ this.ctx=ctx; }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String driver_id= params[0];
        String renter_id=params[1];
        String rate23= params[2];
        String timeStamp23=params[3];
        int final_rate=Integer.parseInt(rate23);
        String url= "http://raktimprova.apps19.com/PHP/check_request_table.php";

        try {
            Thread.sleep(200);
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection) loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("rate23","UTF-8")+"="+URLEncoder.encode(rate23,"UTF-8")+"&"
                            +URLEncoder.encode("timestamp23","UTF-8")+"="+URLEncoder.encode(timeStamp23,"UTF-8")+"&"
                            +URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8")+"&"
                            +URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8");

            bffr.write(post_data);
            bffr.flush();
            out.close();
            bffr.close();

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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
