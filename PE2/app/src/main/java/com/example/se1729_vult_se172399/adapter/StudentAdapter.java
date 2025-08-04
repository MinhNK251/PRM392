package com.example.se1729_vult_se172399.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.bumptech.glide.Glide;
import com.example.se1729_vult_se172399.R;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Student;
import java.io.File;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    
    private Context context;
    private List<Student> studentList;
    private DatabaseHelper databaseHelper;
    private LayoutInflater inflater;
    
    public StudentAdapter(Context context, List<Student> studentList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.studentList = studentList;
        this.databaseHelper = databaseHelper;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return studentList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return studentList.get(position).getId();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.student_list_item, parent, false);
            holder = new ViewHolder();
            holder.ivStudentPhoto = convertView.findViewById(R.id.ivStudentPhoto);
            holder.tvStudentName = convertView.findViewById(R.id.tvStudentName);
            holder.tvStudentMajor = convertView.findViewById(R.id.tvStudentMajor);
            holder.tvStudentPhone = convertView.findViewById(R.id.tvStudentPhone);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        Student student = studentList.get(position);
        
        holder.tvStudentName.setText(student.getName());
        
        String majorName = databaseHelper.getMajorName(student.getIdNganh());
        holder.tvStudentMajor.setText(majorName);
        
        holder.tvStudentPhone.setText(student.getPhone());
        
        if (student.getImagePath() != null && !student.getImagePath().isEmpty()) {
            File imageFile = new File(student.getImagePath());
            if (imageFile.exists()) {
                Glide.with(context)
                        .load(imageFile)
                        .circleCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(holder.ivStudentPhoto);
            } else {
                holder.ivStudentPhoto.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            holder.ivStudentPhoto.setImageResource(R.mipmap.ic_launcher);
        }
        
        return convertView;
    }
    
    private static class ViewHolder {
        ImageView ivStudentPhoto;
        TextView tvStudentName;
        TextView tvStudentMajor;
        TextView tvStudentPhone;
    }
    
    public void updateStudentList(List<Student> newStudentList) {
        this.studentList = newStudentList;
        notifyDataSetChanged();
    }
} 