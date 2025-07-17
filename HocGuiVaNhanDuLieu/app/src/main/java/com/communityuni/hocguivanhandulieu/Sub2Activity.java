package com.communityuni.hocguivanhandulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.communityuni.model.DanhBa;

public class Sub2Activity extends AppCompatActivity {

    TextView txtData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        addControls();
    }

    private void addControls() {
        txtData= (TextView) findViewById(R.id.txtData);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("mybundle");
        int a=bundle.getInt("a",0);
        double b=bundle.getDouble("b",0.0);
        boolean c=bundle.getBoolean("c",false);
        String s=bundle.getString("s","");
        DanhBa x= (DanhBa) bundle.getSerializable("x");

        txtData.setText("");
        txtData.append("a="+a+"\n");
        txtData.append("b="+b+"\n");
        txtData.append("c="+c+"\n");
        txtData.append("s="+s+"\n");
        txtData.append("x="+x+"\n");
    }
}
