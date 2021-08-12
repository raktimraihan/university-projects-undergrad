package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.needyserve.android.needyserve.LoginActivity;
import com.needyserve.android.needyserve.Register;

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

public class LoginBackGround extends AsyncTask<String, Void, String>  {

    private Context context = null;
    private String email, name, phone, password, gender, url,json_string, message;
    ProgressDialog progressBar = null;

    public LoginBackGround(Context ctx){
        this.context=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/register.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("User is Registering....");
        progressBar.setTitle("Sign Up.");
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
            window.setTitle("Error Occured.").setMessage("Please try with different Mail address").setPositiveButton("ok",null).show();
        }
        else{
            try {
                Toast.makeText(context,"User is registered!", Toast.LENGTH_LONG).show();
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        email = strings[1];
        name = strings[0];
        phone = strings[2];
        password = strings[3];
        gender = strings[4];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
            +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                    URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8");
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
            return message;


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
