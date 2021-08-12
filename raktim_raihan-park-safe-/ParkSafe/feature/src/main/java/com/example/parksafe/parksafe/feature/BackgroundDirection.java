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

public class BackgroundDirection extends AsyncTask<String,Void,String> {
    private Context ctx;
    String json_string;

    BackgroundDirection(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        String renter_id=params[0];
        String url= "http://raktimprova.apps19.com/PHP/direction_lat_long.php";

        try {
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
            String longi="",latti="",address="";
            while(count<jArray.length()){
                JSONObject jo1= jArray.getJSONObject(count);
                longi=jo1.getString("longitude");
                latti=jo1.getString("lattitude");
                address=jo1.getString("address");
                count++;
            }

            return longi+";.;"+latti+";.;"+address;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
