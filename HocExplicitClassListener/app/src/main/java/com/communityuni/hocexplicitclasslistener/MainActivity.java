package com.communityuni.hocexplicitclasslistener;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnMove,btnThoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnMove.setOnClickListener(new MyEvent());
        btnThoat.setOnClickListener(new MyEvent());
        btnMove.setOnLongClickListener(new MyEvent());
    }

    private void addControls() {
        btnMove= (Button) findViewById(R.id.btnMove);
        btnThoat= (Button) findViewById(R.id.btnThoat);
    }
    class MyEvent implements View.OnClickListener, View.OnLongClickListener
    {
        @Override
        public void onClick(View view) {
            if(view.equals(btnMove))
            {
                Random rd=new Random();
                int x=rd.nextInt(300);
                int y=rd.nextInt(1000);
                btnMove.setX(x);
                btnMove.setY(y);
            }
            else if(view.equals(btnThoat))
            {
                finish();
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(view.getId()==R.id.btnMove)
            {
                btnMove.setTextColor(Color.RED);
            }
            return false;
        }
    }
}
