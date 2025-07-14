package com.communityuni.hoccustomdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void moManHinhThoat(View view) {
        MyCustomDialog dialog=new MyCustomDialog(Main2Activity.this);
        dialog.show();
    }
}
