package com.communityuni.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.communityuni.baitaprenluyen12.R;
import com.communityuni.model.MyFile;

/**
 * Created by cafe on 28/12/2016.
 */

public class MyFileAdapter extends ArrayAdapter<MyFile>
{
    Activity context;
    int resource;

    public MyFileAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView=this.context.getLayoutInflater().inflate(this.resource,null);
        ImageView imgHinh= (ImageView) customView.findViewById(R.id.imgHinh);
        TextView txtTen= (TextView) customView.findViewById(R.id.txtTen);
        TextView txtMoTa= (TextView) customView.findViewById(R.id.txtMota);
        MyFile file=getItem(position);
        switch (file.getFileType())
        {
            case  FOLDER:
                imgHinh.setImageResource(R.drawable.folder);
                break;
            case MP3:
                imgHinh.setImageResource(R.drawable.mp3);
                break;
            case MP4:
                imgHinh.setImageResource(R.drawable.mp4);
                break;
            case NOTSUPPORT:
                imgHinh.setImageResource(R.drawable.notsupport);
                break;
            default:
                imgHinh.setImageResource(R.drawable.notsupport);
                break;
        }
        txtMoTa.setText(file.getDescription());
        txtTen.setText(file.getDisplay());
        return customView;
    }
}
