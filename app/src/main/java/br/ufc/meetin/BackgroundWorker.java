package br.ufc.meetin;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String servidor_url = "http://192.168.88.81:8000/conn.py";

        try {
            switch (type) {
                case "login":
                    String username = params[1];
                    String password = params[2];
                    URL url = new URL(servidor_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setReadTimeout(20000);
                    httpURLConnection.setConnectTimeout(20000);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    int responseCode=httpURLConnection.getResponseCode(); // To Check for 200
                    Log.d("Teste", "Pegando resposta");
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        Log.d("Teste", "200 - OK");
                        BufferedReader in=new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer("");
                        String line="";
                        while((line = in.readLine()) != null) {
                            stringBuffer.append(line);
                            break;
                        }
                        in.close();
                        return stringBuffer.toString();
                    }
                    break;
                case "register":
                    break;
                default:
                    break;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
