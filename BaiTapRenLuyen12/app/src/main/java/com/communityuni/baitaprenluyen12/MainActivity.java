package com.communityuni.baitaprenluyen12;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.communityuni.adapter.MyFileAdapter;
import com.communityuni.model.FileSupport;
import com.communityuni.model.MyFile;
import com.communityuni.util.Converter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ListView lvMyFile;
    MyFileAdapter myFileAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

        readFolderFromSDCard();
    }

    private void readFolderFromSDCard() {
        String root= Environment.getExternalStorageDirectory().getAbsolutePath();
        Intent intentParent=getIntent();
        if(intentParent!=null)
        {
            if(intentParent.hasExtra("PARENT"))
            {
                root=intentParent.getStringExtra("PARENT");
            }
        }
        File f=new File(root);
        File []dsFileOrFolder=f.listFiles();
        myFileAdapter.clear();
        for (File fileOrFolder:dsFileOrFolder)
        {
            MyFile mf=new MyFile();
            mf.setPath(fileOrFolder.getAbsolutePath());
            mf.setDisplay(fileOrFolder.getName());
            if(fileOrFolder.isDirectory())
            {
                mf.setFileType(FileSupport.FOLDER);
                mf.setDescription("Có "+fileOrFolder.list().length+" tập tin con");
                mf.setHasChild(true);
            }
            else
            {
                if(fileOrFolder.getPath().toLowerCase().endsWith(".mp3"))
                    mf.setFileType(FileSupport.MP3);
                else if(fileOrFolder.getPath().toLowerCase().endsWith(".mp4"))
                    mf.setFileType(FileSupport.MP4);
                else
                    mf.setFileType(FileSupport.NOTSUPPORT);
                String s= Converter.humanReadableByteCount(fileOrFolder.length(),true);
                mf.setDescription(s);
            }
            myFileAdapter.add(mf);
        }
    }

    private void addEvents() {
        lvMyFile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyFile mf=myFileAdapter.getItem(i);
                xyLyMoFile(mf);
            }
        });
    }

    private void xyLyMoFile(MyFile mf) {
        if(mf.getFileType()==FileSupport.NOTSUPPORT)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("File này chưa hỗ trợ, vui lòng tự làm...");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.show();
        }
        else
        {
            if(mf.getFileType()==FileSupport.FOLDER)
            {
                if(mf.isHasChild())
                {
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    intent.putExtra("PARENT",mf.getPath());
                    startActivity(intent);
                }
            }
            else if(mf.getFileType()!=FileSupport.NOTSUPPORT)
            {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                Uri uri=Uri.parse(mf.getPath());
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }

    private void addControls() {
        lvMyFile= (ListView) findViewById(R.id.lvMyFile);
        myFileAdapter=new MyFileAdapter(MainActivity.this,R.layout.item);
        lvMyFile.setAdapter(myFileAdapter);
    }
}
