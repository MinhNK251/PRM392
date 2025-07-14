package com.communityuni.hoccustomdialog;

import android.app.Dialog;
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

    public void moCustomDialogCach1(View view) {
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Xác nhận");
        dialog.setContentView(R.layout.itemfordialog);
        dialog.setCanceledOnTouchOutside(false);

        ImageView imgThoat= (ImageView) dialog.findViewById(R.id.imgThoat);
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView imgHuy= (ImageView) dialog.findViewById(R.id.imgHuy);
        imgHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void moCustomDialogCach2(View view) {
        MyCustomDialog dialog=new MyCustomDialog(MainActivity.this);
        dialog.show();
    }
}
