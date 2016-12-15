package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Adrian on 12/11/2016.
 */

public class Category implements Serializable {
    private String name;
    private String iconName;
    private ArrayList<Expense> items=new ArrayList<>();
    private double sum=0;

    public Category(String name, String iconName) {
        this.name = name;
        this.iconName = iconName;
    }

    public String getName() {
        return name;
    }


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public ArrayList<Expense> getItems() {
        return items;
    }

    public void setItems(ArrayList<Expense> items) {
        this.items = items;
    }
}
