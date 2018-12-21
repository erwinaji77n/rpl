package com.justfriend.suratuin.Model.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.justfriend.suratuin.DrawerActivity;
import com.justfriend.suratuin.MainActivity;

import java.util.HashMap;

public class UserSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;
    public static final String PREFER_NAME = "Reg";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_NAME = "Name";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "txtPassword";

    public UserSession(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String token){
        editor.putBoolean(IS_USER_LOGIN, true);

        editor.putString(KEY_NAME, token);

        editor.commit();
    }

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){

            Intent i = new Intent(context, MainActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(i);

            return true;
        }
        return false;
    }

    public HashMap<String, String> getUserDetails(){

        HashMap<String, String> user = new HashMap<String, String>();

        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        return user;
    }
    public String getnama(){
        return pref.getString(KEY_NAME,"");
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

}