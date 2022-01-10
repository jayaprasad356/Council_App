package com.example.imran.Council;

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
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditquesActivity extends AppCompatActivity {
    String Quesno,Question,Option1,Option2,Option3,Option4,Correct_Option;
    Button next;
    EditText question,option1,option2,option3,option4;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    String correctvalue = "";
    TextView quesnotv;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editques);
        Quesno = getIntent().getStringExtra(Constant.QUES_NO);
        Question = getIntent().getStringExtra(Constant.QUESTION);
        Option1 = getIntent().getStringExtra(Constant.OPTION1);
        Option2 = getIntent().getStringExtra(Constant.OPTION2);
        Option3 = getIntent().getStringExtra(Constant.OPTION3);
        Option4 = getIntent().getStringExtra(Constant.OPTION4);
        Correct_Option = getIntent().getStringExtra(Constant.CORRECT_OPTION);

        activity = EditquesActivity.this;

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

        quesnotv.setText("Q No. "+Quesno);
        question.setText(Question);
        option1.setText(Option1);
        option2.setText(Option2);
        option3.setText(Option3);
        option4.setText(Option4);

        if (Option1.equals(Correct_Option)){
            radioButton1.setChecked(true);
        }
        else if (Option2.equals(Correct_Option)){
            radioButton2.setChecked(true);
        }
        else if (Option3.equals(Correct_Option)){
            radioButton3.setChecked(true);
        }
        else if (Option4.equals(Correct_Option)){
            radioButton4.setChecked(true);
        }


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

    private void AddQuestion()
    {
        Map<String, String> params = new HashMap<>();

        params.put(Constant.QUES_NO,Quesno);
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

                        Intent intent = new Intent(activity, ManageQuesActivity.class);
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
        }, activity, Constant.UPDATE_QUESTION, params,true);


    }
}