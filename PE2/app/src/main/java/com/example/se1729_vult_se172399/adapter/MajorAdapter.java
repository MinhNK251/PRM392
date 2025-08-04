package com.example.se1729_vult_se172399.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import com.example.se1729_vult_se172399.R;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Major;
import java.util.List;

public class MajorAdapter extends BaseAdapter {
    
    private Context context;
    private List<Major> majorList;
    private DatabaseHelper databaseHelper;
    private LayoutInflater inflater;
    
    public MajorAdapter(Context context, List<Major> majorList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.majorList = majorList;
        this.databaseHelper = databaseHelper;
        this.inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return majorList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return majorList.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return majorList.get(position).getIdNganh();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.major_list_item, parent, false);
            holder = new ViewHolder();
            holder.tvMajorName = convertView.findViewById(R.id.tvMajorName);
            holder.tvMajorId = convertView.findViewById(R.id.tvMajorId);
            holder.ivEditMajor = convertView.findViewById(R.id.ivEditMajor);
            holder.ivDeleteMajor = convertView.findViewById(R.id.ivDeleteMajor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        Major major = majorList.get(position);
        
        holder.tvMajorName.setText(major.getNameNganh());
        holder.tvMajorId.setText("ID: #" + major.getIdNganh());
        
        holder.ivEditMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditMajorDialog(major, position);
            }
        });
        
        holder.ivDeleteMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(major, position);
            }
        });
        
        return convertView;
    }
    
    private void showEditMajorDialog(Major major, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Major");
        
        final EditText input = new EditText(context);
        input.setText(major.getNameNganh());
        input.setHint("Enter major name");
        builder.setView(input);
        
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newMajorName = input.getText().toString().trim();
                if (!TextUtils.isEmpty(newMajorName)) {
                    major.setNameNganh(newMajorName);
                    int result = databaseHelper.updateMajor(major);
                    if (result > 0) {
                        majorList.set(position, major);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Major updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed to update major", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Please enter a valid major name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        
        builder.create().show();
    }
    
    private void showDeleteConfirmationDialog(Major major, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Major");
        builder.setMessage("Are you sure you want to delete '" + major.getNameNganh() + "'?");
        
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.deleteMajor(major.getIdNganh());
                majorList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Major deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
        
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        
        builder.create().show();
    }
    
    private static class ViewHolder {
        TextView tvMajorName;
        TextView tvMajorId;
        ImageView ivEditMajor;
        ImageView ivDeleteMajor;
    }
    
    public void updateMajorList(List<Major> newMajorList) {
        this.majorList = newMajorList;
        notifyDataSetChanged();
    }
} 