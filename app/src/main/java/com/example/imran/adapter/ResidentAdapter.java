package com.example.imran.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imran.Council.ViewResidentActivity;
import com.example.imran.R;
import com.example.imran.helper.Constant;
import com.example.imran.model.User;

import java.util.ArrayList;

public class ResidentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final Activity activity;
    final ArrayList<User> users;

    public ResidentAdapter(Activity activity, ArrayList<User> users) {
        this.activity = activity;
        this.users = users;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.view_user_layout, parent, false);
        return new UserItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final UserItemHolder holder = (UserItemHolder) holderParent;
        final User user = users.get(position);
        holder.name.setText(user.getFullname());
        holder.email.setText(user.getEmail());
        holder.sni.setText(user.getSNI());
        holder.Viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ViewResidentActivity.class);
                intent.putExtra(Constant.USER_ID,user.getId());
                activity.startActivity(intent);
                     }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    static class UserItemHolder extends RecyclerView.ViewHolder {

        final TextView name,sni,email;
        final Button Viewbtn;



        public UserItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            sni = itemView.findViewById(R.id.sni_number);
            email = itemView.findViewById(R.id.email);
            Viewbtn = itemView.findViewById(R.id.view_btn);
        }
    }
}
