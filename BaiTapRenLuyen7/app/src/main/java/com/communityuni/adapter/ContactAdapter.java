package com.communityuni.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.communityuni.baitaprenluyen7.R;
import com.communityuni.model.Contact;

/**
 * Created by cafe on 25/12/2016.
 */

public class ContactAdapter extends ArrayAdapter<Contact>
{
    Activity context;
    int resource;
    public ContactAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView=this.context.getLayoutInflater().inflate(this.resource,null);

        TextView txtTen= (TextView) customView.findViewById(R.id.txtTen);
        TextView txtPhone= (TextView) customView.findViewById(R.id.txtPhone);
        ImageView imgCall= (ImageView) customView.findViewById(R.id.imgCall);
        ImageView imgSms= (ImageView) customView.findViewById(R.id.imgSms);
        ImageView imgDetail= (ImageView) customView.findViewById(R.id.imgDetail);

        final Contact contact=getItem(position);
        txtTen.setText(contact.getTen());
        txtPhone.setText(contact.getPhone());

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyCall(contact);
            }
        });
        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLySms(contact);
            }
        });
        imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyDetail(contact);
            }
        });


        return customView;
    }

    private void xuLyDetail(Contact contact) {

    }

    private void xuLySms(Contact contact) {

    }

    private void xuLyCall(Contact contact) {
        Toast.makeText(this.context,"Bạn muốn gọi cho "+contact.getPhone(),Toast.LENGTH_LONG).show();
    }
}
