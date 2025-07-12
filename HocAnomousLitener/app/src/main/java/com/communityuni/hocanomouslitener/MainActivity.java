package com.communityuni.hocanomouslitener;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnDo,btnVang;
    TextView txtMau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMau.setBackgroundColor(Color.RED);
            }
        });
        btnVang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                txtMau.setBackgroundColor(Color.YELLOW);
                return false;
            }
        });
    }

    private void addControls() {
        btnDo= (Button) findViewById(R.id.btnDo);
        btnVang= (Button) findViewById(R.id.btnVang);
        txtMau= (TextView) findViewById(R.id.txtMau);
    }
}
