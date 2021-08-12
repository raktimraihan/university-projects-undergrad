package background;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.needyserveadmin.FoodItem;
import com.example.needyserveadmin.VolunteerInformation;

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

 public class BackGroundVolunteerRequestAccept extends AsyncTask<String, Void, Void> {
    private Context context;
    private String volId,volname, volEmail, foodDescription, location, servingPerson, time, volPhone,volGender, json_string,url;
    private int position;
    ProgressDialog progressBar = null;
    private ArrayList<VolunteerInformation> volunteerInformations = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String id, name, email, phone, gender, donor_status;

    public BackGroundVolunteerRequestAccept(Context context, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, String id, String name, String email, String phone, String gender, String donor_status) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.layoutManager= layoutManager;
        this.id=id;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.gender=gender;
        this.donor_status=donor_status;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/volunteerRequestAccept.php";

        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Fetching Data....");
        progressBar.setTitle("Volunteer List.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        progressBar.hide();
        try {
            Toast.makeText(context,"Connection Successful!  ", Toast.LENGTH_LONG).show();
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter = new AdapterForVolunteerReqAccept(volunteerInformations,context,id);
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
                volId = jsonObject1.getString("id");
                volname = jsonObject1.getString("name").trim();
                volEmail = jsonObject1.getString("email").trim();
                volPhone = jsonObject1.getString("phone").trim();
                volGender = jsonObject1.getString("gender").trim();
                volunteerInformations.add(new VolunteerInformation(volId,volname,volEmail,volPhone,volGender,"0"));
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
