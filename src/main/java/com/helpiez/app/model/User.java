package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumeet.sinha on 20/03/16.
 */
public class User extends BusinessObject {

    @SerializedName("ok")
    private int status;

    @SerializedName("username")
    private String userName;

    @SerializedName("session_id")
    private String sessionId;

    public int getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public String getSessionId() {
        return sessionId;
    }
}
