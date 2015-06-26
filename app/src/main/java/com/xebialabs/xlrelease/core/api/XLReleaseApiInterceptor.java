package com.xebialabs.xlrelease.core.api;

import com.squareup.okhttp.Credentials;

import retrofit.RequestInterceptor;

public class XLReleaseApiInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        String credential = Credentials.basic(XLReleaseCredentials.INSTANCE.getLogin(), XLReleaseCredentials.INSTANCE.getPassword());

        request.addHeader("Authorization", credential);
    }
}
