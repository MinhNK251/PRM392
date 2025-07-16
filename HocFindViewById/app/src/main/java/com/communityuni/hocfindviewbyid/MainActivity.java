package com.communityuni.hocfindviewbyid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyOk(View view) {
        TextView txtMsg= (TextView) findViewById(R.id.txtMsg);
        txtMsg.setText("Tui là Trần Duy Thanh - 0987773061-duythanhcse@gmail.com");
    }
}
