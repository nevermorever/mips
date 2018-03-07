package com.zyjd.mips.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


public class ResponseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        //to be done
        return response;
    }
}