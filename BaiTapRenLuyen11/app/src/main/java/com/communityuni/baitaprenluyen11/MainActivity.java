package com.communityuni.baitaprenluyen11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.communityuni.model.SanPham;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayAdapter<SanPham>sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sp=sanPhamAdapter.getItem(i);
                Intent intent=new Intent(MainActivity.this,ChiTietActivity.class);
                intent.putExtra("SANPHAM",sp);
                startActivityForResult(intent,113);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==113 && resultCode==115)
        {
            boolean isXoa=data.getBooleanExtra("XacNhanXoa",false);
            SanPham spXoa= (SanPham) data.getSerializableExtra("SANPHAM");
            for (int i=0;i<sanPhamAdapter.getCount();i++)
            {
                SanPham sp=sanPhamAdapter.getItem(i);
                if(sp.getMa().equals(spXoa.getMa()))
                {
                    sanPhamAdapter.remove(sp);
                    break;
                }
            }
        }
    }

    private void addControls() {
        lvSanPham= (ListView) findViewById(R.id.lvSanPham);
        sanPhamAdapter=new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamAdapter);

        sanPhamAdapter.add(new SanPham("sp1","Cocacola",15000));
        sanPhamAdapter.add(new SanPham("sp2","Pepsi",17000));
        sanPhamAdapter.add(new SanPham("sp3","Redbull",12000));
        sanPhamAdapter.add(new SanPham("sp4","Sting",25000));
        sanPhamAdapter.add(new SanPham("sp5","Aquafina",5000));
    }
}
