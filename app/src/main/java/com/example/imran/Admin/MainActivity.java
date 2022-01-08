package com.example.imran.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.imran.IntroActivity;
import com.example.imran.R;
import com.example.imran.User.UserActivity;
import com.example.imran.adapter.QuestionListAdapter;
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;
import com.example.imran.model.Question;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button add_question,viewedit_quetion,profile,resetbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_question=findViewById(R.id.textButton1);
        viewedit_quetion=findViewById(R.id.textButton2);
        viewedit_quetion=findViewById(R.id.textButton2);
        profile=findViewById(R.id.textButton3);
        resetbtn=findViewById(R.id.resetbtn);
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
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetQuestions();
            }
        });
    }

    private void ResetQuestions()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TYPE,"Queston_list");


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(MainActivity.this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();




                    }
                    else {
                        Toast.makeText(MainActivity.this, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, MainActivity.this, Constant.DELETE_QUESTION, params, true);
    }

    public void logout(View view) {
        Intent intent = new Intent(MainActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}