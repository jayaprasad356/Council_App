package com.example.imran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imran.Admin.MainActivity;
import com.example.imran.login.Council_loginActivity;
import com.example.imran.login.LoginActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void userlogin(View view) {
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void adminlogin(View view) {
        Intent intent = new Intent(IntroActivity.this, Council_loginActivity.class);
        startActivity(intent);
    }
}