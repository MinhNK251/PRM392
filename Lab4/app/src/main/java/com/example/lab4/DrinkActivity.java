package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DrinkActivity extends AppCompatActivity {
    private RadioGroup rgDrink;
    private Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drink);

        rgDrink = findViewById(R.id.rgDrink);
        btnConfirm = findViewById(R.id.btnConfirmDrink);

        btnConfirm.setOnClickListener(v -> {
            int selectedId = rgDrink.getCheckedRadioButtonId();
            if (selectedId != -1) {
                RadioButton selectedRadio = findViewById(selectedId);
                String drink = selectedRadio.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("drink", drink);
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