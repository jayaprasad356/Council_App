package com.example.imran.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.imran.Admin.MainActivity;
import com.example.imran.R;
import com.example.imran.User.UserquestionActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    Button register_btn;
    TextInputEditText Email,name,dob,address,password,sni_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_btn = findViewById(R.id.register_btn);
        Email = findViewById(R.id.email_ed);
        name = findViewById(R.id.name_ed);
        dob = findViewById(R.id.dob_ed);
        password = findViewById(R.id.password_ed);
        sni_no = findViewById(R.id.sni_ed);
        address = findViewById(R.id.address_ed);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    Intent intent = new Intent(RegisterActivity.this, UserquestionActivity.class);
                 startActivity(intent);
                }

            }
        });
    }



    private boolean isValid() {
        if(Email.getText().toString().equals("")){
            Email.setError("Email is invalid");
            Email.requestFocus();
            return false;
        }
        if (name.getText().toString().equals("")){
            name.setError("Name is not empty");
            name.requestFocus();
            return false;
        }
        if (dob.getText().toString().equals("")){
            dob.setError("Date is not empty");
            dob.requestFocus();
            return false;
        }
        if (address.getText().toString().equals("")){
            address.setError("Address is not empty");
            address.requestFocus();
            return false;
        }
        if (password.getText().toString().equals("")){
            password.setError("password must not empty");
            password.requestFocus();
            return false;
        }
        if (password.getText().length() >= 8){
            password.setError("password must be 8 character");
            password.requestFocus();
            return false;
        }
        if (sni_no.getText().toString().equals("")){
            sni_no.setError("SNI Number must not empty ");
            sni_no.requestFocus();
            return false;
        }
        if (sni_no.getText().length() >= 8){
            sni_no.setError("SNI Number must 8 numbers ");
            sni_no.requestFocus();
            return false;
        }

        return  true;
    }

}