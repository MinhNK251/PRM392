package com.example.listview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    String myBook[] = {"Ta la ta de", "Tinh giap hon tuong", "Dai tuong vo hinh", "Ta that khong muon trong sinh a", "Tu chan lieu thien quan"};
    ArrayAdapter<String> myAdapter;
    ListView myList;
    TextView txtSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        txtSelect = findViewById(R.id.txtSelect);
        myList = findViewById(R.id.myList);
        myAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myBook);
        myList.setAdapter(myAdapter);
        myList.setOnItemClickListener((parent, view, position, id) -> {
            txtSelect.setText("Vi tri: " + position + "\nTen sach: " + myBook[position]);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}