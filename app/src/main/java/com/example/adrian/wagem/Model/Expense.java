package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Adrian on 12/11/2016.
 */

public class Expense implements Serializable {
    private String name;
    private long cost;
    private Date date=new Date();


    public Expense(String name, long cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
