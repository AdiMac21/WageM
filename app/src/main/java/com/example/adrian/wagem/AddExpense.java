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

public class AddExpense extends AppCompatActivity {
    private Categories categories;
    private User user;
    private Button confirm;
    private EditText name;
    private EditText cost;
    private Spinner dropdown;
    private ArrayList<String> listStrings = new ArrayList<>();
    private int positionA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Intent intent = getIntent();
        categories = (Categories) intent.getSerializableExtra("categories");
        user = (User) intent.getSerializableExtra("user");
        linkUI();
        for (int i = 0; i < categories.getCategories().size(); i++) {
            listStrings.add(categories.getCategories().get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listStrings);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);
    }

    private void linkUI() {
        confirm = (Button) findViewById(R.id.button_confexpense);
        name = (EditText) findViewById(R.id.editText_name);
        cost = (EditText) findViewById(R.id.editText_cost);
        dropdown = (Spinner) findViewById(R.id.spinner);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionA = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setRemMon(-Double.parseDouble(cost.getText().toString()));
                SharedPreferences.Editor editor = getSharedPreferences(Dashboard.MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putFloat("remMon", (float) user.getRemMon());
                editor.commit();
                Expense expense=new Expense(name.getText().toString(),Float.parseFloat(cost.getText().toString()));
                categories.getCategories().get(positionA).getItems().add(expense);
                categories.getCategories().get(positionA).setSum(+Double.parseDouble(cost.getText().toString()));
                LoadSave.saveCat(AddExpense.this,categories);
                Intent intent=new Intent(AddExpense.this,Dashboard.class);
                startActivity(intent);
            }
        });

    }
}
