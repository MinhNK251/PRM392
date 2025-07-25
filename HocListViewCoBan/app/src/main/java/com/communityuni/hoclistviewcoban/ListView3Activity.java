package com.communityuni.hoclistviewcoban;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.communityuni.model.Contact;

public class ListView3Activity extends AppCompatActivity {

    EditText edtMa,edtTen,edtPhone;
    Button btnLuu;

    ListView lvContact;
    ArrayAdapter<Contact> contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyThemContact();
            }
        });
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact c=contactAdapter.getItem(i);

                contactAdapter.remove(c);
                return false;
            }
        });
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact c=contactAdapter.getItem(i);
                edtMa.setText(c.getId()+"");
                edtTen.setText(c.getName());
                edtPhone.setText(c.getPhone());
            }
        });
    }

    private void xuLyThemContact() {
        Contact c=new Contact();
        c.setId(Integer.parseInt(edtMa.getText().toString()));
        c.setName(edtTen.getText().toString());
        c.setPhone(edtPhone.getText().toString());

        contactAdapter.add(c);
    }

    private void addControls() {
        edtMa= (EditText) findViewById(R.id.edtMa);
        edtTen= (EditText) findViewById(R.id.edtTen);
        edtPhone= (EditText) findViewById(R.id.edtPhone);
        btnLuu= (Button) findViewById(R.id.btnLuu);
        lvContact= (ListView) findViewById(R.id.lvContact);
        contactAdapter=new ArrayAdapter<Contact>
                (ListView3Activity.this,
                 android.R.layout.simple_list_item_1);
        lvContact.setAdapter(contactAdapter);
    }
}
