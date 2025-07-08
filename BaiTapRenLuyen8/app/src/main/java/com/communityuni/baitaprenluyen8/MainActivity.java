package com.communityuni.baitaprenluyen8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

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
        fakeData();
    }

    private void fakeData() {
        sanPhamAdapter.add(new SanPham("Hoa 1",25000,R.drawable.h1,0));
        sanPhamAdapter.add(new SanPham("Hoa 2",35000,R.drawable.h2,0));
        sanPhamAdapter.add(new SanPham("Hoa 3",15000,R.drawable.h3,0));
        sanPhamAdapter.add(new SanPham("Hoa 4",20000,R.drawable.h4,0));
        sanPhamAdapter.add(new SanPham("Hoa 5",25000,R.drawable.h5,0));
        sanPhamAdapter.add(new SanPham("Hoa 6",45000,R.drawable.h6,0));
        sanPhamAdapter.add(new SanPham("Hoa 7",75000,R.drawable.h7,0));
        sanPhamAdapter.add(new SanPham("Hoa 8",15000,R.drawable.h8,0));
        sanPhamAdapter.add(new SanPham("Hoa 9",22000,R.drawable.h9,0));
        sanPhamAdapter.add(new SanPham("Hoa 10",27000,R.drawable.h10,0));
        sanPhamAdapter.add(new SanPham("Hoa 11",39000,R.drawable.h11,0));
    }

    private void addControls() {
        gvSanPham= (GridView) findViewById(R.id.gvSanPham);

        sanPhamAdapter=new SanPhamAdapter(MainActivity.this,R.layout.item);

        gvSanPham.setAdapter(sanPhamAdapter);
    }
}
