package com.fyp.yes2live;

import android.content.Context;
import android.content.SharedPreferences;

import com.fyp.yes2live.model.User;

public class SharedPreferenceManager {
    private static String SHARED_PREF_NAME="usersession";
    private SharedPreferences sharedPreference;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(Context context) {
        this.context=context;
    }

    public void saveUser(User user){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putLong("id",user.getId());
        editor.putString("name",user.getName());
        editor.putString("email",user.getEmail());
        //for loggedin status... if loggedIn return true
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public User getUser(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreference.getLong("id",-1),
                sharedPreference.getString("name",null),
                sharedPreference.getString("email",null),
                sharedPreference.getFloat("weight",0),
                sharedPreference.getFloat("height",0)

        );
    }

    public String getDate(){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String dateHistory=sharedPreference.getString("date",null);
        return dateHistory;
    }

    public Throwable saveDate(String date){
        sharedPreference=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreference.edit();
        editor.putString("date",date);
        editor.apply();
        return null;
    }
}
