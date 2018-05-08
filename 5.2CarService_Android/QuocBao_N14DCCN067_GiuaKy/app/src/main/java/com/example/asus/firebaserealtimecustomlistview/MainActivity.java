package com.example.asus.firebaserealtimecustomlistview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mData;
    ListView lvCV;
    ConVatAdapter adapter;
    ArrayList<ConVat> arrayConVat;
    String CONVAT = "CONVAT";
    Button btnThem;
    EditText edtTen,edtSoChan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mData = FirebaseDatabase.getInstance().getReference();
        lvCV = findViewById(R.id.lvConVat);
        btnThem = findViewById(R.id.buttonThem);
        edtTen = findViewById(R.id.editTextTen);
        edtSoChan = findViewById(R.id.editTextSoChan);


        arrayConVat = new ArrayList<>();
        adapter = new ConVatAdapter(MainActivity.this,R.layout.dong_con_vat,arrayConVat);
        lvCV.setAdapter(adapter);





        mData.child(CONVAT).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayConVat.clear();
                for(DataSnapshot cv : dataSnapshot.getChildren()){
                    ConVat conVat = cv.getValue(ConVat.class);
                    arrayConVat.add(conVat);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        lvCV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//                String key = mData.child(CONVAT).push().getKey();
//
//
//                mData.child(CONVAT).child(key).removeValue() ;
//
//                adapter.notifyDataSetChanged();
//            }
//        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString().trim();
                Integer sochan = Integer.parseInt(edtSoChan.getText().toString().trim());
                mData.child(CONVAT).push().setValue(new ConVat(ten,sochan));
                arrayConVat.add(new ConVat(ten,sochan));
                adapter.notifyDataSetChanged();
                edtTen.setText("");
                edtSoChan.setText("");
            }
        });

}



}
