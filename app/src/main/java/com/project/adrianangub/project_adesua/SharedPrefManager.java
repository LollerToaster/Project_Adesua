package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 06/05/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

//here for this class we are using a singleton pattern
public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "userSharedPreference";
    private static final String KEY_UID = "keyUid";
    private static final String KEY_DESC = "keyDesc";
    private static final String KEY_META = "keyMeta";
    private static final String KEY_STAT = "keyDesc";
    private static final String KEY_SCHOOLID = "keySchoolId";
    private static final String KEY_SCHOOLNAME = "keySchoolName";
    private static final String KEY_SCHOOLADDRESS = "keySchoolAddress";
    private static final String KEY_SCHOOLACRYN = "keySchoolAcryn";
    private static final String KEY_SCHOOLNUM = "keySchoolNum";
    private static final String KEY_FULLNAME = "keyFullName";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void sharedResponse(Users user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_UID, user.getUid());
        editor.putString(KEY_DESC, user.getDesc());
        editor.putString(KEY_META, user.getMeta());
        editor.putString(KEY_STAT, user.getStat());
        editor.putString(KEY_SCHOOLID, user.getSchoolid());
        editor.putString(KEY_SCHOOLNAME, user.getSchoolname());
        editor.putString(KEY_SCHOOLADDRESS, user.getSchooladdress());
        editor.putString(KEY_SCHOOLACRYN, user.getSchoolacryn());
        editor.putString(KEY_SCHOOLNUM, user.getSchoolnum());
        editor.putString(KEY_FULLNAME, user.getFullname());
        //editor.putString("imagepath","/sdcard/imh.jpeg");
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_UID, null) != null;
    }

    //this method will give the logged in user
    public Users getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Users(
                //sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_UID, null),
                sharedPreferences.getString(KEY_DESC, null),
                sharedPreferences.getString(KEY_META, null),
                sharedPreferences.getString(KEY_STAT, null),
                sharedPreferences.getString(KEY_SCHOOLID, null),
                sharedPreferences.getString(KEY_SCHOOLNAME, null),
                sharedPreferences.getString(KEY_SCHOOLADDRESS, null),
                sharedPreferences.getString(KEY_SCHOOLACRYN, null),
                sharedPreferences.getString(KEY_SCHOOLNUM, null),
                sharedPreferences.getString(KEY_FULLNAME, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}