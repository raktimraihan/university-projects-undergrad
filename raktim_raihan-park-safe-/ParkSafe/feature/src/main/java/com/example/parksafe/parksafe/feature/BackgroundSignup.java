package com.example.parksafe.parksafe.feature;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

    class BackGroundSignup extends AsyncTask<String, Void, String> {
        Context ctx;
        Toast toast;
        String result1="";
        BackGroundSignup(Context ctx){
            this.ctx= ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            String type= params[5];
            String url= "http://raktimprova.apps19.com/PHP/signup.php";

            if(type.equals("signup")){
                String userName= params[0];
                String passWord= params[3];
                String phone= params[1];
                String email= params[2];
                String registratioNumber= params[4];


                try {
                    URL loginurl= new URL(url);
                    HttpURLConnection http= (HttpURLConnection) loginurl.openConnection();
                    http.setRequestMethod("POST");
                    http.setDoInput(true);
                    http.setDoOutput(true);

                    OutputStream out= http.getOutputStream();
                    BufferedWriter bffr= new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                    String post_data= URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
                            +URLEncoder.encode("passWord","UTF-8")+"="+URLEncoder.encode(passWord,"UTF-8")+"&"
                            +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("registratioNumber","UTF-8")+"="+URLEncoder.encode(registratioNumber,"UTF-8");

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
        protected void onPostExecute(String s) {
            toast= Toast.makeText(ctx,result1,Toast.LENGTH_LONG);
            toast.show();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }


