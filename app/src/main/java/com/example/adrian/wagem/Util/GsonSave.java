package com.example.adrian.wagem.Util;

import com.example.adrian.wagem.Model.Categories;
import com.google.gson.Gson;

/**
 * Created by Adrian on 12/14/2016.
 */

public class GsonSave {


    public static String serializeToJson(Categories myClass) {
        Gson gson = new Gson();
        String j = gson.toJson(myClass);
        return j;
    }

    public static Categories deserializeFromJson(String jsonString) {
        Gson gson = new Gson();
        Categories myClass = gson.fromJson(jsonString, Categories.class);
        return myClass;
    }

}
