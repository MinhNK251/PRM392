package com.communityuni.hoctabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    EditText edtUser,edtPassword;
    Button btnLogin,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("t1"))
                {
                    Toast.makeText(MainActivity.this,"Bạn chọn tab đăng nhập",Toast.LENGTH_LONG).show();
                }else  if(s.equals("t2"))
                {
                    Toast.makeText(MainActivity.this,"Bạn chọn tab hướng dẫn",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDangNhap();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void xuLyDangNhap() {
        Toast.makeText(MainActivity.this
                ,"User="+edtUser.getText().toString()+";password="+edtPassword.getText().toString()
                ,Toast.LENGTH_LONG).show();
    }

    private void addControls() {
        tabHost= (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        //tạo đối tượng 1 tab, có id là t1
        TabHost.TabSpec tab1=tabHost.newTabSpec("t1");
        //thiết lập nội dung layout
        tab1.setContent(R.id.tab1);
        //thiết lập tiêu đề cho tab:
        //tab1.setIndicator("Đăng nhập");
        tab1.setIndicator("",getResources().getDrawable(R.drawable.login2));
        //đưa tab1 vào tabhost
        tabHost.addTab(tab1);

        //tạo đối tượng 1 tab, có id là t1
        TabHost.TabSpec tab2=tabHost.newTabSpec("t2");
        //thiết lập nội dung layout
        tab2.setContent(R.id.tab2);
        //thiết lập tiêu đề cho tab:
        tab2.setIndicator("Hướng dẫn");
        //đưa tab1 vào tabhost
        tabHost.addTab(tab2);

        edtUser= (EditText) findViewById(R.id.edtUser);
        edtPassword= (EditText) findViewById(R.id.edtPassword);
        btnLogin= (Button) findViewById(R.id.btnDangNhap);
        btnExit= (Button) findViewById(R.id.btnThoat);
    }
}
