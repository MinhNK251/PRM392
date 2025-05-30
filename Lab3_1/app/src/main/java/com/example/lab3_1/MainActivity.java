package com.example.lab3_1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etItem;
    Button btnAdd, btnUpdate, btnDelete;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etItem = findViewById(R.id.etItem);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        listView = findViewById(R.id.listView);

        // Preloaded data
        items = new ArrayList<>();
        items.add("Android");
        items.add("PHP");
        items.add("iOS");
        items.add("Unity");
        items.add("ASP.net");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String input = etItem.getText().toString().trim();
            if (!input.isEmpty()) {
                items.add(input);
                adapter.notifyDataSetChanged();
                etItem.setText("");
                selectedIndex = -1;
            }
        });

        btnUpdate.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                items.set(selectedIndex, etItem.getText().toString().trim());
                adapter.notifyDataSetChanged();
                etItem.setText("");
                selectedIndex = -1;
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                items.remove(selectedIndex);
                adapter.notifyDataSetChanged();
                etItem.setText("");
                selectedIndex = -1;
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            etItem.setText(items.get(position));
            selectedIndex = position;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}