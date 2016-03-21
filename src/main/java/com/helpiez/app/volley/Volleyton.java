package com.helpiez.app.volley;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Dell on 2/4/2016.
 */
public class Volleyton {

    public static final String TAG = "Volleyton";

    public static final String REQUESTTAG = "VOLLEYTON";

    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;

    private static Volleyton sInstance = null;
    public static Volleyton createInstance(Context context) {
        if(sInstance == null) {
            sInstance = new Volleyton(context);
        }
        return sInstance;
    }

    public static Volleyton getInstance() {
        if(sInstance == null)
            throw new NullPointerException("Use Volleyton.createInstance(context) before Volleyton.getInstance()");

        return sInstance;
    }

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private Volleyton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mImageLoader = new ImageLoader(this.mRequestQueue,
                new LruBitmapCache());
    }

    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null)
            throw new NullPointerException("WTF");
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        if(mImageLoader == null)
            throw new NullPointerException("WTF");
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? REQUESTTAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, REQUESTTAG);
    }

    public void addToRequestQueue(StringRequest req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? REQUESTTAG : tag);
        getRequestQueue().add(req);
    }

    public void addToRequestQueue(StringRequest req) {
        addToRequestQueue(req, REQUESTTAG);
    }

    public void cancelPendingRequests() {
        cancelPendingRequests(REQUESTTAG);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void clearCache(String url){
        if (mRequestQueue != null) {
            mRequestQueue.getCache().clear();
        }
    }

    public void removeCache(String url){
        if (mRequestQueue != null) {
            mRequestQueue.getCache().remove(url);
        }
    }

    public void invalidateCache(String url){
        if (mRequestQueue != null) {
            mRequestQueue.getCache().invalidate(url, true);
        }
    }

    public void addStringRequest(String url, final Message reqMsg) {
        Log.d(TAG, "StringRequest - " + url);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        reqMsg.arg1 = Volleyton.SUCCESS;
                        reqMsg.obj = response;

                        reqMsg.sendToTarget();
                    }
                },
                new  Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reqMsg.arg1 = Volleyton.FAILURE;

                        reqMsg.sendToTarget();
                    }
                });
        Volleyton.getInstance().addToRequestQueue(request);
    }

    public void addJsonRequest(String url, final Message reqMsg) {
        Log.d(TAG, "JsonObjectRequest - " + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        reqMsg.arg1 = Volleyton.SUCCESS;
                        reqMsg.obj = response;

                        reqMsg.sendToTarget();
                    }
                },
                new  Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        reqMsg.arg1 = Volleyton.FAILURE;

                        reqMsg.sendToTarget();
                    }
                });
        Volleyton.getInstance().addToRequestQueue(request);
    }

}
