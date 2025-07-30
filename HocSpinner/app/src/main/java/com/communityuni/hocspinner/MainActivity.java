package com.communityuni.hocspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerSanPham;
    ArrayAdapter<String> adapter;

    TextView txtSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        spinnerSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s=adapter.getItem(i);
                txtSanPham.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addControls() {
        spinnerSanPham= (Spinner) findViewById(R.id.spinnerSanPham);
        adapter=new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_spinner_item
                );
        adapter.add("Cocacola");
        adapter.add("Pepsi");
        adapter.add("Redbull");
        adapter.add("Aquafina");
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerSanPham.setAdapter(adapter);

        txtSanPham= (TextView) findViewById(R.id.txtSanPham);
    }
}
