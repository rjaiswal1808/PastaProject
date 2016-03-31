package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumeet.sinha on 20/03/16.
 */
public class User extends BusinessObject {

    @SerializedName("ok")
    private int status;

    @SerializedName("session_id")
    private String sessionId;

    @SerializedName("user")
    private UserDetail userDetail;

    public int getStatus() {
        return status;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public String getSessionId() {
        return sessionId;
    }
}
