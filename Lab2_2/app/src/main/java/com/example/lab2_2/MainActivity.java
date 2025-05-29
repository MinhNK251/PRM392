package com.example.lab2_2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText firstNumber, secondNumber;
    TextView resultLabel;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        firstNumber = findViewById(R.id.firstNumber);
        secondNumber = findViewById(R.id.secondNumber);
        resultLabel = findViewById(R.id.resultLabel);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);

        View.OnClickListener listener = v -> {
            try {
                double num1 = Double.parseDouble(firstNumber.getText().toString());
                double num2 = Double.parseDouble(secondNumber.getText().toString());
                if (v.getId() == R.id.btnAdd)
                    resultLabel.setText("Kết quả: " + num1 + " + " + num2 + " = " + (num1 + num2));
                else if (v.getId() == R.id.btnSubtract)
                    resultLabel.setText("Kết quả: " + num1 + " - " + num2 + " = " + (num1 - num2));
                else if (v.getId() == R.id.btnMultiply)
                    resultLabel.setText("Kết quả: " + num1 + " * " + num2 + " = " + (num1 * num2));
                else if (v.getId() == R.id.btnDivide)
                    if(num2 == 0)
                        resultLabel.setText("Kết quả: Lỗi chia cho 0");
                    else
                        resultLabel.setText("Kết quả: " + num1 + " / " + num2 + " = " + (num1 / num2));
            } catch (Exception e) {
                resultLabel.setText("Kết quả: Lỗi đầu vào");
            }
        };

        btnAdd.setOnClickListener(listener);
        btnSubtract.setOnClickListener(listener);
        btnMultiply.setOnClickListener(listener);
        btnDivide.setOnClickListener(listener);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}