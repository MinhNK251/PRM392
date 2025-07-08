package com.communityuni.baitaprenluyen7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.communityuni.adapter.ContactAdapter;
import com.communityuni.model.Contact;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ContactAdapter contactAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        fakeData();
    }

    private void fakeData() {
        Random random=new Random();
        for(int i=0;i<500;i++)
        {
            Contact contact=new Contact();
            contact.setTen("Tên "+i);
            String phone="098";
            int x=random.nextInt(3);
            if(x==1)
                phone="090";
            else if(x==2)
                phone="094";
            for (int p=0;p<7;p++)
            {
                phone+=random.nextInt(10);
            }
            contact.setPhone(phone);
            contactAdapter.add(contact);
        }
    }

    private void addControls() {
        lvContact= (ListView) findViewById(R.id.lvContact);
        contactAdapter=new ContactAdapter(MainActivity.this,R.layout.item);
        lvContact.setAdapter(contactAdapter);
    }
}
