package com.example.adrian.wagem.Model;

import java.io.Serializable;

/**
 * Created by Adrian on 12/10/2016.
 */

public class User implements Serializable {
    private String name;
    private double salary;
    private int day;
    private double remMon;
    private double totalMoney;

    public User(String name, double salary, int day) {
        this.name = name;
        this.salary = salary;
        this.day = day;


    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getRemMon() {
        return remMon;
    }

    public void setRemMon(double remMon) {
        this.remMon = remMon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
