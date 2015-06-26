package com.xebialabs.xlrelease;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.Proxy;

class CompleteTaskRequest extends AsyncTask<String, Void, Void> {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String TAG = "CompleteTaskRequest";

    private TasksAdapter tasksAdapter;

    public CompleteTaskRequest(TasksAdapter tasksAdapter) {
        this.tasksAdapter = tasksAdapter;
    }

    protected Void doInBackground(String...tasks) {
        String taskId = tasks[0];
        OkHttpClient client = new OkHttpClient();
        client.setAuthenticator(new Authenticator() {

            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String credential = Credentials.basic(XlReleaseCredentials.INSTANCE.getLogin(), XlReleaseCredentials.INSTANCE.getPassword());
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });


        RequestBody body = RequestBody.create(JSON, "{\"text\": \"OK\"}");
        Request request = new Request.Builder()
                .url("https://dexter.xebialabs.com/xlrelease/tasks/" + taskId + "/complete")
                .post(body)
                .build();

        try {
            String completeResult = client.newCall(request).execute().body().string();
            Log.i(TAG, completeResult);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    protected void onPostExecute(Void param) {
        Log.i(TAG, "Task completed");
        new GetTasksRequest(tasksAdapter).execute();
    }
}
