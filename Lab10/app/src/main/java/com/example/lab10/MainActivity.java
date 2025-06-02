package com.example.lab10;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab10.adapter.TraineeAdapter;
import com.example.lab10.api.TraineeRepository;
import com.example.lab10.api.TraineeService;
import com.example.lab10.model.Trainee;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TraineeService traineeService;
    ListView lv_Trainee;
    ArrayList<Trainee> traineeList;
    TraineeAdapter traineeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv_Trainee = findViewById(R.id.lv_Trainee);
        traineeList = new ArrayList<>();
        traineeAdapter = new TraineeAdapter(this, R.layout.row_trainee, traineeList);
        lv_Trainee.setAdapter(traineeAdapter);
        traineeService = TraineeRepository.getTraineeService();
        GetDataTrainee();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_trainee, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd)
            DialogAdd();
        return super.onOptionsItemSelected(item);
    }

    private void DialogAdd(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_trainee);

        EditText et_Name = dialog.findViewById(R.id.et_Name);
        EditText et_Email = dialog.findViewById(R.id.et_Email);
        EditText et_Phone = dialog.findViewById(R.id.et_Phone);
        EditText et_Gender = dialog.findViewById(R.id.et_Gender);
        Button btn_Add = dialog.findViewById(R.id.btn_Add);
        Button btn_Cancel = dialog.findViewById(R.id.btn_Cancel);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTrainee = et_Name.getText().toString();
                String emailTrainee = et_Email.getText().toString();
                String phoneTrainee = et_Phone.getText().toString();
                String genderTrainee = et_Gender.getText().toString();

                if(nameTrainee.equals("") || emailTrainee.equals("") || phoneTrainee.equals("") || genderTrainee.equals(""))
                    Toast.makeText(MainActivity.this, "Please add full details", Toast.LENGTH_SHORT).show();
                else {
                    save(nameTrainee, emailTrainee, phoneTrainee, genderTrainee);
                    dialog.dismiss();
                    GetDataTrainee();
                }
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DialogUpdate(long id, String name, String email, String phone, String gender){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_trainee);

        EditText et_Name = dialog.findViewById(R.id.et_Name);
        EditText et_Email = dialog.findViewById(R.id.et_Email);
        EditText et_Phone = dialog.findViewById(R.id.et_Phone);
        EditText et_Gender = dialog.findViewById(R.id.et_Gender);
        Button btn_Update = dialog.findViewById(R.id.btn_Update);
        Button btn_Cancel = dialog.findViewById(R.id.btn_Cancel);
        et_Name.setText(name);
        et_Email.setText(email);
        et_Phone.setText(phone);
        et_Gender.setText(gender);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = et_Name.getText().toString().trim();
                String newEmail = et_Email.getText().toString().trim();
                String newPhone = et_Phone.getText().toString().trim();
                String newGender = et_Gender.getText().toString().trim();
                update(id, newName, newEmail, newPhone, newGender);
                dialog.dismiss();
                GetDataTrainee();
            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DialogDelete(long id, String name){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Do you want to Delete '" + name + "'?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(id);
                Toast.makeText(MainActivity.this, "Deleted " + name, Toast.LENGTH_SHORT).show();
                GetDataTrainee();
            }
        });
        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();
    }

    private void save(String name, String email, String phone, String gender){
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

    private void update(long id, String name, String email, String phone, String gender){
        Trainee trainee = new Trainee(name, email, phone, gender);
        trainee.setId(id);
        try{
            Log.d("DEBUG_UPDATE", "Updating ID: " + id);
            Call<Trainee> call = traineeService.updateTrainees(id, trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null)
                        Toast.makeText(MainActivity.this, "Update successfully", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    private void delete(long id){
        try{
            Log.d("DEBUG_DELETE", "Deleting ID: " + id);
            Call<Trainee> call = traineeService.deleteTrainees(id);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null)
                        Toast.makeText(MainActivity.this, "Delete successfully", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Delete failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }

    private void GetDataTrainee(){
        try{
            Call<Trainee[]> call = traineeService.getAllTrainees();
            call.enqueue(new Callback<Trainee[]>() {
                @Override
                public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                    Trainee[] trainees = response.body();
                    if(trainees == null){
                        return;
                    }
                    traineeList.clear();
                    traineeList.addAll(Arrays.asList(trainees));
                    traineeAdapter = new TraineeAdapter(MainActivity.this, R.layout.row_trainee, traineeList);
                    lv_Trainee.setAdapter(traineeAdapter);
                }
                @Override
                public void onFailure(Call<Trainee[]> call, Throwable t) {
                }
            });
        } catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }
}