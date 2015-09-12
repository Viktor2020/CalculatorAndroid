package com.example.ex2015_07_26.listeners;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

import java.util.Random;

public class ProgressBarTask extends AsyncTask<Void, Integer, Void> {
    private int progressStatus;
    private ProgressBar bar;

    public void setProgressBar(ProgressBar bar) {
        this.bar = bar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressStatus = 0;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while (progressStatus < bar.getMax()) {
            progressStatus += new Random().nextInt(80) + 10;
            publishProgress(progressStatus);
            SystemClock.sleep(100);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.bar != null) {
            bar.setProgress(values[0]);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        bar.setProgress(0);
    }
}
