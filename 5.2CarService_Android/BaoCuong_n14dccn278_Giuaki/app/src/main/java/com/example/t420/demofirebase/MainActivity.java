package com.example.t420.demofirebase;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {


        List<SinhVien> sinhViens = new ArrayList<SinhVien>();
        public DatabaseReference mData;
        EditText ten;
        EditText id;
        RecyclerView listview;

        Adapter adapter;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mData = FirebaseDatabase.getInstance().getReference("student");
            ten = (EditText)findViewById(R.id.editText);
            id = (EditText)findViewById(R.id.editText2);
            Button btn = (Button) findViewById(R.id.button);
            btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SinhVien sinhvien = new SinhVien(ten.getText().toString(), Integer.parseInt(id.getText().toString()));
                mData.child(id.getText().toString()).setValue(sinhvien);
            }
        });


// Attach a listener to read the data at our posts reference

            listview =  findViewById(R.id.listView);
            listview.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Adapter(MainActivity.this , sinhViens, new Adapter.OnListener(){

                @Override
                public void onDelete(int position) {
                    mData.child(sinhViens.get(position).getId().toString()).removeValue();
                }
            });
            mData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    sinhViens.clear();
                    if(dataSnapshot.hasChildren()) {
                        for(DataSnapshot item : dataSnapshot.getChildren()) {
                            sinhViens.add(item.getValue(SinhVien.class));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });

            listview.setAdapter(adapter);
    }
}


class SinhVien {
    String name;
    Integer id;
    SinhVien(String name,Integer id) {
        this.name = name;
        this.id = id;
    }
    SinhVien()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}