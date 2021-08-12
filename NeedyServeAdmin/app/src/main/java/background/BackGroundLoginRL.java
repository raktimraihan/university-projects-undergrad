package background;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.needyserveadmin.AdminOptions;

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

public class BackGroundLoginRL extends AsyncTask<String,Void,String> {

    private Context context = null;
    private String email, name, phone, password, gender, url,json_string, message="", id, donor_status="";
    ProgressDialog progressBar = null;

    public BackGroundLoginRL(Context ctx){
        this.context=ctx;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        url = "http://android.needyserve.org/loginAdmin.php";
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Checking database....");
        progressBar.setTitle("Sign In.");
        progressBar.setIndeterminate(false);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressBar.hide();
        if(message.equals("Failed")){
            AlertDialog.Builder window= new AlertDialog.Builder(context);
            window.setTitle("Login Failed.").setMessage("Please try Again").setPositiveButton("ok",null).show();
        }
        else{
            try {
                Toast.makeText(context,"Welcome "+name, Toast.LENGTH_LONG).show();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(context, AdminOptions.class);
            intent.putExtra("id",id);
            intent.putExtra("email", email);
            intent.putExtra("name",name);
            intent.putExtra("phone",phone);
            intent.putExtra("gender",gender);
            intent.putExtra("donor_status",donor_status);
            context.startActivity(intent);
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        email = strings[0];
        password = strings[1];

        try {
            URL url_register = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url_register.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream out= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
            String postData = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
                id = jsonObject1.getString("id").trim();
                name = jsonObject1.getString("name").trim();
                email = jsonObject1.getString("email").trim();
                phone = jsonObject1.getString("phone").trim();
                gender = jsonObject1.getString("gender").trim();
                message = jsonObject1.getString("message");
                donor_status = jsonObject1.getString("donor_status");

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
