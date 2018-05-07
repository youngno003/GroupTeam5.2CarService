package com.example.mac.democustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ListView listView;
    ArrayList<Laptop> list;
    Adapter_Laptop adapter;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mData = FirebaseDatabase.getInstance().getReference();
        addControls();
    }


    private void addControls() {
        listView = (ListView) findViewById(R.id.lv_laptop);

        list = new ArrayList<>();
        //list.add(new Laptop("anh",56.6,"123"));
        adapter = new Adapter_Laptop(this, list);

        listView.setAdapter(adapter);
        //makeDummyData();
        getFireBase();
    }

    private void getFireBase() {
//        mData.child("laptop").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Laptop a = dataSnapshot.getValue(Laptop.class);
//                adapter.addList(a);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        mData.child("latop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot laptopDataSnapshot : dataSnapshot.getChildren()){
                    Laptop a = laptopDataSnapshot.getValue(Laptop.class);
                    Log.d(TAG,a.toString());
                    adapter.addList(a);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void makeDummyData(){
        Laptop a = new Laptop("Macbook", 12000.0, "hihi");
        Laptop b = new Laptop("Asus", 6500.0,"hehe");

        mData.child("latop").push().setValue(a);
        mData.child("latop").push().setValue(b);
    }
}
