package com.communityuni.hocactivityaslistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener {

    Button btnHinh1,btnHinh2;
    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();

        addEvents();
    }

    private void addEvents() {
        btnHinh1.setOnClickListener(this);
        btnHinh2.setOnClickListener(this);
        imgHinh.setOnLongClickListener(this);
        imgHinh.setOnClickListener(this);
    }

    private void addControls() {
        btnHinh1= (Button) findViewById(R.id.btnHinh1);
        btnHinh2= (Button) findViewById(R.id.btnHinh2);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnHinh1)
        {
            imgHinh.setImageResource(R.drawable.hinh1);
        }
        else if(view.getId()==R.id.btnHinh2)
        {
            imgHinh.setImageResource(R.drawable.hinh2);
        }
        else if(view.getId()==R.id.imgHinh)
        {
            imgHinh.setImageResource(R.drawable.hinh3);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if(view.getId()==R.id.imgHinh)
        {
            imgHinh.setVisibility(View.GONE);
        }
        return false;
    }
}
