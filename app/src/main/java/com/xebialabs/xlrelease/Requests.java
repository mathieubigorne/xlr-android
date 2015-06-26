package com.xebialabs.xlrelease;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;

class Requests extends AsyncTask<String, Void, String> {
    private static final String TAG = "Requests";

    private Exception exception;

    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://dexter.xebialabs.com/xlrelease/server/info")
                .build();

        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String requestResult) {
        Log.i(TAG, requestResult);
    }
}
