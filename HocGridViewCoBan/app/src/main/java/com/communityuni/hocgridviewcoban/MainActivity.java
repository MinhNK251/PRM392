package com.communityuni.hocgridviewcoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvSanPham;
    ArrayAdapter<String>sanPhamAdapter;

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
                Toast.makeText(MainActivity.this,sanPhamAdapter.getItem(i),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        gvSanPham= (GridView) findViewById(R.id.gvSanPham);
        sanPhamAdapter=new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        gvSanPham.setAdapter(sanPhamAdapter);
        ArrayList<String>dsSP=new ArrayList<>();
        dsSP.add("Iphone");
        dsSP.add("SamSung");
        dsSP.add("BPhone");
        dsSP.add("Nokia");
        dsSP.add("LG");
        dsSP.add("IPad");
        dsSP.add("MAC");
        dsSP.add("Acer");
        dsSP.add("Asus");
        dsSP.add("Lenovo");
        dsSP.add("HP");
        dsSP.add("DELL");
        dsSP.add("SamSung J7");
        sanPhamAdapter.addAll(dsSP);
    }
}
