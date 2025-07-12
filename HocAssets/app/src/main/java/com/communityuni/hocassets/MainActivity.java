package com.communityuni.hocassets;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtFont;
    ListView lvFont;
    ArrayAdapter<String>fontAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fontName=fontAdapter.getItem(i);
                Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/"+fontName);
                txtFont.setTypeface(typeface);
                xuLyNgheNhac();
            }
        });
    }

    private void xuLyNgheNhac() {
        try
        {
            AssetFileDescriptor afd=getAssets().openFd("musics/manolo_camp-morning_coffee.wav");
            MediaPlayer player=new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            player.prepare();
            player.start();
        }
        catch (Exception ex)
        {
            Log.e("Loi",ex.toString());
        }
    }

    private void addControls() {
        txtFont= (TextView) findViewById(R.id.txtFont);
        lvFont= (ListView) findViewById(R.id.lvFont);
        fontAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1);
        lvFont.setAdapter(fontAdapter);
        try {
            AssetManager assetManager=getAssets();
            String []arrFont=assetManager.list("fonts");
            fontAdapter.addAll(arrFont);
        }
        catch (Exception ex)
        {
            Log.e("Loi",ex.toString());
        }
    }
}
