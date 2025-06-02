package com.example.lab10;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab10.api.TraineeRepository;
import com.example.lab10.api.TraineeService;
import com.example.lab10.model.Trainee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TraineeService traineeService;
    EditText etName, etEmail, etPhone, etGender;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGender = findViewById(R.id.etGender);
        btnSave = findViewById(R.id.btnSave);
        traineeService = TraineeRepository.getTraineeService();

        btnSave.setOnClickListener(v -> save());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void save(){
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String gender = etGender.getText().toString();
        Trainee trainee = new Trainee(name, email, phone, gender);

        try{
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null)
                        Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }
}