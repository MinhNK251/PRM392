package com.communityuni.baitaprenluyen1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtCMND,edtPhone,edtDiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtCMND= (EditText) findViewById(R.id.edtCMND);
        edtPhone= (EditText) findViewById(R.id.edtPhone);
        edtDiaChi= (EditText) findViewById(R.id.edtDiaChi);
    }

    public void xuLyCapNhat(View view) {
        Toast.makeText(this,"Bạn nhấn Cập nhật",Toast.LENGTH_LONG).show();
    }

    public void xuLyLamLai(View view) {
        edtCMND.setText("");
        edtPhone.setText("");
        edtDiaChi.setText("");
        edtCMND.requestFocus();
    }
}
