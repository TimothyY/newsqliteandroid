package com.example.try_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.btnLogin){
            ArrayList<User> arrUsers = new ArrayList<>();

//        User user1 = new User();
//        user1.username="qqq";
//        user1.password="qqq";
//        arrUsers.add(user1);
//
//        User user2 = new User();
//        user2.username="www";
//        user2.password="www";
//        arrUsers.add(user2);

            UserDAO userDao = new UserDAO();
            arrUsers = userDao.getUsers(this);

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            for(int i=0; i<arrUsers.size();i++){
                if(username.compareToIgnoreCase(arrUsers.get(i).username)==0 &&
                        password.compareToIgnoreCase(arrUsers.get(i).password)==0){
                    Intent niatPindah = new Intent(this,MainActivity2.class);
                    startActivity(niatPindah);
                }
            }
        }
        else if(v.getId()==R.id.btnRegister){
            //coba insert
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            User tempUserForReg = new User();
            tempUserForReg.username= username;
            tempUserForReg.password= password;
            UserDAO userDao = new UserDAO();
            userDao.insertUser(this, tempUserForReg);
            Toast.makeText(this, "Insert berhasil", Toast.LENGTH_SHORT).show();
        }




    }

}