package com.helpiez.app.http;

import android.os.Message;

/**
 * Created by sanjeev.b1 on 1/25/2016.
 */
public class Request {

    public static class Result {
        public static final int SUCCESS = 0;
        public static final int FAILURE = 1;
    }

    private String mURL;
    private Message mMsg;

    public Request(String url, Message msg) {
        mURL = url;
        mMsg = msg;
    }

    public String getUrl() {
        return mURL;
    }

    public Message getMessage() {
        return mMsg;
    }

    public void execute() {
        new RequestAsyncTask().execute(this);
    }
}
