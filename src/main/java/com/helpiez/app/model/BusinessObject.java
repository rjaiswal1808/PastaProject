package com.helpiez.app.model;

import com.android.volley.VolleyError;

import java.io.Serializable;
import java.util.ArrayList;

public class BusinessObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private String emptyMsg;

    public String getEmptyMsg() {
        return emptyMsg;
    }

    public ArrayList<?> getArrListBusinessObject() {
        return null;
    }

    private VolleyError volleyError;

    public void setEmptyMsg(String emptyMsg) {
        this.emptyMsg = emptyMsg;
    }

    public VolleyError getVolleyError() {
        return volleyError;
    }

    public void setVolleyError(VolleyError volleyError) {
        this.volleyError = volleyError;
    }
}
