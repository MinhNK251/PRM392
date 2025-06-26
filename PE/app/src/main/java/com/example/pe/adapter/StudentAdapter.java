package com.example.pe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pe.MainActivity;
import com.example.pe.R;
import com.example.pe.model.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Student> studentList;

    public StudentAdapter(MainActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return studentList.get(i).getId();
    }

    private class ViewHolder {
        TextView tv_Name, tv_Phone, tv_MajorId;
        ImageView iv_Edit, iv_Delete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, null);

            holder.tv_Name = view.findViewById(R.id.tv_Name);
            holder.tv_Phone = view.findViewById(R.id.tv_Phone);
            holder.tv_MajorId = view.findViewById(R.id.tv_MajorId);
            holder.iv_Edit = view.findViewById(R.id.iv_Edit);
            holder.iv_Delete = view.findViewById(R.id.iv_Delete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Student student = studentList.get(i);
        holder.tv_Name.setText("Tên: " + student.getName());
        holder.tv_Phone.setText("SĐT: " + student.getPhone());
        holder.tv_MajorId.setText("Mã ngành: " + student.getMajorId());

        holder.iv_Edit.setOnClickListener(v ->
                context.showUpdateStudentDialog(student));

        holder.iv_Delete.setOnClickListener(v ->
                context.confirmDeleteStudent(student));

        return view;
    }
}
