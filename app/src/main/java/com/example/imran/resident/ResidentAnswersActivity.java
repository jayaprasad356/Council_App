package com.example.imran.resident;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.imran.Council.ViewResidentActivity;
import com.example.imran.IntroActivity;
import com.example.imran.R;
import com.example.imran.helper.Constant;

public class ResidentAnswersActivity extends AppCompatActivity {
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_answers);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userid = sh.getString(Constant.USER_ID, "");
    }

    public void logout(View view) {
        Intent intent = new Intent(ResidentAnswersActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }

    public void submitted_answers(View view) {
        Intent intent = new Intent(ResidentAnswersActivity.this, ViewResidentActivity.class);
        intent.putExtra(Constant.USER_ID,userid);
        startActivity(intent);
        finish();

    }
}