package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 21/03/16.
 */
public class CreatedEvent extends BusinessObject {
    @SerializedName("ok")
    private int status;

    @SerializedName("event_id")
    private int id;

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}
