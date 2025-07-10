package com.communityuni.baitaprenluyen11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communityuni.model.SanPham;

public class ChiTietActivity extends AppCompatActivity {

    EditText edtMa,edtTen,edtGia;
    Button btnXoa,btnTroVe;
    Intent intent;
    SanPham sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("XacNhanXoa",true);
                intent.putExtra("SANPHAM",sp);
                setResult(115,intent);
                finish();
            }
        });
    }

    private void addControls() {
        edtMa= (EditText) findViewById(R.id.edtMa);
        edtTen= (EditText) findViewById(R.id.edtTen);
        edtGia= (EditText) findViewById(R.id.edtGia);
        btnXoa= (Button) findViewById(R.id.btnXoa);
        btnTroVe= (Button) findViewById(R.id.btnTroVe);
        intent=getIntent();
        sp= (SanPham) intent.getSerializableExtra("SANPHAM");
        edtMa.setText(sp.getMa());
        edtTen.setText(sp.getTen());
        edtGia.setText(sp.getGia()+"");
    }
}
