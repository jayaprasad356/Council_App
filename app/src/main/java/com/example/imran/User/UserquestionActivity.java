package com.example.imran.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imran.R;
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;
import com.example.imran.model.Question;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserquestionActivity extends AppCompatActivity {
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    TextView quesnotv,question;
    Button next;
    String QuestionNo,correctvalue, resultans;
    Activity activity;
    String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userquestion);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        userid = sh.getString(Constant.USER_ID, "");



        activity = UserquestionActivity.this;
        QuestionNo = getIntent().getStringExtra(Constant.QUES_NO);
        radioButton1 = findViewById(R.id.radio_button_1);
        radioButton2 = findViewById(R.id.radio_button_2);
        radioButton3 = findViewById(R.id.radio_button_3);
        radioButton4 = findViewById(R.id.radio_button_4);

        quesnotv = findViewById(R.id.question_no);
        question = findViewById(R.id.question);
        next = findViewById(R.id.next_btn);


        QuestionList();



    }

    private void UserAnswer()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,userid);
        params.put(Constant.QUES_NO,QuestionNo);
        params.put(Constant.QUESTION,question.getText().toString());
        params.put(Constant.OPTION1,radioButton1.getText().toString());
        params.put(Constant.OPTION2,radioButton2.getText().toString());
        params.put(Constant.OPTION3,radioButton3.getText().toString());
        params.put(Constant.OPTION4,radioButton4.getText().toString());
        params.put(Constant.CORRECT_OPTION,correctvalue);
        params.put(Constant.RESULT, resultans);


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString(Constant.USER_ANSWERED, Constant.TRUE);
                        myEdit.commit();
                        Intent intent = new Intent(activity,UserquestionActivity.class);
                        intent.putExtra(Constant.QUES_NO,String.valueOf(Integer.parseInt(QuestionNo) + 1));
                        startActivity(intent);
                        finish();








                    }
                    else {

                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.USER_ANSWER_URL, params, true);

    }

    private void QuestionList()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.QUES_NO,QuestionNo);


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        Question qt = g.fromJson(jsonObject1.toString(), Question.class);
                        quesnotv.setText("Q.No. " +QuestionNo);
                        question.setText(qt.getQuestion());
                        radioButton1.setText(qt.getOption_1());
                        radioButton2.setText(qt.getOption_2());
                        radioButton3.setText(qt.getOption_3());
                        radioButton4.setText(qt.getOption_4());
                        correctvalue = qt.getCorrect_option();
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (radioButton1.isChecked()){
                                    if (correctvalue.equals(radioButton1.getText().toString())){
                                        resultans = "correct";

                                    }
                                    else {
                                        resultans = "wrong";
                                    }

                                }
                                else if (radioButton2.isChecked()){
                                    if (correctvalue.equals(radioButton2.getText().toString())){
                                        resultans = "correct";

                                    }
                                    else {
                                        resultans = "wrong";
                                    }

                                }
                                else if (radioButton3.isChecked()){
                                    if (correctvalue.equals(radioButton3.getText().toString())){
                                        resultans = "correct";

                                    }
                                    else {
                                        resultans = "wrong";
                                    }

                                }
                                else if (radioButton4.isChecked()){
                                    if (correctvalue.equals(radioButton4.getText().toString())){
                                        resultans = "correct";

                                    }
                                    else {
                                        resultans = "wrong";
                                    }

                                }

                                UserAnswer();
                            }
                        });








                    }
                    else {
                        Intent intent = new Intent(activity,UserActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e.getMessage()), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.QUESTION_URL, params, true);
    }
}