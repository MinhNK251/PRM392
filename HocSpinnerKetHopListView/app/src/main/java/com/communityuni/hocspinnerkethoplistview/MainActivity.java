package com.communityuni.hocspinnerkethoplistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.communityuni.model.DanhMuc;
import com.communityuni.model.SanPham;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDanhMuc;
    ArrayAdapter<DanhMuc>danhMucAdapter;

    ListView lvSanPham;
    ArrayAdapter<SanPham>sanPhamAdapter;

    EditText edtMa,edtTen;
    Button btnNhapSP;

    DanhMuc selectedDanhMuc=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnNhapSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyNhapSanPham();
            }
        });
        spinnerDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDanhMuc=danhMucAdapter.getItem(i);

                sanPhamAdapter.clear();
                sanPhamAdapter.addAll(selectedDanhMuc.getSanPhams());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void xuLyNhapSanPham() {
        SanPham sp=new SanPham(edtMa.getText().toString(),edtTen.getText().toString());
        selectedDanhMuc.getSanPhams().add(sp);
        sanPhamAdapter.clear();
        sanPhamAdapter.addAll(selectedDanhMuc.getSanPhams());
    }

    private void addControls() {
        spinnerDanhMuc= (Spinner) findViewById(R.id.spinnerDanhMuc);
        danhMucAdapter=new ArrayAdapter<DanhMuc>(
                MainActivity.this,
                android.R.layout.simple_spinner_item);
        danhMucAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerDanhMuc.setAdapter(danhMucAdapter);

        lvSanPham= (ListView) findViewById(R.id.lvSanPham);
        sanPhamAdapter=new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamAdapter);

        edtMa= (EditText) findViewById(R.id.edtMa);
        edtTen= (EditText) findViewById(R.id.edtTen);
        btnNhapSP= (Button) findViewById(R.id.btnNhapSP);

        danhMucAdapter.add(new DanhMuc("dm1","Bia"));
        danhMucAdapter.add(new DanhMuc("dm2","Rượu"));
        danhMucAdapter.add(new DanhMuc("dm3","Thuốc lá"));
        danhMucAdapter.add(new DanhMuc("dm4","Nước ngọt"));
    }
}
