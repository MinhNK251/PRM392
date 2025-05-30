package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FoodActivity extends AppCompatActivity {

    private RadioGroup rgFood;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);

        rgFood = findViewById(R.id.rgFood);
        btnConfirm = findViewById(R.id.btnConfirmFood);

        btnConfirm.setOnClickListener(v -> {
            int selectedId = rgFood.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadio = findViewById(selectedId);
                String food = selectedRadio.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("food", food);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}