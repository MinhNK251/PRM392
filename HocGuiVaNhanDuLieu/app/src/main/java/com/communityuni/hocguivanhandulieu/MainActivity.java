package com.communityuni.hocguivanhandulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.communityuni.model.DanhBa;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void truyenTrucTiepIntent(View view) {
        Intent intent=new Intent(MainActivity.this,Sub1Activity.class);
        intent.putExtra("a",113);
        intent.putExtra("b",5.5);
        intent.putExtra("c",true);
        intent.putExtra("s","Obama-Putin-Kim Jong Un");
        DanhBa bill=new DanhBa(1,"BILL","0981234567");
        intent.putExtra("x",bill);

        startActivity(intent);
    }

    public void dongGoiBundle(View view) {
        Intent intent=new Intent(MainActivity.this,Sub2Activity.class);

        Bundle bundle=new Bundle();
        bundle.putInt("a",113);
        bundle.putDouble("b",5.5);
        bundle.putBoolean("c",true);
        bundle.putString("s","Obama-Putin-Kim Jong Un");
        DanhBa bill=new DanhBa(1,"BILL","0981234567");
        bundle.putSerializable("x",bill);

        intent.putExtra("mybundle",bundle);

        startActivity(intent);

    }
}
