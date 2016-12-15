package com.example.adrian.wagem.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.adrian.wagem.Model.Categories;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Adrian on 12/15/2016.
 */

public  class LSCat {
    private static String MY_PREFS_NAME = "prefs";

    public static void saveCat(Context context, Categories categories){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("categories",GsonSave.serializeToJson(categories));
        editor.commit();
    }
    public static Categories loadCat(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String categories = prefs.getString("categories", "No name defined");
        return GsonSave.deserializeFromJson(categories);
    }
}
