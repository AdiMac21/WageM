
package com.example.adrian.wagem;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.adrian.wagem.Adapters.CatGridAdapter;
import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.Model.Expense;
import com.example.adrian.wagem.Model.User;

public class Dashboard extends AppCompatActivity {
    private boolean firstRun = true;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (firstRun) {
            createDialog();
            firstRun = false;
        }
        createUser();
        generateCategories();
        linkUi();
    }

    private void linkUi() {
        gridView= (GridView) findViewById(R.id.grid);
        CatGridAdapter adapter=new CatGridAdapter(Dashboard.this,categories);
        gridView.setAdapter(adapter);

    }

    private void generateCategories() {
        categories=new Categories();
        Category foodAndBeverage=new Category("Food&Beverage","android.resource://com.example.adrian.wagem/drawable/french_fries");
        categories.getCategories().add(foodAndBeverage);
        Expense expense=new Expense("Mancare",50);
        categories.getCategories().get(0).getItems().add(expense);
        categories.getCategories().get(0).setSum(+50);

    }

    private void createUser() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");
            int salary = prefs.getInt("salary", 0);
            int day = prefs.getInt("day", 1);
            user = new User(name, salary, day);
        }
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
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", name.getText().toString());
                editor.putInt("salary", Integer.parseInt(salary.getText().toString()));
                editor.putInt("day", Integer.parseInt(day.getText().toString()));
                editor.commit();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
