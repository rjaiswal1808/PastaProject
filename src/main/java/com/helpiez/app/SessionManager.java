package com.helpiez.app;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 20/03/16.
 */
public class SessionManager {
    private static final String KEY_VALUE = "com.helpiez.app.";
    private static final String SESSION_ID = KEY_VALUE + "session_id";
    private static final String USER_TYPE = KEY_VALUE + "user_type";
    private static final String USER_ID = KEY_VALUE + "user_id";
    private static final String NSS_ID = KEY_VALUE + "nss_id";

    public static void setSessionId(Context context, String device_id) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SESSION_ID, device_id);
        editor.apply();
    }

    public static String getSessionId(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(SESSION_ID, "NA");
    }

    public static void setUserType(Context context, String user_type) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_TYPE, user_type);
        editor.apply();
    }

    public static String getUserType(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(USER_TYPE, null);
    }

    public static void setUserId(Context context, String user_type) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_ID, user_type);
        editor.apply();
    }

    public static String getUserId(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(USER_ID, null);
    }

    public static void setNssId(Context context, String user_type) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(NSS_ID, user_type);
        editor.apply();
    }

    public static String getNssId(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.getString(NSS_ID, null);
    }

    //Shared Prefrences Instance
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(KEY_VALUE, Context.MODE_PRIVATE);
    }
}
