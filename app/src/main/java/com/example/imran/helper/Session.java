package com.example.imran.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    Context _activity;
    public static final String PREFER_NAME = "odigia";
    final int PRIVATE_MODE = 0;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public Session(Context activity) {
        try {
            this._activity = activity;
            pref = _activity.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
            editor = pref.edit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createUserLoginSession(String profile, String id, String username, String firstname, String lastname, String mobile, String description, String city, String instagram, String twitter, String facebook, String linkedin, String youtube) {
        //editor.putBoolean(Constant.IS_USER_LOGIN, true);

        editor.commit();
    }
    public String getData(String id) {
        return pref.getString(id, "");
    }
//    public void setUserData(String profile, String id, String firstname,String lastname, String mobile, String password) {
//
//        editor.putString(Constant.USER_ID, id);
//        editor.putString(Constant.NAME, name);
//        editor.putString(Constant.EMAIL, email);
//        editor.putString(Constant.COUNTRY_CODE, country_code);
//        editor.putString(Constant.PROFILE, profile);
//        editor.putString(Constant.MOBILE, mobile);
//        editor.putString(Constant.BALANCE, balance);
//        editor.putString(Constant.REFERRAL_CODE, referral_code);
//        editor.putString(Constant.FRIEND_CODE, friends_code);
//        editor.putString(Constant.FCM_ID, fcm_id);
//        editor.putString(Constant.STATUS, status);
//        editor.commit();
//    }

    public void setBoolean(String id, boolean val) {
        editor.putBoolean(id, val);
        editor.commit();
    }

    public boolean getBoolean(String id) {
        return pref.getBoolean(id, false);
    }
}
