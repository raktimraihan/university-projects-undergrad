package background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.needyserveadmin.FoodItem;

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

public class BackgroundFetchAll extends AsyncTask<String, Void, Void> {

    private Context context;
    private String name, foodName, foodDescription, location, servingPerson, time, url, json_string, foodId, flag;
    ProgressDialog progressBar = null;
    private ArrayList<FoodItem> foodItemArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public BackgroundFetchAll(Context context, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, String flag) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.layoutManager= layoutManager;
        this.flag = flag;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(flag.equals("post")) {
            url = "http://android.needyserve.org/allitem.php";
        }
        else{
            url = "http://android.needyserve.org/collectitem.php";
        }

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
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new ExampleAdapter(foodItemArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected Void doInBackground(String... strings) {

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("");
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
