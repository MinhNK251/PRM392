package com.communityuni.hocviewsubclassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xuLyMoGioiThieu(View view) {
        LinearLayout.LayoutParams params=
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView txtMsg=new TextView(this);
        txtMsg.setText("Xin chào bạn! hê hê hê");
        txtMsg.setLayoutParams(params);
        linearLayout.addView(txtMsg);

        Button btnBack=new Button(this)
        {
            @Override
            public boolean performClick() {
                setContentView(R.layout.activity_main);
                return super.performClick();
            }
        };
        btnBack.setText("Trở về");
        btnBack.setLayoutParams(params);
        linearLayout.addView(btnBack);

        setContentView(linearLayout);

    }
}
