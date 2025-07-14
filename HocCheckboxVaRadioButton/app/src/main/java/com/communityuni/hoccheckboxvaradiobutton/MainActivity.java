package com.communityuni.hoccheckboxvaradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkAndroid,chkSwift,chkCSharp;
    Button btnXacNhanSoThich;
    TextView txtSoThich;

    RadioButton radObama,radPutin,radKim;
    TextView txtBinhChon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        chkAndroid= (CheckBox) findViewById(R.id.chkAndroid);
        chkCSharp= (CheckBox) findViewById(R.id.chkCSharp);
        chkSwift= (CheckBox) findViewById(R.id.chkSwift);
        txtSoThich = (TextView) findViewById(R.id.txtSothich);
        btnXacNhanSoThich= (Button) findViewById(R.id.btnXacNhan);

        radObama= (RadioButton) findViewById(R.id.radObama);
        radPutin= (RadioButton) findViewById(R.id.radPutin);
        radKim= (RadioButton) findViewById(R.id.radKim);
        txtBinhChon= (TextView) findViewById(R.id.txtBinhChon);
    }

    public void xuLyXacNhanSoThich(View view) {
        String msg="";
        if(chkAndroid.isChecked()==true)
        {
            msg+=chkAndroid.getText().toString()+"\n";
        }
        if(chkSwift.isChecked())
        {
            msg+=chkSwift.getText().toString()+"\n";
        }
        if(chkCSharp.isChecked())
        {
            msg+=chkCSharp.getText().toString();
        }
        txtSoThich.setText(msg);
    }

    public void xuLyBinhChon(View view) {
        /*if(radObama.isChecked())
        {
            txtBinhChon.setText(radObama.getText());
        }
        else if(radPutin.isChecked())
        {
            txtBinhChon.setText(radPutin.getText());
        }
        else if(radKim.isChecked())
        {
            txtBinhChon.setText(radKim.getText());
        }*/
        RadioGroup group= (RadioGroup) findViewById(R.id.group);
        //tìm RadioButotn trong group mà đang được checked:
        int id= group.getCheckedRadioButtonId();
        if(id>0)
        {
            RadioButton radioButton = (RadioButton) findViewById(id);
            txtBinhChon.setText(radioButton.getText());
        }
    }
}
