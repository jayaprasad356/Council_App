package com.example.imran.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imran.Admin.ViewuseranswersActivity;
import com.example.imran.IntroActivity;
import com.example.imran.R;
import com.example.imran.adapter.UserAnswersAdapter;
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

public class UserActivity extends AppCompatActivity {
    String userid;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        activity = UserActivity.this;
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userid = sh.getString(Constant.USER_ID, "");
        QuestionList();

    }
    private void QuestionList()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,userid);


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Intent intent = new Intent(UserActivity.this, ViewSubmittedAnswerActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();




                    }
                    else {
                        findViewById(R.id.getstarted).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(UserActivity.this,UserquestionActivity.class);
                                intent.putExtra(Constant.QUES_NO,"1");
                                startActivity(intent);
                                finish();
                            }
                        });


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.LIST_USER_ANSWERS, params, true);
    }



    public void logout(View view) {
        Intent intent = new Intent(UserActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}