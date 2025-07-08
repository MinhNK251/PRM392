package com.communityuni.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.communityuni.baitaprenluyen8.R;
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
        View customView=this.context.getLayoutInflater().inflate(this.resource,null);
        TextView txtTen= (TextView) customView.findViewById(R.id.txtTen);
        TextView txtGia= (TextView) customView.findViewById(R.id.txtGia);
        ImageView imgHinh= (ImageView) customView.findViewById(R.id.imgHinh);
        final EditText edtSoLuong= (EditText) customView.findViewById(R.id.edtSoLuong);
        ImageView imgMua= (ImageView) customView.findViewById(R.id.imgMua);

        final SanPham sp=getItem(position);
        txtTen.setText(sp.getTen());
        txtGia.setText(sp.getGia()+" VNĐ");
        if(sp.getSoLuong()>0)
            edtSoLuong.setText(sp.getSoLuong()+"");
        imgMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl=Integer.parseInt(edtSoLuong.getText().toString());
                sp.setSoLuong(sl);
            }
        });
        imgHinh.setImageResource(sp.getHinh());

        return customView;
    }
}
