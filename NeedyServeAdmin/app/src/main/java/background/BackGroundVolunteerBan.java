package background;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.needyserveadmin.VolunteerRequestAccept;

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

public class BackGroundVolunteerBan extends AsyncTask<String, Void, String> {
    private Context context;
    private String id;
    ProgressDialog progressBar = null;
    private String url, foodId, json_string, name, email, phone, message;
    public BackGroundVolunteerBan(Context context, String id) {
        super();
        this.context = context;
        this.id = id;
    }

    @Override
    protected String doInBackground(String... strings) {
        //foodId = strings[0];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("Id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/banVolunteer.php";
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
        if(message.equals("Success")) {
            Toast.makeText(context, "User is registered as Volunteer!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, VolunteerRequestAccept.class);
            intent.putExtra("id", id);
            intent.putExtra("email", email);
            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("gender", "");
            intent.putExtra("donor_status", "");
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context, "User can't be registered as Volunteer! Try again Later.", Toast.LENGTH_SHORT).show();
        }
    }
}
