
package com.example.adrian.wagem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.adrian.wagem.Adapters.CatGridAdapter;
import com.example.adrian.wagem.Model.Categories;
import com.example.adrian.wagem.Model.User;

import static com.example.adrian.wagem.Util.LoadSave.loadCat;
import static com.example.adrian.wagem.Util.LoadSave.loadUser;

public class Dashboard extends AppCompatActivity {
    static String MY_PREFS_NAME = "prefs";
    private final Context context = this;
    private User user;
    private Button buttonAdd;
    private Categories categories;
    private GridView gridView;
    private RoundCornerProgressBar progressBar;
    private TextView allMoney;
    private TextView minusMoney;
    private TextView resultMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("categories", null);
        if (restoredText == null) {
            Intent intent = new Intent(context, OneTime.class);
            startActivity(intent);
        } else {

            user = loadUser(context);
            categories = loadCat(context);
            linkUi();
            moneyTV();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        user = loadUser(context);
        categories = loadCat(context);
        progressBar.setProgressColor(Color.parseColor("#4267B2"));
        progressBar.setBackgroundColor(Color.parseColor("#E9EBEE"));
        progressBar.setMax((float) user.getTotalMoney());
        progressBar.setProgress((float) user.getRemMon());
        moneyTV();
    }

    private void linkUi() {
        gridView = (GridView) findViewById(R.id.grid);
        CatGridAdapter adapter = new CatGridAdapter(Dashboard.this, categories);
        gridView.setAdapter(adapter);
        allMoney = (TextView) findViewById(R.id.all_money_tv);
        minusMoney = (TextView) findViewById(R.id.money_spet_tv);
        resultMoney = (TextView) findViewById(R.id.rem_money_tv);
        progressBar = (RoundCornerProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgressColor(Color.parseColor("#4267B2"));
        progressBar.setBackgroundColor(Color.parseColor("#E9EBEE"));
        progressBar.setMax((float) user.getTotalMoney());
        progressBar.setProgress((float) user.getRemMon());
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddExpense.class);
                intent.putExtra("categories", categories);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, CatDet.class);
                intent.putExtra("categories", categories);
                intent.putExtra("position", position);
                intent.putExtra("user", user);
                intent.putExtra("category", categories.getCategories().get(position));
                startActivity(intent);
            }
        });

    }


    private void moneyTV() {
        allMoney.setText(String.valueOf(user.getTotalMoney()));
        minusMoney.setText(String.valueOf(user.getRemMon() - user.getTotalMoney()));
        resultMoney.setText(String.valueOf(user.getRemMon()));
    }


}
