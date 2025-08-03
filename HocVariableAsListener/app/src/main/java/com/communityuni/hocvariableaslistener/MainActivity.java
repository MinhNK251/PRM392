package com.communityuni.hocvariableaslistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnAn,btnHien;
    ImageView imgHinh;
    View.OnClickListener myEvent=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.btnAn)
            {
                imgHinh.setVisibility(View.INVISIBLE);
            }
            else if(view.getId()==R.id.btnHien)
            {
                imgHinh.setVisibility(View.VISIBLE);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnAn.setOnClickListener(myEvent);
        btnHien.setOnClickListener(myEvent);
    }

    private void addControls() {
        btnAn= (Button) findViewById(R.id.btnAn);
        btnHien= (Button) findViewById(R.id.btnHien);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
    }
}
