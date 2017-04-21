package com.android_examples.autoimageslider_android_examplescom;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public  String info="";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClickSearch(View view) {
        //Hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        EditText myText = (EditText) findViewById(R.id.tagTextId);
        info = myText.getText().toString();

        DownloadTask task = new DownloadTask();

        progressDialog = new ProgressDialog( MainActivity.this);
        progressDialog.setMessage("Downloading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

         task.execute(JsonRequest.requestRawJson(info));
    }




    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char)data;
                    result +=current;
                    data = reader.read();
                }

                return result;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.cancel();
            Intent next = new Intent(getApplicationContext(), FullScreenActivity.class);
            next.putExtra("result", result);
            startActivity(next);
        }
    }

    }
