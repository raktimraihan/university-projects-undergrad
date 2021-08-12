package com.needyserve.android.needyserve.com.needyserve.background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.needyserve.android.needyserve.com.needyserve.android.instances.ExampleAdapter;
import com.needyserve.android.needyserve.com.needyserve.android.instances.FoodItem;

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
import java.util.ArrayList;

public class MyDonationFetchAll extends AsyncTask<String, Void, Void> {

    private Context context;
    private String name, foodName, foodDescription, location, servingPerson, time, url, json_string,id, foodId;
    ProgressDialog progressBar = null;
    private ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public MyDonationFetchAll(Context context, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.layoutManager= layoutManager;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/mydonation.php";

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Fetching Data....");
        progressBar.setTitle("Food List.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressBar.hide();
        try {
            Toast.makeText(context,"Connection Successful!  ", Toast.LENGTH_LONG).show();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ExampleAdapter(foodItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected Void doInBackground(String... strings) {
        id=strings[0];
        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            while (count < jsonArray.length()) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                foodId = jsonObject1.getString("id");
                name = jsonObject1.getString("name").trim();
                foodName = jsonObject1.getString("food_name").trim();
                foodDescription = jsonObject1.getString("description").trim();
                location = jsonObject1.getString("location").trim();
                servingPerson = jsonObject1.getString("serving_person").trim();
                time = jsonObject1.getString("time");
                foodItemArrayList.add(new FoodItem(name,foodName,foodDescription,location,servingPerson,time, foodId));
                count++;
            }
            return null;

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
