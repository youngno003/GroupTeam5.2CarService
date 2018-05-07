package d14cqcp01.group5.carservices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DatChoActivity extends AppCompatActivity {

    private final static String TAG = DatChoActivity.class.getSimpleName();
    private String currentCarID;

    private FirebaseUser user;
    private XeKhach currentCar;
    private DatabaseReference ticketListRef; // Tham chiếu đến danh sách chi tiết vé .
    private DatabaseReference coachRef; // Tham chiếu đến xe khách hien tai.
    private DatabaseReference ticketHistoryOfUser; // Tham chiếu đến danh sách vé của người dùng.

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datcho);
        Button btnDatCho = findViewById(R.id.btnDatVe);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        currentCarID = intent.getStringExtra(getString(R.string.currentCarID));
        String currentCarType = intent.getStringExtra(getString(R.string.currentCarType));
        if(currentCarID.isEmpty() || currentCarType.isEmpty()){
            Log.e(TAG, "Can't find currentCar");
            return;
        }
        Log.d(TAG, "Xe: " + currentCarID + "-------" + currentCarType);

        SharedPreferences myCar = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = myCar.edit();
        edt.putString(getString(R.string.currentCarID),currentCarID);
        edt.putString(getString(R.string.currentCarType),currentCarType);
        edt.commit();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final FragmentXuLyDatCho fragment = new FragmentXuLyDatCho();
        transaction.add(R.id.fragmetnContainer, fragment);
        transaction.commit();

        coachRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_COACH)+"/" + currentCarID );
        ticketListRef = FirebaseDatabase.getInstance().getReference(getString(R.string.NODE_TICKET));

        if(user!=null){
            ticketHistoryOfUser = FirebaseDatabase.getInstance().getReference("UserList/" + user.getUid() + "/ticketList");
        }

        btnDatCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FragmentXuLyDatCho.getSelectedSeatNumbers().size() >0){
                    ArrayList<String> seats = FragmentXuLyDatCho.getSelectedSeatNumbers();
                    if(seats.size()>0){
                        for(String seatNumber: seats){
                            final String key = ticketListRef.push().getKey();
                            VeXe ve = new VeXe();
                            ve.setIdCoach(currentCarID);
                            ve.setSeatNumber(Integer.parseInt(seatNumber));
                            ve.setOrderTime(System.currentTimeMillis());
                            ve.setStatus("Chờ thanh toán");
                            ve.setId(key);
                            ticketListRef.child(key).setValue(ve);

                            Log.d(TAG,"Tạo vé thành công: " + key);
                            coachRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    currentCar = dataSnapshot.getValue(XeKhach.class);
                                    try{
                                        if(currentCar.getTicketList() == null){
                                            currentCar.setTicketList(new ArrayList<String>());

                                        }
                                        currentCar.addTicket(key);
                                        coachRef.setValue(currentCar);
                                    }catch (Exception e){
                                        Log.e(TAG,e.toString());
                                        currentCar.setTicketList(new ArrayList<String>());
                                        coachRef.setValue(currentCar);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            ticketHistoryOfUser.push().setValue(key);
                        }
                        Toast.makeText(DatChoActivity.this, "Đặt vé thành công.",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(DatChoActivity.this,SearchActivity.class);
                        startActivity(intent1);
                        finish();
                    }else{
                        Toast.makeText(DatChoActivity.this, "Bạn chưa chọn ghế",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(DatChoActivity.this, "Bạn chỉ được đặt 6 vé mỗi xe",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent(DatChoActivity.this,SearchActivity.class);
        startActivity(intent1);
        finish();
    }
}