package com.example.adrian.wagem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adrian.wagem.Adapters.DetAdapter;
import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.Category;
import com.example.adrian.wagem.Model.User;

public class CatDet extends AppCompatActivity {
    private Category category;
    private ListView listView;
    private Categories categories;
    private int positionCat;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_det);
        listView= (ListView) findViewById(R.id.list_bon);
        Intent intent=getIntent();
        categories= (Categories) intent.getSerializableExtra("categories");
        positionCat=intent.getIntExtra("position",0);
        user= (User) intent.getSerializableExtra("user");
        category= (Category) intent.getSerializableExtra("category");
        DetAdapter detAdapter=new DetAdapter(this,category);
        listView.setAdapter(detAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(CatDet.this,EditExpense.class);
                intent1.putExtra("categories",categories);
                intent1.putExtra("positionCat",positionCat);
                intent1.putExtra("positionItem",position);
                intent1.putExtra("user",user);
                startActivity(intent1);
            }
        });

    }
}
