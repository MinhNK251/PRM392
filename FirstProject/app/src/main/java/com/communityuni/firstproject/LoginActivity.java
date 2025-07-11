package com.communityuni.firstproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //viết tương tác control bên trên đây là sai
        setContentView(R.layout.activity_login);
        //khi tương tác control ta phải viết bên dưới hàm này
    }
}
