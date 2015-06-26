package com.xebialabs.xlrelease;

import android.app.Application;

import com.xebialabs.xlrelease.core.api.XLReleaseApiInterceptor;
import com.xebialabs.xlrelease.core.api.XLReleaseApiService;

import retrofit.RestAdapter;

import static retrofit.RestAdapter.LogLevel.BASIC;

public class XLReleaseApplication extends Application {

    private static XLReleaseApiService xlReleaseApiService = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (xlReleaseApiService == null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("https://dexter.xebialabs.com/xlrelease")
                    .setLogLevel(BASIC)
                    .setRequestInterceptor(new XLReleaseApiInterceptor())
                    .build();

            xlReleaseApiService = restAdapter.create(XLReleaseApiService.class);
        }

    }

    public static XLReleaseApiService getApiService(){
        return xlReleaseApiService;
    }
}
