package com.example.imran.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.imran.R;
import com.example.imran.login.LoginActivity;
import com.example.imran.login.RegisterActivity;

public class Viewedit_questionActivity extends AppCompatActivity {
    ImageView edit_ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewedit_question);
        edit_ques=findViewById(R.id.edit_ques);
        edit_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Viewedit_questionActivity.this, EditquestionActivity.class);
                startActivity(intent);
            }
        });
    }
}