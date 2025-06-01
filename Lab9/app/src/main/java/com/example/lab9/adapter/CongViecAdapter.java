package com.example.lab9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab9.MainActivity;
import com.example.lab9.model.CongViec;
import com.example.lab9.R;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView tv_Name;
        ImageView iv_Delete, iv_Edit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tv_Name = (TextView) view.findViewById(R.id.tv_Name);
            holder.iv_Delete = (ImageView) view.findViewById(R.id.iv_Delete);
            holder.iv_Edit = (ImageView) view.findViewById(R.id.iv_Edit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CongViec congViec = congViecList.get(i);
        holder.tv_Name.setText(congViec.getTenCV());

        holder.iv_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogUpdate(congViec.getTenCV(), congViec.getIdCv());
            }
        });
        return view;
    }
}
