package com.communityuni.baitaprenluyen6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.communityuni.model.PhuongTrinhBac2;

public class MainActivity extends AppCompatActivity {

    EditText edtHsa,edtHsb,edtHsc;
    Button btnTiep,btnGiai,btnThoat;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThoat.setOnClickListener(new MyEvent());
        btnGiai.setOnClickListener(new MyEvent());
        btnTiep.setOnClickListener(new MyEvent());
    }

    private void addControls() {
        edtHsa= (EditText) findViewById(R.id.edtHsa);
        edtHsb= (EditText) findViewById(R.id.edtHsb);
        edtHsc= (EditText) findViewById(R.id.edtHsc);
        btnTiep= (Button) findViewById(R.id.btnTiepTuc);
        btnGiai= (Button) findViewById(R.id.btnGiaiPT);
        btnThoat= (Button) findViewById(R.id.btnThoat);
        txtKetQua= (TextView) findViewById(R.id.txtKetQua);
    }
    class MyEvent implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.btnTiepTuc:
                    xuLyTiepTuc();
                    break;
                case R.id.btnGiaiPT:
                    xuLyGiaiPT();
                    break;
                case R.id.btnThoat:
                    xuLyThoat();
                    break;
            }
        }
    }

    private void xuLyThoat() {
        finish();
    }

    private void xuLyGiaiPT() {
        double a=Double.parseDouble(edtHsa.getText().toString());
        double b=Double.parseDouble(edtHsb.getText().toString());
        double c=Double.parseDouble(edtHsc.getText().toString());
        PhuongTrinhBac2 ptb2=new PhuongTrinhBac2(a,b,c);
        txtKetQua.setText(ptb2.giai());
    }

    private void xuLyTiepTuc() {
        edtHsa.setText("");
        edtHsb.setText("");
        edtHsc.setText("");
        txtKetQua.setText("");
        edtHsa.requestFocus();
    }
}
