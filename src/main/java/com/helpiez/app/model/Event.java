package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumeet.sinha on 20/03/16.
 */
public class Event extends BusinessObject{

    @SerializedName("ok")
    private int status;

    @SerializedName("event_detail")
    private EventDetail eventDetail;

    public int getStatus() {
        return status;
    }

    public EventDetail getEventDetail() {
        return eventDetail;
    }
}
