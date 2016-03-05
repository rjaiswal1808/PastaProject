package com.helpiez.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ParseClassName("opportunity")
public class NGOEvent extends ParseObject implements Serializable {

    final static String EVENT_NAME = "name";
    final static String EVENT_TIME = "when";
    final static String EVENT_IMAGE = "event_image";
    final static String EVENT_LOCATION_ADDRESS = "where";
    final static String EVENT_LAT = "event_lat";
    final static String EVENT_LON = "event_lon";
    final static String EVENT_TYPE = "event_type";
    final static String EVENT_CAUSE = "cause";
    final static String EVENT_DESCRIPTION = "about";
    final static String EVENT_OWNER = "owner";
    final static String EVENT_REIMBURSEMENT = "reimbursement";
    final static String EVENT_QUALIFICATION = "qualification";
    final static String EVENT_REQUIREMENT = "requirement";
    final static String EVENT_DEADLINE = "deadline";

    public NGOEvent() {
        // A default constructor is required.
    }

    public String getEventName() {
        return getString(EVENT_NAME);
    }

    public void setEventName(String eventName) {
        put(EVENT_NAME, eventName);
    }

    public String getEventTime() {
        return getString(EVENT_TIME);
    }

    public void setEventTime(String timeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(convertedCurrentDate != null)
            put(EVENT_TIME, convertedCurrentDate);
    }

    public String getEventLocationAddress() {
        return getString(EVENT_LOCATION_ADDRESS);
    }


    public void setEventLocationAddress(String address) {
        put(EVENT_LOCATION_ADDRESS, address);
    }

//    public LatLng getEventLocation() {
//        return new LatLng(getDouble(EVENT_LAT), getDouble(EVENT_LON));
//    }
//
//    public void setEventLocation(LatLng latLng) {
//        put(EVENT_LAT, latLng.latitude);
//        put(EVENT_LON, latLng.longitude);
//    }

    public void setEventType(String eventType) {
        put(EVENT_TYPE, eventType);
    }

    public String getEventType() {
        return getString(EVENT_TYPE);
    }

    public void setEventCause(String eventCause) {
        put(EVENT_CAUSE, eventCause);
    }

    public String getEventCause() {
        return getString(EVENT_CAUSE);
    }

    public void setEventDescription(String cause) {
        put(EVENT_DESCRIPTION, cause);
    }

    public String getEventDescription() {
        return getString(EVENT_DESCRIPTION);
    }


    public ParseUser getOwner() {
        return getParseUser(EVENT_OWNER);
    }

    public void setOwner(ParseUser user) {
        put(EVENT_OWNER, user);
    }

    public ParseFile getImage() {
        return getParseFile(EVENT_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(EVENT_IMAGE, parseFile);
    }

    public String getEventDeadline() {
        return getString(EVENT_DEADLINE);
    }

    public void setEventDeadline( String timeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        Date convertedCurrentDate = null;
        try {
            convertedCurrentDate = sdf.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(convertedCurrentDate != null)
            put(EVENT_DEADLINE, convertedCurrentDate);
    }
}
