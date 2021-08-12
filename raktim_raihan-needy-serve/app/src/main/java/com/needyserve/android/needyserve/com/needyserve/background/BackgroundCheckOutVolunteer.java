package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

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

public class BackgroundCheckOutVolunteer extends AsyncTask<String, Void, String> {
    private Context context;
    ProgressDialog progressBar = null;
    private String url, foodId, json_string, name, email, phone, message;
    private TextView nameT, phoneT, emailT;

    public BackgroundCheckOutVolunteer(Context context, TextView nameT, TextView phoneT, TextView emailT){
        this.context=context;
        this.nameT= nameT;
        this.emailT= emailT;
        this.phoneT = phoneT;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        url = "http://android.needyserve.org/checkout.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Checking database....");
        progressBar.setTitle("Fetching Data.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.hide();
        nameT.setText("Name: "+name);
        emailT.setText("Email: "+email);
        phoneT.setText("Phone No: "+phone);

    }

    @Override
    protected String doInBackground(String... strings) {
        foodId=strings[0];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("foodId","UTF-8")+"="+URLEncoder.encode(foodId,"UTF-8");
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
            int count = 0;
            while (count < 1) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                name = jsonObject1.getString("name").trim();
                email = jsonObject1.getString("email").trim();
                phone = jsonObject1.getString("phone").trim();
                message = jsonObject1.getString("message");

                count++;
            }
            return message;

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
