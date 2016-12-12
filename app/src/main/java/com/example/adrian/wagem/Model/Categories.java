package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Adrian on 12/11/2016.
 */

public class Categories implements Serializable {
    private ArrayList<Category> categories=new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
