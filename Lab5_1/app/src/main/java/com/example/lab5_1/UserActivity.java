package com.example.lab5_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_1.adapter.UserAdapter;
import com.example.lab5_1.model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        RecyclerView rvUser = findViewById(R.id.rv_user);
        userList = new ArrayList<>();
        userList.add(new User("MinhNK", "Nguyen Khanh Minh", "john.mclean@examplepetstore.vn"));
        userList.add(new User("TuTT", "Tran Thi Thanh Tu", "james.wilson@example-pet-store.vn"));
        userList.add(new User("DiemT", "Ta Diem", "tadiem251@gmail.com"));

        UserAdapter userAdapter = new UserAdapter(userList);
        rvUser.setAdapter(userAdapter);
        rvUser.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}