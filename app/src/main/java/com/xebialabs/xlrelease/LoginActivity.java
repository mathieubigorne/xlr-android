package com.xebialabs.xlrelease;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void onLoginButtonClick(View view) {
        final String login = ((TextView) findViewById(R.id.loginTextField)).getText().toString();
        final String password = ((TextView) findViewById(R.id.passwordTextField)).getText().toString();

        new AsyncTask<Void, Void, Boolean>() {
            protected Boolean doInBackground(Void... params) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://dexter.xebialabs.com/xlrelease/profile")
                        .header("Authorization", Credentials.basic(login, password))
                        .get()
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    Log.i(TAG, "Profile request returned code " + response.code());
                    return response.code() == 200;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            protected void onPostExecute(Boolean isAuthenticated) {
                Log.i(TAG, "isAuthenticated ? " + isAuthenticated);

                if (isAuthenticated) {
                    XlReleaseCredentials.INSTANCE.setLogin(login);
                    XlReleaseCredentials.INSTANCE.setPassword(password);
                    Intent intent = new Intent(getApplicationContext(), MyTasksActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
