package com.communityuni.hocguivanhandulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.communityuni.model.DanhBa;

public class Sub1Activity extends AppCompatActivity {

    TextView txtData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        addControls();
    }

    private void addControls() {
        txtData= (TextView) findViewById(R.id.txtData);
        Intent intent=getIntent();
        int a=intent.getIntExtra("a",0);
        double b=intent.getDoubleExtra("b",0.0);
        boolean c=intent.getBooleanExtra("c",false);
        String s=intent.getStringExtra("s");
        DanhBa x= (DanhBa) intent.getSerializableExtra("x");
        txtData.setText("");
        txtData.append("a="+a+"\n");
        txtData.append("b="+b+"\n");
        txtData.append("c="+c+"\n");
        txtData.append("s="+s+"\n");
        txtData.append("x="+x+"\n");
    }
}
