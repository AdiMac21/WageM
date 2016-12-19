package com.example.adrian.wagem.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Adrian on 12/19/2016.
 */

public class History implements Serializable {

    private ArrayList<Categories> history;

    public ArrayList<Categories> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Categories> history) {
        this.history = history;
    }
}
