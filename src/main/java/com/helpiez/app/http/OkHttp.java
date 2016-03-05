package com.helpiez.app.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by sanjeev.b1 on 1/25/2016.
 */
public class OkHttp {
    private OkHttp() {}
    private static OkHttp sInstance;
    public static OkHttp getInstance() {
        if(sInstance == null) {
            sInstance = new OkHttp();
        }
        return sInstance;
    }

    OkHttpClient client = new OkHttpClient();
    public String getRequest(String url) {
        String data = null;
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            data = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        }
        return data;
    }
}
