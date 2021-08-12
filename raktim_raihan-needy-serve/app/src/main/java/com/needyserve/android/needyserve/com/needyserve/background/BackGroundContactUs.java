package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.needyserve.android.needyserve.ContactUs;
import com.needyserve.android.needyserve.LoginActivity;

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
import java.net.URL;
import java.net.URLEncoder;

public class BackGroundContactUs extends AsyncTask<String, Void, Void> {
    private Context context;
    private String id, phone, name, email, message, type, url, json_string;
    private ProgressDialog progressBar = null;

    public BackGroundContactUs(Context context){
        this.context= context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/contactUs.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Sending your feedback....");
        progressBar.setTitle("Thanks for evaluating us.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.hide();
        if(message.equals("Error Occured")){
            AlertDialog.Builder window= new AlertDialog.Builder(context);
            window.setTitle("Error Occured.").setMessage("Please try after sometime").setPositiveButton("ok",null).show();
        }
        else{
            AlertDialog.Builder window= new AlertDialog.Builder(context);
            window.setTitle("Successfully send your query").setMessage("Thank you for your precious feedback. \nWe will " +
                    " rech you as soon as possible.").setPositiveButton("ok",null).show();
        }
    }

    @Override
    protected Void doInBackground(String... strings) {

        name = strings[0];
        email = strings[1];
        phone = strings[2];
        type = strings[3];
        message = strings[4];
        id = strings[5];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                    +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+URLEncoder.encode("type","UTF-8")+"="+URLEncoder.encode(type,"UTF-8")+"&"+
                    URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(message,"UTF-8")+"&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            out.close();

            InputStream input = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input,"UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            while((json_string=bufferedReader.readLine())!=null){
                stringBuilder.append(json_string+"\n");
            }
            json_string = stringBuilder.toString().trim();
            bufferedReader.close();
            input.close();
            httpURLConnection.disconnect();

            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count=0;

            while(count<jsonArray.length()){
                JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                message = jsonObject1.getString("message").trim();
                count++;
            }



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
