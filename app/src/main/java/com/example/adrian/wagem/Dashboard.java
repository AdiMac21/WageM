
package com.example.adrian.wagem;

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

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.adrian.wagem.Adapters.CatGridAdapter;
import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.User;

import static com.example.adrian.wagem.Util.LoadSave.loadCat;
import static com.example.adrian.wagem.Util.LoadSave.loadUser;

public class Dashboard extends AppCompatActivity {
    private static String MY_PREFS_NAME = "prefs";
    private final Context context = this;
    private User user;
    private Button buttonAdd;
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

        user=loadUser(context);
        categories=loadCat(context);
        linkUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        user=loadUser(context);
        categories=loadCat(context);
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
        buttonAdd= (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,AddExpense.class);
                intent.putExtra("categories",categories);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

    }







}
