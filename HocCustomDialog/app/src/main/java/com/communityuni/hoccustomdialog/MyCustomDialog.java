package com.communityuni.hoccustomdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by cafe on 24/12/2016.
 */

public class MyCustomDialog extends Dialog {

    ImageView imgThoat,imgHuy;
    Activity context;
    public MyCustomDialog(Activity context) {
        super(context);
        this.context=context;
        setContentView(R.layout.itemfordialog);
        addControls();
        addEvents();
    }

    private void addEvents() {
        imgThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();
            }
        });
        imgHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void addControls() {
        imgThoat= (ImageView) findViewById(R.id.imgThoat);
        imgHuy= (ImageView) findViewById(R.id.imgHuy);
        setTitle("Xác nhận");
        setCanceledOnTouchOutside(false);
    }
}
