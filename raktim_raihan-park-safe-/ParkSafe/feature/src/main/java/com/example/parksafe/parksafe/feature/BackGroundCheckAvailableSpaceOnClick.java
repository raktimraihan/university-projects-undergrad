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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackGroundCheckAvailableSpaceOnClick extends AsyncTask<String, Void, String> {
    private Context ctx;
    private String renter_id,time,type,space;
    private String json_string;

    BackGroundCheckAvailableSpaceOnClick(Context ctx){
        this.ctx=ctx;
    }


    @Override
    protected String doInBackground(String... params) {
       renter_id=params[0];
       time=params[1];
       type=params[2];
       space=params[3];
       String url= "http://raktimprova.apps19.com/PHP/onclickSpaceUpdate.php";

       try{

           Thread.sleep(200);

           URL loginurl= new URL(url);
           HttpURLConnection http= (HttpURLConnection)loginurl.openConnection();
           http.setRequestMethod("POST");
           http.setDoOutput(true);
           http.setDoInput(true);

           OutputStream out= http.getOutputStream();
           BufferedWriter bfwr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
           String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8")+"&"
                            +URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"
                   +URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"
                   +URLEncoder.encode("space","UTF-8")+"="+URLEncoder.encode(space,"UTF-8");

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

       } catch (InterruptedException e) {
           e.printStackTrace();
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
