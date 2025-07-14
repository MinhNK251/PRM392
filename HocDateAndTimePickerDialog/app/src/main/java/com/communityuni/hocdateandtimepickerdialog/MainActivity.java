package com.communityuni.hocdateandtimepickerdialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtDate;
    ImageView imgDate;
    Calendar calendarDate=Calendar.getInstance();
    SimpleDateFormat sdfDate=new SimpleDateFormat("dd/MM/yyyy");

    TextView txtTime;
    ImageView imgTime;
    Calendar calendarTime=Calendar.getInstance();
    SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moManHinhDatePickerDialog();
            }
        });
        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moManHinhTimePickerDialog();
            }
        });
    }

    private void moManHinhTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener callBack=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendarTime.set(Calendar.HOUR,i);
                calendarTime.set(Calendar.MINUTE,i1);
                txtTime.setText(sdfTime.format(calendarTime.getTime()));
            }
        };
        TimePickerDialog dialog=new TimePickerDialog(
                MainActivity.this,
                callBack,
                calendarTime.get(Calendar.HOUR),
                calendarTime.get(Calendar.MINUTE),
                true
                );
        dialog.show();
    }

    private void moManHinhDatePickerDialog() {
        final DatePickerDialog.OnDateSetListener callBack=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendarDate.set(Calendar.YEAR,i);
                calendarDate.set(Calendar.MONTH,i1);
                calendarDate.set(Calendar.DAY_OF_MONTH,i2);
                txtDate.setText(sdfDate.format(calendarDate.getTime()));
            }
        };
        DatePickerDialog dialog=new DatePickerDialog(
                MainActivity.this,
                callBack,
                calendarDate.get(Calendar.YEAR),
                calendarDate.get(Calendar.MONTH),
                calendarDate.get(Calendar.DAY_OF_MONTH)
                );
        dialog.show();
    }

    private void addControls() {
        txtDate= (TextView) findViewById(R.id.txtDate);
        imgDate= (ImageView) findViewById(R.id.imgDate);

        txtTime= (TextView) findViewById(R.id.txtTime);
        imgTime= (ImageView) findViewById(R.id.imgTime);
    }
}
