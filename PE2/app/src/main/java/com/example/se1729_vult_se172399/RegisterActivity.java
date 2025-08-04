package com.example.se1729_vult_se172399;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    
    private TextInputEditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private DatabaseHelper databaseHelper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initViews();
        setupDatabase();
        setupListeners();
    }
    
    private void initViews() {
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }
    
    private void setupListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    private void performRegistration() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter username");
            etUsername.requestFocus();
            return;
        }
        
        if (username.length() < 3) {
            etUsername.setError("Username must be at least 3 characters");
            etUsername.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter email");
            etEmail.requestFocus();
            return;
        }
        
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please enter a valid email");
            etEmail.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter password");
            etPassword.requestFocus();
            return;
        }
        
        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please confirm password");
            etConfirmPassword.requestFocus();
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }
        
        if (databaseHelper.checkUserExists(username)) {
            etUsername.setError("Username already exists");
            etUsername.requestFocus();
            return;
        }
        
        User user = new User(username, password, email);
        long result = databaseHelper.addUser(user);
        
        if (result != -1) {
            Toast.makeText(this, "Registration successful! You can now login.", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
} 