package com.example.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void, Integer,String> {
    private static final String TAG = "MyTask";
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;

    public MyTask(Context context, TextView textView, Button button) {
        this.context = context;
        this.textView = textView;
        this.button = button;
    }

    @Override
    protected String doInBackground(Void... voids) {
        int i = 0;
        synchronized (this){
            while(i<10){
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    Log.d(TAG, "-----"+e);
                    e.printStackTrace();
                }
            }
        }
        return "Download Finish.";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Download in Progress...");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        textView.setText(result);
        button.setEnabled(true);
        progressDialog.hide();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Dowload in Progress.");
    }
}
