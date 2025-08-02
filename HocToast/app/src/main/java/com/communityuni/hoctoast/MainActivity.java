package com.communityuni.hoctoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyToastShort(View view) {
        Toast t=Toast.makeText(MainActivity.this,"Toast Short",Toast.LENGTH_SHORT);
        t.show();
    }

    public void xuLyToastLong(View view) {
        Toast.makeText(MainActivity.this,"Toast Long",Toast.LENGTH_LONG).show();
    }
}
