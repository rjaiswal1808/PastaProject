package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 24/03/16.
 */
public class UserDetail extends BusinessObject {

    @SerializedName("email")
    private String email;

    @SerializedName("nss_id")
    private String nssId;

    public String getEmail() {
        return email;
    }

    public String getNssId() {
        return nssId;
    }
}
