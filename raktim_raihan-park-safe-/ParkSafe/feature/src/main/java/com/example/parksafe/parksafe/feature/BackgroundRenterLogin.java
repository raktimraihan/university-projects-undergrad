package com.example.parksafe.parksafe.feature;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundRenterLogin extends AsyncTask<String, Void, String> {
    Context ctx;
    Toast toast;
    String result1="";

    BackgroundRenterLogin(Context ctx){
        this.ctx=ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type= params[0];
        String url= "http://raktimprova.apps19.com/PHP/login_renter.php";

        if(type.equals("renterLoginPage")){
            String userName= params[1];
            String passWord= params[2];
            String access = params[3];
            try {
                URL loginurl= new URL(url);
                HttpURLConnection http= (HttpURLConnection) loginurl.openConnection();
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                OutputStream out= http.getOutputStream();
                BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String post_data= URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                        +URLEncoder.encode("passWord","UTF-8")+"="+URLEncoder.encode(passWord,"UTF-8")+"&"+
                        URLEncoder.encode("access","UTF-8")+"="+URLEncoder.encode(access,"UTF-8");
                bffr.write(post_data);
                bffr.flush();
                out.close();
                bffr.close();

                InputStream inpt= http.getInputStream();
                BufferedReader bfrdr= new BufferedReader(new InputStreamReader(inpt,"iso-8859-1"));
                String line="";
                String result="";
                while((line=bfrdr.readLine())!=null){
                    result+=line;
                }
                bfrdr.close();
                inpt.close();
                http.disconnect();
                result1=result1+result;
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {

        if(result1.contains("Welcome")){
            String[] array= result1.split(";.;");
            toast= Toast.makeText(ctx,array[0],Toast.LENGTH_LONG);
            toast.show();

            Intent itn=new Intent(ctx,renterLanding.class);
            itn.putExtra("renter_id",array[1]);
            itn.putExtra("time",array[2]);
            itn.putExtra("status",array[3]);
            Log.d("renter id",array[1]);
            ctx.startActivity(itn);

        }
        else{
            Toast.makeText(ctx,"Wrong User id or Password.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
