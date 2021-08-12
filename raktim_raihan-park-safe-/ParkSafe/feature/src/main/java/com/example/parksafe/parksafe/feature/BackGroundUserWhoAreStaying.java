package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackGroundUserWhoAreStaying extends AsyncTask<String,Void,String> {

    private Context ctx;
    String json_string;

    BackGroundUserWhoAreStaying(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... params){

        String renter_id=params[0];
        String url= "http://raktimprova.apps19.com/PHP/userStaying.php";

        try {
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection) loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8");
            bffr.write(post_data);
            bffr.flush();
            out.close();
            bffr.close();

            InputStream inptStrm= http.getInputStream();
            BufferedReader bfrrdr= new BufferedReader(new InputStreamReader(inptStrm));
            StringBuilder strBldr= new StringBuilder();

            while((json_string=bfrrdr.readLine())!=null){
                strBldr.append(json_string+"\n");
            }
            json_string=strBldr.toString().trim();
            Log.d("d_string",json_string);

            bfrrdr.close();
            inptStrm.close();
            http.disconnect();


            return json_string;


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
