package com.helpiez.app.volley.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.helpiez.app.volley.FeedManager;

public class CustomImageView extends NetworkImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindImage(String url) {
        FeedManager.getInstance().bindImage(this, url);
    }
}
