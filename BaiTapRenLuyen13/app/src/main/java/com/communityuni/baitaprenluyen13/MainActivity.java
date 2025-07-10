package com.communityuni.baitaprenluyen13;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtUser,edtPassword;
    CheckBox chkLuu;

    String nameShare="thongtindangnhap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtUser= (EditText) findViewById(R.id.edtUser);
        edtPassword= (EditText) findViewById(R.id.edtPassword);
        chkLuu= (CheckBox) findViewById(R.id.chkLuu);
    }

    public void xuLyDangNhap(View view) {
        if(edtUser.getText().toString().equals("admin")&&edtPassword.getText().toString().equals("123"))
        {
            Intent intent=new Intent(MainActivity.this,QuanLyActivity.class);
            startActivity(intent);
        }
    }

    public void xuLyThoat(View view) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences=getSharedPreferences(nameShare,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user",edtUser.getText().toString());
        editor.putString("pass",edtPassword.getText().toString());
        editor.putBoolean("save",chkLuu.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences=getSharedPreferences(nameShare,MODE_PRIVATE);
        String user=preferences.getString("user","");
        String pass=preferences.getString("pass","");
        boolean save=preferences.getBoolean("save",false);
        if(save)
        {
            edtUser.setText(user);
            edtPassword.setText(pass);
        }
        chkLuu.setChecked(save);
    }
}
