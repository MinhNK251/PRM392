package com.example.se1729_vult_se172399;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.se1729_vult_se172399.adapter.MajorAdapter;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Major;
import java.util.List;

public class MajorListActivity extends AppCompatActivity {
    
    private ListView listViewMajors;
    private TextView tvEmptyMajorList;
    private Button btnAddMajor;
    private DatabaseHelper databaseHelper;
    private MajorAdapter majorAdapter;
    private List<Major> majorList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_list);
        
        initViews();
        setupDatabase();
        setupListeners();
        loadMajors();
    }
    
    private void initViews() {
        listViewMajors = findViewById(R.id.listViewMajors);
        tvEmptyMajorList = findViewById(R.id.tvEmptyMajorList);
        btnAddMajor = findViewById(R.id.btnAddMajor);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }
    
    private void setupListeners() {
        btnAddMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddMajorDialog();
            }
        });
    }
    
    private void loadMajors() {
        majorList = databaseHelper.getAllMajors();
        
        if (majorList.isEmpty()) {
            listViewMajors.setVisibility(View.GONE);
            tvEmptyMajorList.setVisibility(View.VISIBLE);
        } else {
            listViewMajors.setVisibility(View.VISIBLE);
            tvEmptyMajorList.setVisibility(View.GONE);
            
            majorAdapter = new MajorAdapter(this, majorList, databaseHelper);
            listViewMajors.setAdapter(majorAdapter);
        }
    }
    
    private void showAddMajorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Major");
        
        final EditText input = new EditText(this);
        input.setHint("Enter major name");
        builder.setView(input);
        
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String majorName = input.getText().toString().trim();
                if (!TextUtils.isEmpty(majorName)) {
                    Major major = new Major(majorName);
                    long result = databaseHelper.addMajor(major);
                    if (result != -1) {
                        Toast.makeText(MajorListActivity.this, "Major added successfully", Toast.LENGTH_SHORT).show();
                        loadMajors();
                    } else {
                        Toast.makeText(MajorListActivity.this, "Failed to add major", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MajorListActivity.this, "Please enter a valid major name", Toast.LENGTH_SHORT).show();
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
    
    @Override
    protected void onResume() {
        super.onResume();
        loadMajors();
    }
} 