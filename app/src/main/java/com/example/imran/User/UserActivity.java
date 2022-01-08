package com.example.imran.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.imran.IntroActivity;
import com.example.imran.R;
import com.example.imran.helper.Constant;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void getstarted(View view) {
        Intent intent = new Intent(UserActivity.this,UserquestionActivity.class);
        intent.putExtra(Constant.QUES_NO,"1");
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(UserActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}