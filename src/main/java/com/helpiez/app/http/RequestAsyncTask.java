package com.helpiez.app.http;

import android.os.AsyncTask;
import android.os.Message;

/**
 * Created by sanjeev.b1 on 1/25/2016.
 */
public class RequestAsyncTask extends AsyncTask<Request, Void, Void> {

    private OkHttp mOkHttp;
    private Message mMsg;

    @Override
    protected Void doInBackground(Request... params) {
        Request request = params[0];
        mMsg = request.getMessage();

        String json = mOkHttp.getRequest(request.getUrl());
        mMsg.arg1 = (json != null) ? Request.Result.SUCCESS : Request.Result.FAILURE;
        mMsg.obj = json;

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        mOkHttp = OkHttp.getInstance();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mMsg.sendToTarget();
    }

}
