package com.example.imran.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imran.Admin.EditquestionActivity;
import com.example.imran.R;
import com.example.imran.helper.Constant;
import com.example.imran.login.LoginActivity;
import com.example.imran.login.RegisterActivity;
import com.example.imran.model.Question;

import java.util.ArrayList;

public class QuestionListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final Activity activity;
    final ArrayList<Question> questions;

    public QuestionListAdapter(Activity activity, ArrayList<Question> questions) {
        this.activity = activity;
        this.questions = questions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.view_edit_layout, parent, false);
        return new QuestionItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final QuestionItemHolder holder = (QuestionItemHolder) holderParent;
        final Question question = questions.get(position);
        holder.quesno.setText(question.getId());
        holder.questions.setText(question.getQuestion());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditquestionActivity.class);
                intent.putExtra(Constant.QUES_NO,question.getId());
                intent.putExtra(Constant.QUESTION,question.getQuestion());
                intent.putExtra(Constant.OPTION1,question.getOption_1());
                intent.putExtra(Constant.OPTION2,question.getOption_2());
                intent.putExtra(Constant.OPTION3,question.getOption_3());
                intent.putExtra(Constant.OPTION4,question.getOption_4());
                intent.putExtra(Constant.CORRECT_OPTION,question.getCorrect_option());
                activity.startActivity(intent);
                activity.finish();            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
    static class QuestionItemHolder extends RecyclerView.ViewHolder {

        final TextView questions,quesno;
        final ImageView edit;


        public QuestionItemHolder(@NonNull View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.question);
            quesno = itemView.findViewById(R.id.question_no);
            edit = itemView.findViewById(R.id.edit_ques);
        }
    }
}
