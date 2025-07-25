package com.communityuni.hoclistviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.communityuni.adapter.SanPhamAdapter;
import com.communityuni.model.SanPham;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        fakeData();
    }

    private void fakeData() {
        sanPhamAdapter.add(new SanPham(R.drawable.h1,"Hoa 1",15000));
        sanPhamAdapter.add(new SanPham(R.drawable.h2,"Hoa 2",25000));
        sanPhamAdapter.add(new SanPham(R.drawable.h3,"Hoa 3",35000));
        sanPhamAdapter.add(new SanPham(R.drawable.h4,"Hoa 4",5000));
        sanPhamAdapter.add(new SanPham(R.drawable.h5,"Hoa 5",20000));
        sanPhamAdapter.add(new SanPham(R.drawable.h6,"Hoa 6",18000));
        sanPhamAdapter.add(new SanPham(R.drawable.h7,"Hoa 7",17000));
        sanPhamAdapter.add(new SanPham(R.drawable.h8,"Hoa 8",6000));
        sanPhamAdapter.add(new SanPham(R.drawable.h9,"Hoa 9",9000));
        sanPhamAdapter.add(new SanPham(R.drawable.h10,"Hoa 10",10000));
    }

    private void addEvents() {
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sp=sanPhamAdapter.getItem(i);
                Toast.makeText(MainActivity.this,"Bạn chọn:"+sp.getTen(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        lvSanPham= (ListView) findViewById(R.id.lvSanPham);
        sanPhamAdapter=new SanPhamAdapter(MainActivity.this,R.layout.item);
        lvSanPham.setAdapter(sanPhamAdapter);
    }
}
