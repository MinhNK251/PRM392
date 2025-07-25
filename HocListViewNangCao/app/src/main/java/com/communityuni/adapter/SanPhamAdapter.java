package com.communityuni.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.communityuni.hoclistviewnangcao.R;
import com.communityuni.model.SanPham;

/**
 * Created by cafe on 25/12/2016.
 */

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    public SanPhamAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=inflater.inflate(this.resource,null);

        ImageView imgHinh= (ImageView) customView.findViewById(R.id.imgHinh);
        TextView txtTen= (TextView) customView.findViewById(R.id.txtTen);
        TextView txtGia= (TextView) customView.findViewById(R.id.txtGia);

        SanPham sp=getItem(position);
        imgHinh.setImageResource(sp.getHinh());
        txtTen.setText(sp.getTen());
        txtGia.setText(sp.getGia()+" VNĐ");

        return customView;
    }
}
