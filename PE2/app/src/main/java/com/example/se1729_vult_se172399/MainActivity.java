package com.example.se1729_vult_se172399;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.se1729_vult_se172399.adapter.StudentAdapter;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Student;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private ListView listViewStudents;
    private TextView tvEmptyList, tvWelcomeUser;
    private Button btnAddStudent, btnManageMajors, btnLogin, btnRegister, btnLogout;
    private LinearLayout layoutAuth, layoutLoggedIn;
    private DatabaseHelper databaseHelper;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();
        setupDatabase();
        setupListeners();
        updateUIBasedOnLoginStatus();
        loadStudents();
    }
    
    private void initViews() {
        listViewStudents = findViewById(R.id.listViewStudents);
        tvEmptyList = findViewById(R.id.tvEmptyList);
        tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnManageMajors = findViewById(R.id.btnManageMajors);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogout = findViewById(R.id.btnLogout);
        layoutAuth = findViewById(R.id.layoutAuth);
        layoutLoggedIn = findViewById(R.id.layoutLoggedIn);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }
    
    private void setupListeners() {
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
        
        btnManageMajors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MajorListActivity.class);
                startActivity(intent);
            }
        });
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        
        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student selectedStudent = studentList.get(position);
                Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
                intent.putExtra("STUDENT_ID", selectedStudent.getId());
                startActivity(intent);
            }
        });
    }
    
    private void loadStudents() {
        studentList = databaseHelper.getAllStudents();
        
        if (studentList.isEmpty()) {
            listViewStudents.setVisibility(View.GONE);
            tvEmptyList.setVisibility(View.VISIBLE);
        } else {
            listViewStudents.setVisibility(View.VISIBLE);
            tvEmptyList.setVisibility(View.GONE);
            
            studentAdapter = new StudentAdapter(this, studentList, databaseHelper);
            listViewStudents.setAdapter(studentAdapter);
        }
    }
    
    private void updateUIBasedOnLoginStatus() {
        String loggedInUser = sharedPreferences.getString("logged_in_user", null);
        if (loggedInUser != null) {
            layoutAuth.setVisibility(View.GONE);
            layoutLoggedIn.setVisibility(View.VISIBLE);
            tvWelcomeUser.setText("Welcome, " + loggedInUser + "!");
        } else {
            layoutAuth.setVisibility(View.VISIBLE);
            layoutLoggedIn.setVisibility(View.GONE);
        }
    }
    
    private void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        
        updateUIBasedOnLoginStatus();
        
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        updateUIBasedOnLoginStatus();
        loadStudents();
    }
} 