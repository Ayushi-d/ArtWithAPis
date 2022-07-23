package com.example.art_stationary.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.art_stationary.R;


public class PreferenceHelper {
    private static final String TAG = "PreferenceHelper";
    private static PreferenceHelper helper;
    private static SharedPreferences preference;
    private static Context mContext;


    public static PreferenceHelper getInstance(Context context) {

        if (helper == null) {
            helper = new PreferenceHelper();
            preference = context.getSharedPreferences(context.getString(R.string.prefName), Context.MODE_PRIVATE);

        }
        mContext = context;
        return helper;
    }

    public void setid(String value) {
        preference.edit().putString(mContext.getString(R.string.prefuserid), value).apply();
    }

    public String getid() {
        return preference.getString(mContext.getString(R.string.prefuserid), mContext.getString(R.string.prefDefaultValue));
    }

    public void setusername(String value) {
        preference.edit().putString(mContext.getString(R.string.prefName), value).apply();
    }

    public String getusername() {
        return preference.getString(mContext.getString(R.string.prefName),"");
    }

    public void setPropertyid(String value) {
        preference.edit().putString(mContext.getString(R.string.prefpropertyid), value).apply();
    }

    public String getPropertyid() {
        return preference.getString(mContext.getString(R.string.prefpropertyid), mContext.getString(R.string.prefDefaultValue));
    }
    ///frndd
    public void setfriendid(String value) {
        preference.edit().putString(mContext.getString(R.string.preffriendid), value).apply();
    }

    public String getfriendid() {
        return preference.getString(mContext.getString(R.string.preffriendid), mContext.getString(R.string.prefDefaultValue));
    }

    public void setToken(String value) {
        preference.edit().putString(mContext.getString(R.string.preftoken), value).apply();
    }

    public String getToken() {
        Log.d(TAG, "getToken"+preference);
        return preference.getString(mContext.getString(R.string.preftoken),"");
    }


    public void setemail(String value) {
        preference.edit().putString(mContext.getString(R.string.prefemail), value).apply();
    }

    public String getemail() {
        return preference.getString(mContext.getString(R.string.prefemail), "");
    }


    public void setProfileImage(String profileImage)
    {
        preference.edit().putString(mContext.getString(R.string.prefProfileImage),profileImage);
    }

    public String getProfileImage(String profileImage)
    {
        return preference.getString(mContext.getString(R.string.prefProfileImage),"");
    }




    //google acc details
    public void setgoogleemail(String value) {
        preference.edit().putString(mContext.getString(R.string.prefgoogleemail), value).apply();
    }

    public String getgoogleemail() {
        return preference.getString(mContext.getString(R.string.prefgoogleemail), "");
    }

    public void setgoogleImage(String profileImage)
    {
        preference.edit().putString(mContext.getString(R.string.prefgoogleImage),profileImage);
    }

    public String getgoogleImage()
    {
        return preference.getString(mContext.getString(R.string.prefgoogleImage),"");
    }

    public void setgoogleid(String value) {
        preference.edit().putString(mContext.getString(R.string.prefgoogleid), value).apply();
    }

    public String getgoogleid() {
        return preference.getString(mContext.getString(R.string.prefgoogleid), "");
    }

    public void setgooglename(String value) {
        preference.edit().putString(mContext.getString(R.string.prefgooglename), value).apply();

    }

    public String getgooglename() {
        return preference.getString(mContext.getString(R.string.prefgooglename),"");
    }

    //fb
    public void setfbemail(String value) {
        preference.edit().putString(mContext.getString(R.string.preffbemail), value).apply();
    }

    public String getfbemail() {
        return preference.getString(mContext.getString(R.string.preffbemail), "");
    }

    public void setfbImage(String profileImage)
    {
        preference.edit().putString(mContext.getString(R.string.preffbImage),profileImage);
    }

    public String getfbImage()
    {
        return preference.getString(mContext.getString(R.string.preffbImage),"");
    }

    public void setfbid(String value) {
        preference.edit().putString(mContext.getString(R.string.preffbid), value).apply();
    }

    public String getfbid() {
        return preference.getString(mContext.getString(R.string.preffbid), "");
    }

    public void setfbname(String value) {
        preference.edit().putString(mContext.getString(R.string.preffbname), value).apply();
    }

    public String getfbname() {
        return preference.getString(mContext.getString(R.string.preffbname),"");
    }

}
