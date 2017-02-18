package com.example.adrian.wagem.Model;

import java.io.Serializable;

/**
 * Created by Adrian on 12/10/2016.
 */

public class User implements Serializable {
    private String name;
    private long salary;
    private int day;
    private long remMon;
    private long totalMoney;

    public User(String name, long salary, int day) {
        this.name = name;
        this.salary = salary;
        this.day = day;


    }

    public long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getRemMon() {
        return remMon;
    }

    public void setRemMon(long remMon) {
        this.remMon = remMon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
