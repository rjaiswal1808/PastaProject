package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by admin on 21/03/16.
 */
public class EventsList extends BusinessObject {

    @SerializedName("ok")
    private int status;

    @SerializedName("event_detail")
    private ArrayList<EventDetail> eventDetail;

    public int getStatus() {
        return status;
    }

    public ArrayList<EventDetail> getEventDetail() {
        return eventDetail;
    }
}
