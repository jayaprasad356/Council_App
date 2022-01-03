package com.example.imran.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.imran.Admin.MainActivity;
import com.example.imran.R;
import com.google.android.material.textfield.TextInputEditText;

public class Council_loginActivity extends AppCompatActivity {

    TextInputEditText email_council,password;
    Button login_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_council_login);

        email_council = findViewById(R.id.email_council);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_council.getText().toString().equals("admin@shangrila.gov.un") && password.getText().toString().equals("shangrila@2021$"))
                {
                    Intent intent = new Intent(Council_loginActivity.this, MainActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(Council_loginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }


}