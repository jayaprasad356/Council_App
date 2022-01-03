package com.example.imran.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.imran.Admin.MainActivity;
import com.example.imran.R;
import com.example.imran.User.UserquestionActivity;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextView register_tv;
    TextInputLayout Sni_no,password;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_tv = findViewById(R.id.register_tv);
        Sni_no = findViewById(R.id.sni_number);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserquestionActivity.class);
                startActivity(intent);
            }

        });
    }
}