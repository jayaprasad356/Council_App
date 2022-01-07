package com.example.imran.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imran.R;
import com.example.imran.User.UserquestionActivity;
import com.example.imran.adapter.QuestionListAdapter;
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;
import com.example.imran.login.RegisterActivity;
import com.example.imran.model.Question;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddquestionActivity extends AppCompatActivity {
    Button next;
    EditText question,option1,option2,option3,option4;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    String correctvalue = "";
    TextView quesnotv;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);

        activity = AddquestionActivity.this;

        next = findViewById(R.id.next_btn);
        question = findViewById(R.id.Question);
        radioButton1 = findViewById(R.id.radio_button_1);
        radioButton2 = findViewById(R.id.radio_button_2);
        radioButton3 = findViewById(R.id.radio_button_3);
        radioButton4 = findViewById(R.id.radio_button_4);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        quesnotv = findViewById(R.id.question_no);
        QuestionList();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radioButton1.isChecked()){
                    correctvalue = option1.getText().toString().trim();

                }
                if (radioButton2.isChecked()){
                    correctvalue = option2.getText().toString().trim();

                }
                if (radioButton3.isChecked()){
                    correctvalue = option3.getText().toString().trim();

                }
                if (radioButton4.isChecked()){
                    correctvalue = option4.getText().toString().trim();

                }

               AddQuestion();
            }
        });








    }

    private void QuestionList()
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.TYPE,"Queston_list");


        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        ArrayList<Question> questions = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Question question = g.fromJson(jsonObject1.toString(), Question.class);
                                questions.add(question);
                            } else {
                                break;
                            }
                        }
                        quesnotv.setText("Q No. "+String.valueOf(questions.size() + 1));




                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.LIST_QUESTION, params, true);
    }



    private void AddQuestion()
    {
        Map<String, String> params = new HashMap<>();
        
            params.put(Constant.QUESTION,question.getText().toString().trim());
            params.put(Constant.OPTION1,option1.getText().toString().trim());
            params.put(Constant.OPTION2,option2.getText().toString().trim());
            params.put(Constant.OPTION3,option3.getText().toString().trim());
            params.put(Constant.OPTION4,option4.getText().toString().trim());
            params.put(Constant.CORRECT_OPTION,correctvalue.toString().trim());

        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {

                        Intent intent = new Intent(AddquestionActivity.this, AddquestionActivity.class);
//
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
        }, AddquestionActivity.this, Constant.ADD_QUESTION, params,true);


    }
}