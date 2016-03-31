package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 25/03/16.
 */
public class ApplyResponse extends BusinessObject {

    @SerializedName("ok")
    private int status;

    public int getStatus() {
        return status;
    }
}
