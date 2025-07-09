package com.communityuni.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.communityuni.baitaprenluyen9.R;
import com.communityuni.model.NhanVien;

/**
 * Created by cafe on 25/12/2016.
 */

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    public NhanVienAdapter(Activity context, int resource) {
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
        CheckBox chkXoa= (CheckBox) customView.findViewById(R.id.chkXoa);
        final NhanVien nv=getItem(position);
        if(nv.isNu())
            imgHinh.setImageResource(R.drawable.woman);
        else
            imgHinh.setImageResource(R.drawable.man);
        txtTen.setText(nv.getMa()+" - "+nv.getTen());
        chkXoa.setChecked(nv.isXoa());

        chkXoa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                nv.setXoa(b);
            }
        });

        return customView;
    }
}
