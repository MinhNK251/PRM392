package com.communityuni.baitaprenluyen10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnLayHinh;
    ImageView imgHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLayHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyLayHinh();
            }
        });
    }

    private void xuLyLayHinh() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"chọn hình"),113);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==113&&resultCode==RESULT_OK)
        {
            try {
                Uri imgURI=data.getData();
                Bitmap hinh= MediaStore.Images.Media.getBitmap(
                        getContentResolver(),
                        imgURI);
                imgHinh.setImageBitmap(hinh);
            }
            catch (Exception ex)
            {
                Log.e("loi",ex.toString());
            }
        }
    }

    private void addControls() {
        btnLayHinh= (Button) findViewById(R.id.btnLayHinh);
        imgHinh= (ImageView) findViewById(R.id.imgHinh);
    }
}
