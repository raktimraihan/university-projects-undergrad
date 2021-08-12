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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackGroundCheckAvailableSpace extends AsyncTask<String,Void,String> {
    private Context ctx;
    private String renter_id;
    private String json_string;

    BackGroundCheckAvailableSpace (Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(200);
            renter_id=params[0];

            String url= "http://raktimprova.apps19.com/PHP/checkspacename.php";
            URL loginurl= new URL(url);
            HttpURLConnection http= (HttpURLConnection)loginurl.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setDoInput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bfwr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("renter_id","UTF-8")+"="+URLEncoder.encode(renter_id,"UTF-8");
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
            String address="",space="",rate="";
            while(count<jArray.length()){
                JSONObject jo1= jArray.getJSONObject(count);
                address=jo1.getString("address");
                rate=jo1.getString("rate");
                space=jo1.getString("space");
                count++;
            }

            return address+";"+rate+";"+space;



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
