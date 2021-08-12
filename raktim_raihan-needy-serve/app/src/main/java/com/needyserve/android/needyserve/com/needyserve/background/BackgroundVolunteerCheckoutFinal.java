package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.needyserve.android.needyserve.finallyEnd;

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

public class BackgroundVolunteerCheckoutFinal extends AsyncTask<String, Void, String> {

    private Context context;
    private String url, json_string, foodId, fName, fdescription, fserv, ftime, flocation, message,volunteerID;
    private ProgressDialog progressBar = null;

    public BackgroundVolunteerCheckoutFinal(Context context){
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/checkoutfinal.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Checking out....");
        progressBar.setTitle("Registering.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.hide();
        if(message.equals("Error Occured")){
            AlertDialog.Builder window= new AlertDialog.Builder(context);
            window.setTitle("Error Occured.").setMessage("Please try again").setPositiveButton("ok",null).show();
        }
        else{
                Toast.makeText(context,"Successfully added!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, finallyEnd.class);
                context.startActivity(intent);
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        foodId = strings[0];
        fName = strings[1];
        fdescription = strings[2];
        fserv = strings[3];
        ftime = strings[4];
        flocation = strings[5];
        volunteerID = strings[6];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(foodId,"UTF-8")+"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(fName,"UTF-8")+"&"
                    +URLEncoder.encode("des","UTF-8")+"="+URLEncoder.encode(fdescription,"UTF-8")+"&"+URLEncoder.encode("serv","UTF-8")+"="+URLEncoder.encode(fserv,"UTF-8")+"&"+
                    URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(ftime,"UTF-8")+"&"+URLEncoder.encode("location","UTF-8")+"="+URLEncoder.encode(flocation,"UTF-8")
                    +"&"+URLEncoder.encode("volID","UTF-8")+"="+URLEncoder.encode(volunteerID,"UTF-8");
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
            return null;


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
