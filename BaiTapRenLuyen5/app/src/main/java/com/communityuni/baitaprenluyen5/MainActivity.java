package com.communityuni.baitaprenluyen5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTen,edtChieuCao,edtCanNang;
    Button btnTinhBMI;
    TextView txtBMI,txtChanDoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTinhBMI.setOnClickListener(this);
    }

    private void addControls() {
        edtTen= (EditText) findViewById(R.id.edtTen);
        edtChieuCao= (EditText) findViewById(R.id.edtChieuCao);
        edtCanNang= (EditText) findViewById(R.id.edtCanNang);
        btnTinhBMI= (Button) findViewById(R.id.btnTinhBMI);
        txtBMI= (TextView) findViewById(R.id.txtBMI);
        txtChanDoan= (TextView) findViewById(R.id.txtChanDoan);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(btnTinhBMI))
        {
            xuLyTinhBMI();
        }
    }

    private void xuLyTinhBMI() {
        double H=Double.parseDouble(edtChieuCao.getText().toString());
        double W=Double.parseDouble(edtCanNang.getText().toString());
        double BMI=W/Math.pow(H,2);
        txtBMI.setText(BMI+"");
        if(BMI<18)
            txtChanDoan.setText(edtTen.getText().toString() +"=>Gầy");
        else  if(BMI<=24.9)
            txtChanDoan.setText(edtTen.getText().toString() +"=>Bình thường");
        else  if(BMI<=29.9)
            txtChanDoan.setText(edtTen.getText().toString() +"=>Béo phì độ 1");
        else if (BMI<=34.9)
            txtChanDoan.setText(edtTen.getText().toString() +"=>Béo phì độ 2");
        else
            txtChanDoan.setText(edtTen.getText().toString() +"=>Béo phì độ 3");
    }
}
