package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adrian on 12/11/2016.
 */

public class Expense implements Serializable {
    private String name;
    private float cost;
    private Date date=new Date();


    public Expense(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
