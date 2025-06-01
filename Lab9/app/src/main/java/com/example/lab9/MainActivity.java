package com.example.lab9;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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

        lv_Project = (ListView) findViewById(R.id.lv_Project);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.row_project, arrayCongViec);
        lv_Project.setAdapter(adapter);

        database = new Database(this, "Lab9.sqLite", null, 1);
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key AutoIncrement, TenCV nvarchar(200))");
        database.QueryData("Insert into CongViec values(null, 'Project Android')");
        database.QueryData("Insert into CongViec values(null, 'Design App')");

        Cursor dataCongViec = database.GetData("Select * from CongViec");
        while (dataCongViec.moveToNext()){
            String tenCV = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id, tenCV));
        }
        adapter.notifyDataSetChanged();
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}