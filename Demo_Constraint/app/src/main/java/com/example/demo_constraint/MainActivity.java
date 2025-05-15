package com.example.demo_constraint;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Get references to the UI elements
        EditText editTextNumber1 = findViewById(R.id.editTextNumber1);
        EditText editTextNumber2 = findViewById(R.id.editTextNumber2);
        Button buttonSum = findViewById(R.id.buttonSum);
        TextView textViewResult = findViewById(R.id.textViewResult);

        // Set an OnClickListener for the button
        buttonSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(editTextNumber1.getText().toString());
                int b = Integer.parseInt(editTextNumber2.getText().toString());
                int c = a + b;
                textViewResult.setText(String.valueOf(c));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}