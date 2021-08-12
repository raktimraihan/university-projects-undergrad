package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

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

public class BackGroundRegDonate extends AsyncTask<String, Void, Void> {

    private Context context;
    ProgressDialog progressBar = null;
    String url, F,S,T,L,Se, id, json_string,message;

    public BackGroundRegDonate(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        S = strings[1];
        F = strings[0];
        T = strings[2];
        L = strings[3];
        Se = strings[4];
        id = strings[5];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(F,"UTF-8")+"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(S,"UTF-8")+"&"
                    +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(T,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(L,"UTF-8")+"&"+
                    URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(Se,"UTF-8")+"&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/donateRegFood.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Donation is Registering....");
        progressBar.setTitle("Making Visible to others.");
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
            window.setTitle("Error Occured.").setMessage("Please try again").setPositiveButton("ok",null).show();
        }
        else{
            try {
                Toast.makeText(context,"Successfully added!", Toast.LENGTH_LONG).show();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Intent intent = new Intent(context, LoginActivity.class);
            //context.startActivity(intent);
        }
    }
}
