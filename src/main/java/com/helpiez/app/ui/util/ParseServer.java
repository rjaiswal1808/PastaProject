package com.helpiez.app.ui.util;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.helpiez.app.model.NGOEvent;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by admin on 25/01/16.
 */
public class ParseServer {

    public static final String TAG = "ParseServer";

    public static void getProjects(final Context context, final Message msg) {
        ParseQuery<NGOEvent> query = ParseQuery.getQuery(NGOEvent.class);

        query.findInBackground(new FindCallback<NGOEvent>() {

            @Override
            public void done(List<NGOEvent> itemList, ParseException e) {
                if (e == null) {
                    msg.obj = itemList;
                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                msg.sendToTarget();
            }
        });
    }
}