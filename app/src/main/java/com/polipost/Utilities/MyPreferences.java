package com.polipost.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.polipost.R;

public class MyPreferences {
    private SharedPreferences mSharedPrefrences;
    private Context context;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";


    public MyPreferences(Context context) {
        this.mSharedPrefrences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
        this.context = context;
    }


    public void writeLoginStatus(boolean status) {

        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.apply();
    }
    public boolean readLoginStatus() {

        return mSharedPrefrences.getBoolean(context.getString(R.string.pref_login_status), false);
    }


    public void writeUserName(String name) {
        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putString(context.getString(R.string.pref_user_name), name);
        editor.apply();
    }
    public String readUserName() {
        return mSharedPrefrences.getString(context.getString(R.string.pref_user_name), "Guest");
    }


    public void writeUserEmail(String email) {
        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putString(context.getString(R.string.pref_user_email), email);
        editor.apply();
    }
    public String readUserEmail() {
        return mSharedPrefrences.getString(context.getString(R.string.pref_user_email), "Guest");
    }

    public void writeUserId(String userId) {
        SharedPreferences.Editor editor = mSharedPrefrences.edit();
        editor.putString(context.getString(R.string.pref_user_id), userId);
        editor.apply();
    }
    public String readUserId() {
        return mSharedPrefrences.getString(context.getString(R.string.pref_user_id), "Guest");
    }

}
