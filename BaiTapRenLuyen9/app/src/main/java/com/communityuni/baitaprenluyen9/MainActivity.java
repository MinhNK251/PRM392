package com.communityuni.baitaprenluyen9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.communityuni.adapter.NhanVienAdapter;
import com.communityuni.model.NhanVien;

public class MainActivity extends AppCompatActivity {

    EditText edtMa,edtTen;
    RadioButton radNu,radNam;
    Button btnNhap;
    ImageView imgXoa;
    ListView lvNhanVien;
    NhanVienAdapter nhanVienAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyNhapNhanVien();
            }
        });
        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyXoa();
            }
        });
    }

    private void xuLyXoa() {
        for (int i=nhanVienAdapter.getCount()-1;i>=0;i--)
        {
            NhanVien nv=nhanVienAdapter.getItem(i);
            if(nv.isXoa())
                nhanVienAdapter.remove(nv);
        }
    }

    private void xuLyNhapNhanVien() {
        NhanVien nv=new NhanVien();
        nv.setMa(edtMa.getText().toString());
        nv.setTen(edtTen.getText().toString());
        nv.setNu(radNu.isChecked());
        nv.setXoa(false);
        nhanVienAdapter.add(nv);
    }

    private void addControls() {
        edtMa= (EditText) findViewById(R.id.edtMa);
        edtTen= (EditText) findViewById(R.id.edtTen);
        radNu= (RadioButton) findViewById(R.id.radNu);
        radNam= (RadioButton) findViewById(R.id.radNam);
        btnNhap= (Button) findViewById(R.id.btnNhap);
        imgXoa= (ImageView) findViewById(R.id.imgXoa);
        lvNhanVien= (ListView) findViewById(R.id.lvNhanVien);
        nhanVienAdapter=new NhanVienAdapter(MainActivity.this,R.layout.item);
        lvNhanVien.setAdapter(nhanVienAdapter);
    }
}
