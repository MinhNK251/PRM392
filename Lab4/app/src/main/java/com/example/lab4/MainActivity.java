package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> foodLauncher;
    private ActivityResultLauncher<Intent> drinkLauncher;

    private String selectedFood = "";
    private String selectedDrink = "";

    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnChooseFood = findViewById(R.id.btnChooseFood);
        Button btnChooseDrink = findViewById(R.id.btnChooseDrink);
        Button btnExit = findViewById(R.id.btnExit);
        tvResult = findViewById(R.id.tvResult);

        // Register result launchers
        foodLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            selectedFood = data.getStringExtra("food");
                            updateResultText();
                        }
                    }
                });

        drinkLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            selectedDrink = data.getStringExtra("drink");
                            updateResultText();
                        }
                    }
                });

        btnChooseFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            foodLauncher.launch(intent);
        });

        btnChooseDrink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
            drinkLauncher.launch(intent);
        });

        btnExit.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateResultText() {
        tvResult.setText(selectedFood + " - " + selectedDrink);
    }
}