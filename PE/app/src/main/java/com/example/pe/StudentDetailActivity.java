package com.example.pe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvStudentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_detail);

        tvStudentInfo = findViewById(R.id.tvStudentInfo);

        // Get intent extras
        int id = getIntent().getIntExtra("id", -1);
        String name = getIntent().getStringExtra("name");
        String date = getIntent().getStringExtra("date");
        String gender = getIntent().getStringExtra("gender");
        String address = getIntent().getStringExtra("address");
        int majorId = getIntent().getIntExtra("majorId", -1);
        String phone = getIntent().getStringExtra("phone");

        String info = "ID: " + id + "\nTên: " + name + "\nNgày sinh: " + date +
                "\nGiới tính: " + gender + "\nĐịa chỉ: " + address +
                "\nID Ngành: " + majorId + "\nSĐT: " + phone;

        tvStudentInfo.setText(info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}