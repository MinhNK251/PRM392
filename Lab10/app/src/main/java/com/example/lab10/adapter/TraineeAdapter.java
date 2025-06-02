package com.example.lab10.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab10.MainActivity;
import com.example.lab10.R;
import com.example.lab10.model.Trainee;

import java.util.List;

public class TraineeAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Trainee> traineeList;

    public TraineeAdapter(MainActivity context, int layout, List<Trainee> traineeList) {
        this.context = context;
        this.layout = layout;
        this.traineeList = traineeList;
    }

    @Override
    public int getCount() {
        return traineeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView tv_Name, tv_Email, tv_Phone, tv_Gender;
        ImageView iv_Delete, iv_Edit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            holder.tv_Email = (TextView) view.findViewById(R.id.tv_Email);
            holder.tv_Phone = (TextView) view.findViewById(R.id.tv_Phone);
            holder.tv_Gender = (TextView) view.findViewById(R.id.tv_Gender);
            holder.iv_Delete = (ImageView) view.findViewById(R.id.iv_Delete);
            holder.iv_Edit = (ImageView) view.findViewById(R.id.iv_Edit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Trainee trainee = traineeList.get(i);
        holder.tv_Name.setText(trainee.getName());
        holder.tv_Email.setText(trainee.getEmail());
        holder.tv_Phone.setText(trainee.getPhone());
        holder.tv_Gender.setText(trainee.getGender());

        holder.iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogUpdate(trainee.getId(), trainee.getName(), trainee.getEmail(), trainee.getPhone(), trainee.getGender());
            }
        });

        holder.iv_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogDelete(trainee.getId(), trainee.getName());
            }
        });
        return view;
    }
}
