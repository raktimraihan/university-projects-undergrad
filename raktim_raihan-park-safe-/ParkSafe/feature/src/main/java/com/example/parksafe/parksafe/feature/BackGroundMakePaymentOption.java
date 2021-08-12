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

class BackGroundMakePaymentOption extends AsyncTask<String,Void,String> {
    private Context ctx;
    private String result;
    private String driver_id;
    BackGroundMakePaymentOption(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        driver_id=params[0];
        String url="http://raktimprova.apps19.com/PHP/makePaymentOption.php";

        try {
            URL sUrl= new URL(url);
            HttpURLConnection http= (HttpURLConnection) sUrl.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream out= http.getOutputStream();
            BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String post_data= URLEncoder.encode("driver_id","UTF-8")+"="+URLEncoder.encode(driver_id,"UTF-8");
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

            line=strbldr.toString().trim();
            result=line;

            JSONObject jsonObject= new JSONObject(result);
            JSONArray jsonArray= jsonObject.getJSONArray("response");
            int count=0;
            String renter_name="",start_time="",end_time="",rate="",phone="",renter_id="";
            while(count<jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                renter_name = jo.getString("name");
                phone = jo.getString("phone");
                rate = jo.getString("rate");
                start_time=jo.getString("start_time");
                end_time=jo.getString("end-time");
                renter_id=jo.getString("renter_id");
                count++;
            }

            return renter_name+";.;"+phone+";.;"+rate+";.;"+start_time+";.;"+end_time+";.;"+renter_id;

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
