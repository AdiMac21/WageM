package com.example.adrian.wagem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Expense;
import com.example.adrian.wagem.Model.User;
import com.example.adrian.wagem.Util.LoadSave;

import java.util.ArrayList;
import java.util.Date;

public class EditExpense extends AppCompatActivity {
    private Categories categories;
    private User user;
    private Button edit;
    private Button delete;
    private EditText name;
    private EditText cost;
    private Spinner dropdown;
    private ArrayList<String> listStrings = new ArrayList<>();
    private int positionA;
    private int positionCat;
    private int positionItem;
    private double lastcost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        Intent intent = getIntent();
        categories = (Categories) intent.getSerializableExtra("categories");
        user = (User) intent.getSerializableExtra("user");
        positionCat = intent.getIntExtra("positionCat", 0);
        positionItem = intent.getIntExtra("positionItem", 0);
        linkUI();
        for (int i = 0; i < categories.getCategories().size(); i++) {
            listStrings.add(categories.getCategories().get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listStrings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);
        dropdown.setSelection(positionCat);
    }

    private void linkUI() {
        edit = (Button) findViewById(R.id.button_editexpense);
        delete = (Button) findViewById(R.id.button_deleteexpense);
        name = (EditText) findViewById(R.id.editText_name_edit);
        cost = (EditText) findViewById(R.id.editText_cost_edit);
        dropdown = (Spinner) findViewById(R.id.spinner_edit);
        name.setText(categories.getCategories().get(positionCat).getItems().get(positionItem).getName());
        cost.setText(String.valueOf(categories.getCategories().get(positionCat).getItems().get(positionItem).getCost()));

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionA = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setRemMon(user.getRemMon() + (long) Double.parseDouble(cost.getText().toString())*100);
                SharedPreferences.Editor editor = getSharedPreferences(Dashboard.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putLong("remMon", user.getRemMon());
                editor.commit();
                categories.getCategories().get(positionCat).setSum(categories.getCategories().get(positionCat).getSum() - categories.getCategories().get(positionCat).getItems().get(positionItem).getCost());
                categories.getCategories().get(positionCat).getItems().remove(positionItem);
                LoadSave.saveCat(EditExpense.this, categories);
//                Intent intent = new Intent(EditExpense.this, Dashboard.class);
//                startActivity(intent);
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastcost = categories.getCategories().get(positionCat).getItems().get(positionItem).getCost();
                double newcost = lastcost - Double.parseDouble(cost.getText().toString());
                user.setRemMon(user.getRemMon() + newcost);
                SharedPreferences.Editor editor = getSharedPreferences(Dashboard.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putFloat("remMon", (float) user.getRemMon());
                editor.commit();
                if (dropdown.getSelectedItemPosition() == positionCat) {
                    categories.getCategories().get(positionCat).setSum(categories.getCategories().get(positionCat).getSum() - newcost);
                    categories.getCategories().get(positionCat).getItems().get(positionItem).setName(name.getText().toString());
                    categories.getCategories().get(positionCat).getItems().get(positionItem).setCost(Double.parseDouble(cost.getText().toString()));
                    LoadSave.saveCat(EditExpense.this, categories);
                    Intent intent = new Intent(EditExpense.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    categories.getCategories().get(positionCat).setSum(categories.getCategories().get(positionCat).getSum() - categories.getCategories().get(positionCat).getItems().get(positionItem).getCost());
                    Date date = categories.getCategories().get(positionCat).getItems().get(positionItem).getDate();
                    categories.getCategories().get(positionCat).getItems().remove(positionItem);
                    Expense expense = new Expense(name.getText().toString(), Float.parseFloat(cost.getText().toString()));
                    expense.setDate(date);
                    categories.getCategories().get(positionA).getItems().add(expense);
                    categories.getCategories().get(positionA).setSum(categories.getCategories().get(positionA).getSum() + Double.parseDouble(cost.getText().toString()));
                    LoadSave.saveCat(EditExpense.this, categories);
                    Intent intent = new Intent(EditExpense.this, Dashboard.class);
                    startActivity(intent);
                }
            }
        });

    }
}
