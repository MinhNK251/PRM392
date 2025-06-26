package com.example.pe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import com.example.pe.adapter.MajorAdapter;
import com.example.pe.adapter.StudentAdapter;
import com.example.pe.model.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvStudent;
    ArrayList<Student> studentList;
    StudentAdapter adapter;
    ArrayList<Nganh> majorList;
    MajorAdapter majorAdapter;
    ListView lvMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvStudent = findViewById(R.id.lv_Student);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(this, R.layout.row_student, studentList);
        lvStudent.setAdapter(adapter);

        // Initialize DB
        database = new Database(this, "StudentDB.sqlite", null, 1);

        database.QueryData("CREATE TABLE IF NOT EXISTS Sinhvien(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name NVARCHAR(100), " +
                "date TEXT, gender TEXT, address TEXT, idNganh INTEGER, phone TEXT)");

        database.QueryData("CREATE TABLE IF NOT EXISTS Nganh(" +
                "idNganh INTEGER PRIMARY KEY AUTOINCREMENT, nameNganh NVARCHAR(100))");

        // Initial data
        Cursor c = database.GetData("SELECT * FROM Sinhvien");
        if (c.getCount() == 0) {
            database.QueryData("INSERT INTO Sinhvien VALUES(null, 'Nguyen Van A', '2000-01-01', 'Nam', 'HCM', 1, '0123456789')");
        }
        c.close();

        loadStudents();

        lvMajor = findViewById(R.id.lvMajor);
        majorList = new ArrayList<>();
        majorAdapter = new MajorAdapter(this, R.layout.row_major, majorList);
        lvMajor.setAdapter(majorAdapter);

        loadMajors();

        lvStudent.setOnItemClickListener((parent, view, position, id) -> {
            Student s = studentList.get(position);

            Intent intent = new Intent(MainActivity.this, StudentDetailActivity.class);
            intent.putExtra("id", s.getId());
            intent.putExtra("name", s.getName());
            intent.putExtra("date", s.getDate());
            intent.putExtra("gender", s.getGender());
            intent.putExtra("address", s.getAddress());
            intent.putExtra("majorId", s.getMajorId());
            intent.putExtra("phone", s.getPhone());

            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_add_student)
            showAddStudentDialog();
        else if (item.getItemId() == R.id.menu_add_major)
            showAddMajorDialog();
        return super.onOptionsItemSelected(item);
    }

    private void showAddStudentDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_student);

        EditText etName = dialog.findViewById(R.id.etName);
        EditText etDate = dialog.findViewById(R.id.etDate);
        EditText etGender = dialog.findViewById(R.id.etGender);
        EditText etAddress = dialog.findViewById(R.id.etAddress);
        EditText etMajorId = dialog.findViewById(R.id.etMajorId);
        EditText etPhone = dialog.findViewById(R.id.etPhone);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String date = etDate.getText().toString();
            String gender = etGender.getText().toString();
            String address = etAddress.getText().toString();
            int idMajor = Integer.parseInt(etMajorId.getText().toString());
            String phone = etPhone.getText().toString();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            String sql = "INSERT INTO Sinhvien VALUES(null, '" + name + "', '" + date + "', '" + gender + "', '" + address + "', " + idMajor + ", '" + phone + "')";
            database.QueryData(sql);
            Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            loadStudents();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    private void showAddMajorDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_major);

        EditText etMajorName = dialog.findViewById(R.id.etMajorName);
        Button btnAdd = dialog.findViewById(R.id.btnAddMajor);
        Button btnCancel = dialog.findViewById(R.id.btnCancelMajor);

        btnAdd.setOnClickListener(v -> {
            String name = etMajorName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên ngành", Toast.LENGTH_SHORT).show();
                return;
            }
            database.QueryData("INSERT INTO Nganh VALUES(null, '" + name + "')");
            Toast.makeText(this, "Đã thêm ngành", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            loadMajors();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    public void showUpdateStudentDialog (Student s) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_student); // reuse layout

        EditText etName = dialog.findViewById(R.id.etName);
        EditText etDate = dialog.findViewById(R.id.etDate);
        EditText etGender = dialog.findViewById(R.id.etGender);
        EditText etAddress = dialog.findViewById(R.id.etAddress);
        EditText etMajorId = dialog.findViewById(R.id.etMajorId);
        EditText etPhone = dialog.findViewById(R.id.etPhone);
        Button btnSave = dialog.findViewById(R.id.btnSave);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        etName.setText(s.getName());
        etDate.setText(s.getDate());
        etGender.setText(s.getGender());
        etAddress.setText(s.getAddress());
        etMajorId.setText(String.valueOf(s.getMajorId()));
        etPhone.setText(s.getPhone());

        btnSave.setText("Cập nhật");
        btnSave.setOnClickListener(v -> {
            String sql = "UPDATE Sinhvien SET name='" + etName.getText() + "', date='" + etDate.getText() +
                    "', gender='" + etGender.getText() + "', address='" + etAddress.getText() + "', idNganh=" +
                    etMajorId.getText() + ", phone='" + etPhone.getText() + "' WHERE id=" + s.getId();
            database.QueryData(sql);
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            loadStudents();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void confirmDeleteStudent(Student s) {
        new AlertDialog.Builder(this)
                .setTitle("Xoá sinh viên")
                .setMessage("Bạn có muốn xoá " + s.getName() + " không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    database.QueryData("DELETE FROM Sinhvien WHERE id=" + s.getId());
                    Toast.makeText(this, "Đã xoá", Toast.LENGTH_SHORT).show();
                    loadStudents();
                })
                .setNegativeButton("Không", null)
                .show();
    }

    public void showUpdateMajorDialog(Nganh major) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_major);

        EditText etMajorName = dialog.findViewById(R.id.etMajorName);
        Button btnSave = dialog.findViewById(R.id.btnSaveMajor);
        Button btnCancel = dialog.findViewById(R.id.btnCancelMajor);

        etMajorName.setText(major.getName());
        btnSave.setText("Cập nhật");

        btnSave.setOnClickListener(v -> {
            String newName = etMajorName.getText().toString();
            if (newName.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên ngành", Toast.LENGTH_SHORT).show();
                return;
            }
            String sql = "UPDATE Nganh SET nameNganh='" + newName + "' WHERE idNganh=" + major.getId();
            database.QueryData(sql);
            Toast.makeText(this, "Cập nhật ngành thành công", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            loadMajors();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    public void confirmDeleteMajor(Nganh major) {
        new AlertDialog.Builder(this)
                .setTitle("Xoá ngành")
                .setMessage("Bạn có muốn xoá ngành '" + major.getName() + "' không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    database.QueryData("DELETE FROM Nganh WHERE idNganh=" + major.getId());
                    Toast.makeText(this, "Đã xoá ngành", Toast.LENGTH_SHORT).show();
                    loadMajors();
                })
                .setNegativeButton("Không", null)
                .show();
    }

    private void loadStudents() {
        Cursor c = database.GetData("SELECT * FROM Sinhvien");
        studentList.clear();
        while (c.moveToNext()) {
            studentList.add(new Student(
                    c.getInt(0), c.getString(1), c.getString(2),
                    c.getString(3), c.getString(4), c.getInt(5), c.getString(6)
            ));
        }
        c.close();
        adapter.notifyDataSetChanged();
    }

    private void loadMajors() {
        Cursor c = database.GetData("SELECT * FROM Nganh");
        majorList.clear();
        while (c.moveToNext()) {
            majorList.add(new Nganh(c.getInt(0), c.getString(1)));
        }
        c.close();
        majorAdapter.notifyDataSetChanged();
    }
}