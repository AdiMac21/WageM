package com.example.adrian.wagem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.Model.User;

import static com.example.adrian.wagem.Util.LoadSave.saveCat;

public class OneTime extends AppCompatActivity {
    private String MY_PREFS_NAME = "prefs";
    private final Context context = this;
    private TextView name;
    private TextView salary;
    private TextView day;
    private User user;
    private Button button;
    private Categories categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prim_dialog);
        linkUi();
    }

    private void linkUi() {
        name = (TextView) findViewById(R.id.name_tf);
        salary = (TextView) findViewById(R.id.salary_tf);
        day = (TextView) findViewById(R.id.day_tf);
        button = (Button) findViewById(R.id.button_prim);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
                generateCategories();
                Intent intent=new Intent(context,Dashboard.class);
                startActivity(intent);

            }
        });


    }
    private void generateCategories() {
        categories = new Categories();
        saveCat(context,categories);

    }
    private void saveUser(){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", name.getText().toString());
        editor.putFloat("salary", Float.parseFloat(salary.getText().toString()));
        editor.putInt("day", Integer.parseInt(day.getText().toString()));
        editor.putFloat("remMon", Float.parseFloat(salary.getText().toString()));
        editor.putFloat("totalMoney", Float.parseFloat(salary.getText().toString()));
        editor.commit();
    }


}
