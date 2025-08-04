package com.example.se1729_vult_se172399;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    
    private TextInputEditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        initViews();
        setupDatabase();
        setupListeners();
        
        checkIfAlreadyLoggedIn();
    }
    
    private void checkIfAlreadyLoggedIn() {
        String loggedInUser = sharedPreferences.getString("logged_in_user", null);
        if (loggedInUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
    
    private void initViews() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }
    
    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    
    private void performLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter username");
            etUsername.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter password");
            etPassword.requestFocus();
            return;
        }
        
        User user = databaseHelper.checkUser(username, password);
        
        if (user != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("logged_in_user", username);
            editor.putInt("user_id", user.getId());
            editor.apply();
            
            Toast.makeText(this, "Login successful! Welcome " + username, Toast.LENGTH_SHORT).show();
            
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String loggedInUser = sharedPreferences.getString("logged_in_user", null);
        if (loggedInUser != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
} 