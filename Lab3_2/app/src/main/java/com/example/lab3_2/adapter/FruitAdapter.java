package com.example.lab3_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lab3_2.R;
import com.example.lab3_2.model.FruitItem;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<FruitItem> {
    Context context;
    List<FruitItem> fruitList;

    public FruitAdapter(Context context, List<FruitItem> fruits) {
        super(context, 0, fruits);
        this.context = context;
        this.fruitList = fruits;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        FruitItem item = fruitList.get(position);

        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descTextView = convertView.findViewById(R.id.descTextView);

        imageView.setImageResource(item.getImageResId());
        titleTextView.setText(item.getTitle());
        descTextView.setText(item.getDescription());

        return convertView;
    }
}
