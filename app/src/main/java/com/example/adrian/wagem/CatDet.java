package com.example.adrian.wagem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.adrian.wagem.Adapters.DetAdapter;
import com.example.adrian.wagem.Model.Category;

public class CatDet extends AppCompatActivity {
    Category category;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_det);
        listView= (ListView) findViewById(R.id.list_bon);
        Intent intent=getIntent();
        category= (Category) intent.getSerializableExtra("category");
        DetAdapter detAdapter=new DetAdapter(this,category);
        listView.setAdapter(detAdapter);

    }
}
