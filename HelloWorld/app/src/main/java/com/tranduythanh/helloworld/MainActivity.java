package com.tranduythanh.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTong2So= (Button) findViewById(R.id.btnTong2So);

        btnTong2So.setOnClickListener(new MyEvent());
    }
    private class MyEvent implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            //xử lý ở đây
        }
    }
}
