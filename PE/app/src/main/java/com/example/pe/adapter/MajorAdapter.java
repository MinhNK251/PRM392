package com.example.pe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pe.MainActivity;
import com.example.pe.R;
import com.example.pe.model.Nganh;

import java.util.List;

public class MajorAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Nganh> majorList;

    public MajorAdapter(MainActivity context, int layout, List<Nganh> majorList) {
        this.context = context;
        this.layout = layout;
        this.majorList = majorList;
    }

    @Override
    public int getCount() {
        return majorList.size();
    }

    @Override
    public Object getItem(int i) {
        return majorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return majorList.get(i).getId();
    }

    private class ViewHolder {
        TextView tvMajorId, tvMajorName;
        ImageView iv_Edit, iv_Delete;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new MajorAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, null);

            holder.tvMajorId = view.findViewById(R.id.tvMajorId);
            holder.tvMajorName = view.findViewById(R.id.tvMajorName);
            holder.iv_Edit = view.findViewById(R.id.iv_Edit);
            holder.iv_Delete = view.findViewById(R.id.iv_Delete);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Nganh major = majorList.get(i);
        holder.tvMajorId.setText("ID: " + major.getId());
        holder.tvMajorName.setText("Tên ngành: " + major.getName());

        holder.iv_Edit.setOnClickListener(v ->
                context.showUpdateMajorDialog(major));

        holder.iv_Delete.setOnClickListener(v ->
                context.confirmDeleteMajor(major));

        return view;
    }
}
