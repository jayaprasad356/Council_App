package com.example.imran.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.imran.R;

public class MainActivity extends AppCompatActivity {

    Button add_question,viewedit_quetion,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_question=findViewById(R.id.textButton1);
        viewedit_quetion=findViewById(R.id.textButton2);
        viewedit_quetion=findViewById(R.id.textButton2);
        profile=findViewById(R.id.textButton3);
        add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddquestionActivity.class);
                startActivity(intent);
            }
        });
        viewedit_quetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Viewedit_questionActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                startActivity(intent);
            }
        });
    }
}