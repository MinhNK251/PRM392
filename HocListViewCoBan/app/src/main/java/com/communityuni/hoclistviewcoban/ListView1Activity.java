package com.communityuni.hoclistviewcoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListView1Activity extends AppCompatActivity {

    ListView lvData1;
    String []arrData={"Hà Nội","Huế","Đà Nẵng","Sài Gòn","Cần Thơ","Đà Lạt","Tây Ninh","Buôn mê thuột","Nha Trang","Phan Thiết","Vinh","Quảng Ninh","Tiền Giang"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvData1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListView1Activity.this,"Bạn chọn:"+arrData[i],Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        lvData1= (ListView) findViewById(R.id.lvData1);
        //tạo adapter:
        adapter=new ArrayAdapter<String>(
                ListView1Activity.this,
                android.R.layout.simple_list_item_1,
                arrData
                );
        //gán adapter cho listView:
        lvData1.setAdapter(adapter);
    }
}
