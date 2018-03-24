package com.tobefit.tobefit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Admin on 24.3.2018.
 */

public class Sessions {
    private static final String DATE_TEMPLATE = "dd/MM/yyyy";

    Context c;
    public Sessions(Context c) {
        super();
        this.c = c;

    }

    public void AddNowDate(Context c){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();

        String date=String.format("%s", formatDate(DATE_TEMPLATE, new Date(System.currentTimeMillis())));
            editor.putString("NowDate",date );


        editor.commit();
    }

    public void updateEasyMode(int update_value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();


        editor.putString("EastMode",update_value+"" );


        editor.commit();
    }

    public int getEasyMode(){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();

        String EastMode =preferences.getString("EastMode", "-1");
        return Integer.parseInt(EastMode);
    }
    public void addEasyMode(int add_value){
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = preferences.edit();

        String EastMode =preferences.getString("EastMode", "-1");

        int addvalue  = Integer.parseInt(EastMode)+add_value;

        editor.putString("EastMode",addvalue+"" );


        editor.commit();
    }

    private String formatDate(@NonNull String dateTemplate, @NonNull Date date) {
        return new SimpleDateFormat(dateTemplate, Locale.getDefault()).format(date);
    }

}
