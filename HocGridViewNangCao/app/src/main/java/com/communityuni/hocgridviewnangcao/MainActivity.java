package com.communityuni.hocgridviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.communityuni.adapter.SanPhamAdapter;
import com.communityuni.model.SanPham;

public class MainActivity extends AppCompatActivity {

    GridView gvSanPham;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        gvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sp=sanPhamAdapter.getItem(i);
                hienThiChiTiet(sp);
            }
        });
    }

    private void hienThiChiTiet(SanPham sp) {
        setContentView(R.layout.soloitem);
        TextView txtTen= (TextView) findViewById(R.id.txtTen);
        ImageView imgHinh= (ImageView) findViewById(R.id.imgHinh);
        Button btnTroVe= (Button) findViewById(R.id.btnTroVe);
        imgHinh.setImageResource(sp.getHinh());
        txtTen.setText(sp.getTen());
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
                addControls();
                addEvents();
            }
        });
    }

    private void addControls() {
        gvSanPham= (GridView) findViewById(R.id.gvHinh);
        sanPhamAdapter=new SanPhamAdapter(MainActivity.this,R.layout.item);
        gvSanPham.setAdapter(sanPhamAdapter);
        sanPhamAdapter.add(new SanPham(R.drawable.h1,"Minion 1"));
        sanPhamAdapter.add(new SanPham(R.drawable.h2,"Minion 2"));
        sanPhamAdapter.add(new SanPham(R.drawable.h3,"Minion 3"));
        sanPhamAdapter.add(new SanPham(R.drawable.h4,"Minion 4"));
        sanPhamAdapter.add(new SanPham(R.drawable.h5,"Minion 5"));
        sanPhamAdapter.add(new SanPham(R.drawable.h6,"Minion 6"));
        sanPhamAdapter.add(new SanPham(R.drawable.h7,"Minion 7"));
        sanPhamAdapter.add(new SanPham(R.drawable.h8,"Minion 8"));
        sanPhamAdapter.add(new SanPham(R.drawable.h9,"Minion 9"));
        sanPhamAdapter.add(new SanPham(R.drawable.h10,"Minion 10"));
    }
}
