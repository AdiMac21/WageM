package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Adrian on 12/11/2016.
 */

public class Categories implements Serializable {
    private ArrayList<Category> categories=new ArrayList<>();
    private Date createdDate=new Date();


    public Categories() {
        Category foodAndBeverage = new Category("Food&Beverage", "android.resource://com.example.adrian.wagem/drawable/french_fries");
        Category bills = new Category("Bills", "android.resource://com.example.adrian.wagem/drawable/atm");
        Category healthAndFitness = new Category("Health&Fitness", "android.resource://com.example.adrian.wagem/drawable/dumbbell");
        Category transportation = new Category("Transportation", "android.resource://com.example.adrian.wagem/drawable/taxi");
        Category gifts = new Category("Gifts", "android.resource://com.example.adrian.wagem/drawable/ticket");
        Category shopping = new Category("Shopping", "android.resource://com.example.adrian.wagem/drawable/shirt");
        Category entertainment = new Category("Entertainment", "android.resource://com.example.adrian.wagem/drawable/gamepad");
        categories.add(foodAndBeverage);
        categories.add(bills);
        categories.add(healthAndFitness);
        categories.add(transportation);
        categories.add(gifts);
        categories.add(shopping);
        categories.add(entertainment);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
}
