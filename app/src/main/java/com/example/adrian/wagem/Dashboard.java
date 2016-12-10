
package com.example.adrian.wagem;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if (firstRun) {
            createDialog();
            firstRun = false;
        }
        createUser();
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
