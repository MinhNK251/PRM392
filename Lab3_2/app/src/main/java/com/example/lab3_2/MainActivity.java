package com.example.lab3_2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab3_2.adapter.FruitAdapter;
import com.example.lab3_2.model.FruitItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<FruitItem> fruits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        fruits = new ArrayList<>();
        fruits.add(new FruitItem(R.drawable.apple, "apple", "apple...some description goes here."));
        fruits.add(new FruitItem(R.drawable.banana, "banana", "banana...some description goes here."));
        fruits.add(new FruitItem(R.drawable.blueberry, "blueberry", "blueberry...some description goes here."));
        fruits.add(new FruitItem(R.drawable.corn, "corn", "corn...some description goes here."));
        fruits.add(new FruitItem(R.drawable.grapes, "grapes", "grapes...some description goes here."));

        FruitAdapter adapter = new FruitAdapter(this, fruits);
        listView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}