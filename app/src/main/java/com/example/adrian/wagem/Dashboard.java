
package com.example.adrian.wagem;

import android.app.Dialog;
import android.content.Context;
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

public class Dashboard extends AppCompatActivity {
    private String MY_PREFS_NAME = "prefs";
    private final Context context = this;
    private TextView name;
    private TextView salary;
    private TextView day;
    private User user;
    private Button button;
    private Dialog dialog;
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
            createDialog();
        }

        createUser();
        categories=loadCat();
        linkUi();
    }

    private void linkUi() {
        gridView = (GridView) findViewById(R.id.grid);
        CatGridAdapter adapter = new CatGridAdapter(Dashboard.this, categories);
        gridView.setAdapter(adapter);
        RoundCornerProgressBar progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressColor(Color.parseColor("#36282B"));
        progressBar.setBackgroundColor(Color.parseColor("#EAE9E9"));
        progressBar.setMax(user.getSalary());
        progressBar.setProgress(user.getRemMon());

    }

    private void generateCategories() {
        categories = new Categories();
        Category foodAndBeverage = new Category("Food&Beverage", "android.resource://com.example.adrian.wagem/drawable/french_fries");
        Category bills = new Category("Bills", "android.resource://com.example.adrian.wagem/drawable/atm");
        Category healthAndFitness = new Category("Health&Fitness", "android.resource://com.example.adrian.wagem/drawable/dumbbell");
        Category transportation = new Category("Transportation", "android.resource://com.example.adrian.wagem/drawable/taxi");
        Category gifts = new Category("Gifts", "android.resource://com.example.adrian.wagem/drawable/ticket");
        Category shopping = new Category("Shopping", "android.resource://com.example.adrian.wagem/drawable/shirt");
        Category entertainment = new Category("Entertainment", "android.resource://com.example.adrian.wagem/drawable/gamepad");
        categories.getCategories().add(foodAndBeverage);
        categories.getCategories().add(bills);
        categories.getCategories().add(healthAndFitness);
        categories.getCategories().add(transportation);
        categories.getCategories().add(gifts);
        categories.getCategories().add(shopping);
        categories.getCategories().add(entertainment);
        saveCat();

    }

    private void createUser() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String name = prefs.getString("name", "No name defined");
        int salary = prefs.getInt("salary", 0);
        int day = prefs.getInt("day", 1);
        user = new User(name, salary, day);
        user.setRemMon(prefs.getInt("remMon", 0));

    }

    private void createDialog() {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.prim_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setTitle("Info");
        name = (TextView) dialog.findViewById(R.id.name_tf);
        salary = (TextView) dialog.findViewById(R.id.salary_tf);
        day = (TextView) dialog.findViewById(R.id.day_tf);
        button = (Button) dialog.findViewById(R.id.button_prim);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
                generateCategories();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void saveCat(){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("categories",GsonSave.serializeToJson(categories));
        editor.commit();
    }
    private Categories loadCat(){
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String categories = prefs.getString("categories", "No name defined");
        return GsonSave.deserializeFromJson(categories);
    }
    private void saveUser(){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", name.getText().toString());
        editor.putInt("salary", Integer.parseInt(salary.getText().toString()));
        editor.putInt("day", Integer.parseInt(day.getText().toString()));
        editor.putInt("remMon", Integer.parseInt(salary.getText().toString()));
        editor.commit();
    }
}
