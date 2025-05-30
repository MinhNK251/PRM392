package com.example.lab5_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_1.R;
import com.example.lab5_1.model.User;

import java.util.ArrayList;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private ArrayList<User> userList;

    public UserAdapter(ArrayList<User> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_userlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserName.setText("UserName: " + user.getUserName());
        holder.tvFullName.setText("FullName: " + user.getFullName());
        holder.tvEmail.setText("Email: " + user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName, tvFullName, tvEmail;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_userName);
            tvFullName = itemView.findViewById(R.id.tv_fullName);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }
}
