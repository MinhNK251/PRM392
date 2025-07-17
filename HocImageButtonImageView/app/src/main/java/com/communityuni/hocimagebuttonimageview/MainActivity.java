package com.communityuni.hocimagebuttonimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyDoiHinh(View view) {
        ImageView imgHinh= (ImageView) findViewById(R.id.imgHinh);
        if(imgHinh.getTag()==null || imgHinh.getTag().equals("h1")) {
            imgHinh.setImageResource(R.drawable.hoa2);
            imgHinh.setTag("h2");
        }
        else
        {
            imgHinh.setImageResource(R.drawable.hoa);
            imgHinh.setTag("h1");
        }

    }

    public void xuLyDongPhanMem(View view) {
        finish();
    }
}
