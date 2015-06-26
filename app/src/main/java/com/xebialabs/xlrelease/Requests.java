package com.xebialabs.xlrelease;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.xebialabs.xlrelease.views.ReleaseTasks;
import com.xebialabs.xlrelease.views.TaskFullView;
import com.xebialabs.xlrelease.views.TaskSearchView;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

class Requests extends AsyncTask<String, Void, List<Task>> {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String TAG = "Requests";

    private TaskAdapter tasksAdapter;

    public Requests(TaskAdapter tasksAdapter) {
        this.tasksAdapter = tasksAdapter;
    }

    protected List<Task> doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        client.setAuthenticator(new Authenticator() {

            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String credential = Credentials.basic("flegall", "techrally");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });
        RequestBody body = RequestBody.create(JSON, "{\"active\":true,\"assignedToMe\":true,\"assignedToMyTeams\":false,\"assignedToOthers\":false,\"notAssigned\":false,\"filter\":\"\"}");
        Request request = new Request.Builder()
                .url("https://dexter.xebialabs.com/xlrelease/tasks/search?limitTasksHint=100")
                .post(body)
                .build();

        try {
            String json = client.newCall(request).execute().body().string();
            TaskSearchView taskSearchView = new Gson().fromJson(json, TaskSearchView.class);


            List<Task> tasks = newArrayList();
            for (ReleaseTasks releaseTasks : taskSearchView.getReleaseTasks()) {
                for (TaskFullView taskFullView : releaseTasks.getTasks()) {
                    tasks.add(new Task(taskFullView.getId(), taskFullView.getTitle(), taskFullView.getStatus()));
                }
            }

            return tasks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void onPostExecute(List<Task> tasks) {
        Log.i(TAG, tasks.toString());

        tasksAdapter.addAll(tasks);
    }
}
