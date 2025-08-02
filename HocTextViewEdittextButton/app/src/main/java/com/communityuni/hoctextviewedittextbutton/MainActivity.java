package com.communityuni.hoctextviewedittextbutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edtTen;
    Button btnXacNhan;
    TextView txtKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        edtTen= (EditText) findViewById(R.id.edtTen);
        txtKetQua= (TextView) findViewById(R.id.txtKetQua);
        btnXacNhan= (Button) findViewById(R.id.btnXacNhan);

    }

    public void xuLyXacNhan(View view) {
        txtKetQua.setText(edtTen.getText().toString());
        btnXacNhan.setTextColor(Color.RED);
    }
}
