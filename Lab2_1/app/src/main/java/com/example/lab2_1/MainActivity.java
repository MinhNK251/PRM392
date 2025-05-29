package com.example.lab2_1;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText minInput, maxInput;
    TextView resultText;
    Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        minInput = findViewById(R.id.minInput);
        maxInput = findViewById(R.id.maxInput);
        resultText = findViewById(R.id.resultText);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minStr = minInput.getText().toString();
                String maxStr = maxInput.getText().toString();
                if (minStr.isEmpty() || maxStr.isEmpty()) {
                    resultText.setText("Please enter both Min and Max.");
                    return;
                }
                int min = Integer.parseInt(minStr);
                int max = Integer.parseInt(maxStr);
                if (min > max) {
                    resultText.setText("Min should be less than or equal to Max.");
                    return;
                }
                int random = new Random().nextInt(max - min + 1) + min;
                resultText.setText("Result: " + random);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}