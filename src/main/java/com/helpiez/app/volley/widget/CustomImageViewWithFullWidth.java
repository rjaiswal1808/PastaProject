package com.helpiez.app.volley.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;
import com.helpiez.app.volley.FeedManager;

public class CustomImageViewWithFullWidth extends NetworkImageView {

    public CustomImageViewWithFullWidth(Context context) {
        super(context);
    }

    public CustomImageViewWithFullWidth(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bindImage(String url) {
        FeedManager.getInstance().bindImage(this, url);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = heightMeasureSpec;
        if(getDrawable() != null && getDrawable().getIntrinsicWidth() != 0 && getDrawable().getIntrinsicWidth() != -1)
            height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}
