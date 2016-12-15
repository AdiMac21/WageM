package com.example.adrian.wagem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.User;

public class AddExpense extends AppCompatActivity {
    private Categories categories;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        Intent intent=getIntent();
        categories= (Categories) intent.getSerializableExtra("categories");
        user= (User) intent.getSerializableExtra("user");

    }
}
