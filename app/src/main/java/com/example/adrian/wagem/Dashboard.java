
package com.example.adrian.wagem;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.adrian.wagem.Adapters.CatGridAdapter;
import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.Model.Expense;
import com.example.adrian.wagem.Model.User;
import com.example.adrian.wagem.Util.GsonSave;

import static com.example.adrian.wagem.Util.LSCat.loadCat;

public class Dashboard extends AppCompatActivity {
    private static String MY_PREFS_NAME = "prefs";
    private final Context context = this;
    private User user;
    private Categories categories;
    private GridView gridView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("categories", null);
        if (restoredText == null) {
            Intent intent=new Intent(context,OneTime.class);
            startActivity(intent);
        }

        createUser();
        categories=loadCat(context);
        linkUi();
    }

    private void linkUi() {
        gridView = (GridView) findViewById(R.id.grid);
        CatGridAdapter adapter = new CatGridAdapter(Dashboard.this, categories);
        gridView.setAdapter(adapter);
        RoundCornerProgressBar progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressColor(Color.parseColor("#36282B"));
        progressBar.setBackgroundColor(Color.parseColor("#EAE9E9"));
        progressBar.setMax((float) user.getSalary());
        progressBar.setProgress((float) user.getRemMon());

    }


    private void createUser() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String name=prefs.getString("name", "No name defined");
        double salary=prefs.getFloat("salary", 0);
        int day = prefs.getInt("day", 1);
        user = new User(name, salary, day);
        user.setRemMon(prefs.getFloat("remMon", 0));

    }




}
