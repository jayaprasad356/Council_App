package com.example.imran.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imran.R;
import com.example.imran.adapter.QuestionListAdapter;
import com.example.imran.helper.ApiConfig;
import com.example.imran.helper.Constant;
import com.example.imran.login.LoginActivity;
import com.example.imran.login.RegisterActivity;
import com.example.imran.model.Question;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Viewedit_questionActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static QuestionListAdapter questionListAdapter;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewedit_question);
        activity = Viewedit_questionActivity.this;
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        recyclerView.setLayoutManager(linearLayoutManager);
        QuestionList();





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

                        ArrayList<Question> channels = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            if (jsonObject1 != null) {
                                Question question = g.fromJson(jsonObject1.toString(), Question.class);
                                channels.add(question);
                            } else {
                                break;
                            }
                        }
                        questionListAdapter = new QuestionListAdapter(activity, channels);
                        recyclerView.setAdapter(questionListAdapter);



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
}