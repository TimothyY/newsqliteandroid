package com.example.try_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView tvusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvusers = findViewById(R.id.tvUsers);

        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = userDAO.getUsers(this);

        String results="";
        for (int i =0;i<users.size();i++){
            results = results+" "+users.get(i).username+" "+ users.get(i).password +" ||\n";
        }
        tvusers.setText(results);
    }
}