package com.example.imran.adapter;

import android.app.Activity;
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
import com.example.imran.model.Question;

import java.util.ArrayList;

public class UserAnswersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final Activity activity;
    final ArrayList<Question> questions;

    public UserAnswersAdapter(Activity activity, ArrayList<Question> questions) {
        this.activity = activity;
        this.questions = questions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.answer_layout, parent, false);
        return new QuestionItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final QuestionItemHolder holder = (QuestionItemHolder) holderParent;
        final Question question = questions.get(position);
        holder.quesno.setText(question.getQuestion_id());
        holder.question.setText(question.getQuestion());
        holder.answer.setText(question.getCorrect_option());
        if (question.getResult().equals("wrong")){
            holder.wrong.setVisibility(View.VISIBLE);
            holder.wrong.setText(question.getResult());

        }else if (question.getResult().equals("correct")){
            holder.correct.setVisibility(View.VISIBLE);
            holder.correct.setText(question.getResult());

        }




    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
    static class QuestionItemHolder extends RecyclerView.ViewHolder {

        final TextView question,quesno,answer,wrong,correct;


        public QuestionItemHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            quesno = itemView.findViewById(R.id.question_no);
            answer = itemView.findViewById(R.id.answer);
            wrong = itemView.findViewById(R.id.wrong);
            correct = itemView.findViewById(R.id.correct);
        }
    }
}
