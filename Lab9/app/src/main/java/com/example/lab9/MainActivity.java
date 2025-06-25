package com.example.lab9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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

import com.example.lab9.adapter.CongViecAdapter;
import com.example.lab9.model.CongViec;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;
    ListView lv_Project;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv_Project = (ListView) findViewById(R.id.lv_Project);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.row_project, arrayCongViec);
        lv_Project.setAdapter(adapter);

        database = new Database(this, "Lab9.sqLite", null, 1);
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key AutoIncrement, TenCV nvarchar(200))");
        // Check if records already exist before inserting
        Cursor cursor = database.GetData("SELECT * FROM CongViec");
        if (cursor.getCount() == 0) {
            // Insert records only if the table is empty
            database.QueryData("Insert into CongViec values(null, 'Project Android')");
            database.QueryData("Insert into CongViec values(null, 'Design App')");
        }
        cursor.close();
        GetDataCongViec();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_project, menu);
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
        dialog.setContentView(R.layout.dialog_add_project);

        EditText et_Name = (EditText) dialog.findViewById(R.id.et_Name);
        Button btn_Add = (Button) dialog.findViewById(R.id.btn_Add);
        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameProject = et_Name.getText().toString();
                if(nameProject.equals(""))
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
                else {
                    database.QueryData("Insert into CongViec values(null, '" + nameProject + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
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

    public void DialogUpdate(String tenCV, int id){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_project);

        EditText et_Name = (EditText) dialog.findViewById(R.id.et_Name);
        Button btn_Update = (Button) dialog.findViewById(R.id.btn_Update);
        Button btn_Cancel = (Button) dialog.findViewById(R.id.btn_Cancel);
        et_Name.setText(tenCV);

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = et_Name.getText().toString().trim();
                database.QueryData("Update CongViec set TenCV = '" + newName + "' where id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                GetDataCongViec();
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

    public void DialogDelete(String tenCV, int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Bạn có muốn xóa công việc '" + tenCV + "' không?");
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("Delete from CongViec where id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Đã xóa " + tenCV, Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();
    }

    private void GetDataCongViec(){
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        arrayCongViec.clear();
        while (dataCongViec.moveToNext()){
            String tenCV = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id, tenCV));
        }
        adapter.notifyDataSetChanged();
    }
}