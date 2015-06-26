package com.xebialabs.xlrelease.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xebialabs.xlrelease.R;
import com.xebialabs.xlrelease.XLReleaseApplication;
import com.xebialabs.xlrelease.core.api.XLReleaseCredentials;
import com.xebialabs.xlrelease.core.api.XLReleaseApiService;
import com.xebialabs.xlrelease.ui.NavigationActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";

    @InjectView(R.id.login)
    EditText loginEditText;
    @InjectView(R.id.password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
    }

    public void onLoginButtonClick(View view) {
        final String login = loginEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        XLReleaseCredentials.INSTANCE.setLogin(login);
        XLReleaseCredentials.INSTANCE.setPassword(password);

        XLReleaseApiService xlReleaseApiService = XLReleaseApplication.getApiService();
        xlReleaseApiService.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response>() {
                               @Override
                               public void call(Response response) {
                                   if (response.getStatus() == 200) {
                                       Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                                       startActivity(intent);
                                   } else {
                                       Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                        });

    }
}
