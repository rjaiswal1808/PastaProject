package com.helpiez.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 21/03/16.
 */
public class EventDetail extends BusinessObject{

    @SerializedName("nss_id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("about")
    private String about;

    @SerializedName("when")
    private String when;

    @SerializedName("where")
    private String where;

    @SerializedName("cause")
    private String cause;

    @SerializedName("reimbursement")
    private String reimbursement;

    @SerializedName("qual")
    private String qual;

    @SerializedName("req")
    private String req;

    @SerializedName("deadline")
    private String deadline;

    @SerializedName("ngo")
    private String ngo;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbout() {
        return about;
    }

    public String getWhen() {
        return when;
    }

    public String getWhere() {
        return where;
    }

    public String getCause() {
        return cause;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public String getQual() {
        return qual;
    }

    public String getReq() {
        return req;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getNgo() {
        return ngo;
    }
}
