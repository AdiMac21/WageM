package com.example.adrian.wagem.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Adrian on 12/15/2016.
 */

public class LoadSave {
    private static String MY_PREFS_NAME = "prefs";

    public static void saveCat(Context context, Categories categories) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("categories", GsonSave.serializeToJson(categories));
        editor.commit();
    }

    public static Categories loadCat(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String categories = prefs.getString("categories", "No name defined");
        return GsonSave.deserializeFromJson(categories);
    }

    public static User loadUser(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String name = prefs.getString("name", "No name defined");
        double salary = prefs.getFloat("salary", 0);
        int day = prefs.getInt("day", 1);
        User user = new User(name, salary, day);
        user.setRemMon(prefs.getFloat("remMon", 0));
        user.setTotalMoney(prefs.getFloat("totalMoney",0));
        return user;
    }
}
