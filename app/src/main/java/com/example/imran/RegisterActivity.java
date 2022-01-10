package com.example.imran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button register_btn,dob;
    TextInputEditText Email,name,address,password,sni_no;
    DatePickerDialog picker;
    boolean dateset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_btn = findViewById(R.id.register_btn);
        Email = findViewById(R.id.email_ed);
        name = findViewById(R.id.name_ed);
        dob = findViewById(R.id.dob_btn);
        password = findViewById(R.id.password_ed);
        sni_no = findViewById(R.id.sni_ed);
        address = findViewById(R.id.address_ed);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                dateset = true;
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    RegisterUser();
//                    Intent intent = new Intent(RegisterActivity.this, UserquestionActivity.class);
//                    startActivity(intent);
                }

            }
        });
    }

    private void RegisterUser()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.EMAIL,Email.getText().toString().trim());
        params.put(Constant.FULLNAME, name.getText().toString().trim());
        params.put(Constant.DOB, dob.getText().toString().trim());
        params.put(Constant.PASSWORD, password.getText().toString().trim());
        params.put(Constant.SNI, sni_no.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }



            }
            else {
                Toast.makeText(this, String.valueOf(response) +String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
        }, RegisterActivity.this, Constant.REGISTER_URL, params,true);


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
        if (!dateset){
            Toast.makeText(this, "Set Date of Birth", Toast.LENGTH_SHORT).show();
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
        if (password.getText().length() < 7){
            password.setError("password must be 8 character");
            password.requestFocus();
            return false;
        }
        if (sni_no.getText().toString().equals("")){
            sni_no.setError("SNI Number must not empty ");
            sni_no.requestFocus();
            return false;
        }
        if (sni_no.getText().length() != 8){
            sni_no.setError("SNI Number must 8 numbers ");
            sni_no.requestFocus();
            return false;
        }

        return  true;
    }

}