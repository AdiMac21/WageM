package com.example.adrian.wagem.Model;

/**
 * Created by Adrian on 12/10/2016.
 */

public class User {
    private String name;
    private int salary;
    private int day;
    private int remMon;

    public User(String name, int salary, int day) {
        this.name = name;
        this.salary = salary;
        this.day = day;

    }

    public int getRemMon() {
        return remMon;
    }

    public void setRemMon(int remMon) {
        this.remMon = remMon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
