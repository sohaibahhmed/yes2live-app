package com.fyp.yes2live;

import android.content.Context;
import android.content.SharedPreferences;

import com.fyp.yes2live.response.Payload;


public class SharedPreferenceManager {

    private static String SHARED_PREF_NAME="usersession";
    private SharedPreferences sharedPreference;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        this.context=context;
    }

    public void saveUser(Payload user){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putInt("id",user.getUserId());
        editor.putString("name",user.getName());
        editor.putString("email",user.getEmail());
        //for loggedin status... if loggedIn return true
        editor.putBoolean("logged",true);
        editor.apply();
    }
}