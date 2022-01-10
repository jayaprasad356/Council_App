package com.example.imran.resident;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imran.IntroActivity;
import com.example.imran.R;
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResidentMainActivity extends AppCompatActivity {
    String userid;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_main);
        activity = ResidentMainActivity.this;
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
                        Intent intent = new Intent(ResidentMainActivity.this, ResidentAnswersActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();




                    }
                    else {
                        findViewById(R.id.getstarted).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ResidentMainActivity.this, ResidentQuesActivity.class);
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
        Intent intent = new Intent(ResidentMainActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }
}