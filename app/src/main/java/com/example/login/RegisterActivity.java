package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText name, password, confirm;
    Button btnRegister;
    DatabaseHelper db;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.Email_txt);
        password = (EditText) findViewById(R.id.Password_txt);
        confirm = (EditText) findViewById(R.id.ConfirmPassword_txt);

        btnRegister = (Button) findViewById(R.id.Register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s = name.getText().toString();
                String password_s = password.getText().toString();
                String confirm_s = confirm.getText().toString();

                //if (checkEmail ==
                if(name.getText().toString().contains("@")){
                    Toast.makeText(RegisterActivity.this, "Contains @", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Does not Contain @", Toast.LENGTH_SHORT).show();
                }
                if (name_s.equals("") || password_s.equals("") || confirm_s.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    if (password_s.equals(confirm_s)) {
                        Boolean checkEmail = db.checkEmail(name_s);

                        if (checkEmail == false) {
                            //Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                            Boolean insert = db.insert(name_s, password_s);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Account Registered!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Not Registered!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
//
//    public boolean checkEmail(View view, String user) {
//        if (user.contains("@")) {
//            Toast.makeText(RegisterActivity.this, "Contains @", Toast.LENGTH_SHORT).show();
//            return true;
//        } else {
//            Toast.makeText(RegisterActivity.this, "Does not Contain @", Toast.LENGTH_SHORT).show();
//            return false;
//
//        }
//    }
}
