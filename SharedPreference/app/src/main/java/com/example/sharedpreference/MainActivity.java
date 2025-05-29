package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editA, editB, editResult;
    TextView textHistory;
    Button btnSum, btnClear;
    String history = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Find views
        editA = findViewById(R.id.editA);
        editB = findViewById(R.id.editB);
        editResult = findViewById(R.id.editResult);
        textHistory = findViewById(R.id.textHistory);
        btnSum = findViewById(R.id.btnSum);
        btnClear = findViewById(R.id.btnClear);

        // Save data in SharedPreferences
        SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        history = myPrefs.getString("ls", "");
        textHistory.setText(history);

        //Handle click events
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(editA.getText().toString());
                int b = Integer.parseInt(editB.getText().toString());
                int result = a + b;
                editResult.setText(String.valueOf(result));
                history += a + " + " + b + " = " + result;
                textHistory.setText(history);
                history += "\n";
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = "";
                textHistory.setText(history);
                editA.setText("");
                editB.setText("");
                editResult.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences myPref = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = myPref.edit();
        editor.putString("ls", history);
        editor.apply();
    }
}